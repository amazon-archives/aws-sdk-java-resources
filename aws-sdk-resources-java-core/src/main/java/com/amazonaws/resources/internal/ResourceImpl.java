/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.resources.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.CollectionModel;
import com.amazonaws.resources.internal.model.EmbeddedIdentifierMapping;
import com.amazonaws.resources.internal.model.FlatMapping;
import com.amazonaws.resources.internal.model.IdentifierModel;
import com.amazonaws.resources.internal.model.ReferenceModel;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.SubResourceGetterModel;

/**
 * A generic implementation of an arbitrary resource type. Wraps a set of
 * metadata about the resource which describe how to map operations on the
 * resource to service calls.
 */
public final class ResourceImpl implements ActionContext {

    private final ServiceModel serviceModel;
    private final ResourceModel resourceModel;
    private final Object client;
    private final Map<String, ?> identifiers;

    private volatile Map<String, ?> attributes;

    public ResourceImpl(
            ServiceModel serviceModel,
            ResourceModel resourceModel,
            Object client,
            Map<String, ?> identifiers) {

        this(serviceModel, resourceModel, client, identifiers, null);
    }

    public ResourceImpl(
            ServiceModel serviceModel,
            ResourceModel resourceModel,
            Object client,
            Map<String, ?> identifiers,
            Object data) {

        this.serviceModel = serviceModel;
        this.resourceModel = resourceModel;
        this.client = client;
        this.identifiers = identifiers;

        if (data != null) {
            this.attributes = parseAttributes(resourceModel, data);
        }

        for (Map.Entry<String, IdentifierModel> entry : resourceModel
                .getIdentifiers().entrySet()) {
            if (!(identifiers.containsKey(entry.getKey()))) {
                throw new IllegalArgumentException(
                        "Missing Identifier data. Identifier *" + entry.getKey()
                                + "* expected to construct the resource.");
            }
        }
    }

    @Override
    public boolean hasState() {
        return true;
    }

    @Override
    public ServiceModel getServiceModel() {
        return serviceModel;
    }

    /**
     * @return the model for this resource type
     */
    public ResourceModel getResourceModel() {
        return resourceModel;
    }

    @Override
    public Object getClient() {
        return client;
    }

    /**
     * @return the set of identifiers for this resource
     */
    public Map<String, ?> getIdentifiers() {
        return identifiers;
    }

    @Override
    public Object getIdentifier(String name) {
        return identifiers.get(name);
    }

    /**
     * Returns the value for the given attribute by lazily loading the
     * resource if need be and digging into the result.
     */
    @Override
    public Object getAttribute(String name) {
        if (attributes == null) {
            load(null, null);
        }
        return attributes.get(name);
    }

    /**
     * @return true if this resource has been loaded; false otherwise
     */
    public boolean isLoaded() {
        return (attributes != null);
    }

    /**
     * Explicitly loads a representation of this resource by calling the
     * service to retrieve a new set of attributes.
     *
     * @param extractor optional result extractor object
     */
    public synchronized boolean load(
            AmazonWebServiceRequest request,
            ResultCapture<?> extractor) {

        if (attributes != null) {
            return false;
        }

        ActionModel action = resourceModel.getLoadAction();
        if (action == null) {
            throw new UnsupportedOperationException(
                "This resource does not support being loaded.");
        }

        // The facade generator ensures it's only ever possible to pass an
        // instance of the appropriate type.
        @SuppressWarnings("unchecked")
        ResultCapture<Object> erasedExtractor =
                (ResultCapture<Object>) extractor;

        ActionResult result = ActionUtils.perform(
                this, action, request, erasedExtractor);

        this.attributes = parseAttributes(resourceModel, result.getData());

        return true;
    }

    private static Map<String, ?> parseAttributes(
            ResourceModel resourceModel,
            Object object) {

        Map<String, Object> result = new HashMap<>();

        for (String attribute : resourceModel.getAttributes().keySet()) {
            Object value = ReflectionUtils.getByPath(
                    object, Collections.singletonList(attribute));

            result.put(attribute, value);
        }

        return result;
    }

    /**
     * Returns a resource that is referenced by this resource. This may involve
     * a call to the service to dereference this resource if one or more of
     * the identifiers of the referenced resource are contained in the
     * attributes of this resource, and we don't have a fresh set of attributes
     * for this resource in our cache.
     * <p>
     * For example, an S3 Object has a reference to S3 Bucket it belongs to.
     *
     * @param name the name of the reference to follow
     * @return the referenced resource
     */
    public ResourceImpl getReference(String name) {
        ReferenceModel reference = resourceModel.getReference(name);

        if (reference == null) {
            throw new IllegalArgumentException("No reference named " + name);
        }
        if (reference.isMultiValued()) {
            throw new IllegalArgumentException(
                    "The " + name + " reference is multi-valued. You must use "
                    + "the getMultiReference method to resolve it.");
        }

        Map<String, Object> ids = new HashMap<>();

        if (reference.getIdentifierMappings() != null) {
            for (FlatMapping mapping : reference.getIdentifierMappings()) {
                Object value = identifiers.get(mapping.getSource());
                if (value == null) {
                    throw new IllegalStateException(
                            "The " + name + " reference model has a mapping "
                            + "for the " + mapping.getSource() + " identifier, "
                            + "but this resource doesn't have an identifier of "
                            + "that name!");
                }
                ids.put(mapping.getTarget(), value);
            }
        }

        if (reference.getAttributeMappings() != null) {
            for (EmbeddedIdentifierMapping mapping
                    : reference.getAttributeMappings()) {

                Object value = getAttribute(mapping.getSource());
                if (value == null) {
                    // TODO: Check whether the identifier is nullable; if so
                    // it may be fine for this to be null...
                    return null;
                }

                ids.put(mapping.getTarget(), value);
            }
        }

        ResourceModel refTypeModel =
                serviceModel.getResource(reference.getType());

        return new ResourceImpl(serviceModel, refTypeModel, client, ids);
    }

    /**
     * Returns a set of resources that are referenced by this resource. This
     * may involve a call to the service to dereference this resource if we
     * don't have a fresh set of attributes for this resource in our cache.
     * <p>
     * For example, an EC2 Instance has a reference to the set of security
     * groups to which it belongs.
     *
     * @param name the name of the reference
     * @return the list of referenced resources
     */
    public List<ResourceImpl> getMultiReference(String name) {
        ReferenceModel reference = resourceModel.getReference(name);
        if (reference == null) {
            throw new IllegalArgumentException("No reference named " + name);
        }

        Map<String, Object> sharedIds = new HashMap<>();

        if (reference.getIdentifierMappings() != null) {
            for (FlatMapping mapping : reference.getIdentifierMappings()) {
                Object value = identifiers.get(mapping.getSource());
                if (value == null) {
                    throw new IllegalStateException(
                            "The " + name + " reference model has a mapping "
                            + "for the " + mapping.getSource() + " identifier, "
                            + "but this resource doesn't have an identifier of "
                            + "that name!");
                }
                sharedIds.put(mapping.getTarget(), value);
            }
        }

        Map<String, List<?>> ids = new HashMap<>();
        int listSize = -1;

        if (reference.getAttributeMappings() != null) {
            for (EmbeddedIdentifierMapping mapping
                    : reference.getAttributeMappings()) {

                if (mapping.isMultiValued()) {
                    List<?> value =
                            (List<?>) getAttribute(mapping.getSource());
                    if (listSize < 0) {
                        listSize = value.size();
                    } else {
                        if (listSize != value.size()) {
                            throw new IllegalStateException(
                                    "List size mismatch! " + listSize + " vs "
                                    + value.size());
                        }
                    }
                    ids.put(mapping.getTarget(), value);
                } else {
                    Object value = getAttribute(mapping.getSource());
                    sharedIds.put(mapping.getTarget(), value);
                }
            }
        }

        if (listSize == 0) {
            return Collections.emptyList();
        }

        ResourceModel refTypeModel =
                serviceModel.getResource(reference.getType());

        List<ResourceImpl> rval = new ArrayList<>(listSize);

        for (int i = 0; i < listSize; ++i) {
            Map<String, Object> myIds = new HashMap<>(sharedIds);
            for (Map.Entry<String, List<?>> entry : ids.entrySet()) {
                myIds.put(entry.getKey(), entry.getValue().get(i));
            }
            rval.add(new ResourceImpl(
                    serviceModel, refTypeModel, client, myIds));
        }

        return Collections.unmodifiableList(rval);
    }

    /**
     * Returns a collection of resources which is referenced by this resource.
     * This may involve a call to the service to dereference this resource if
     * the collection has an attribute of this resource as one of its
     * identifiers and we don't have a fresh set of attributes for this
     * resource in our cache. Enumerating the collection always involves a
     * request (or multiple requests) to the service, otherwise this would
     * simply be a multi-valued reference.
     * <p>
     * For example, an S3 Bucket has a reference to a collection of objects.
     *
     * @param name the name of the collection reference
     * @param request client-specified parameters to merge onto the request
     * @return the referenced collection
     */
    public ResourceCollectionImpl getCollection(
            String name,
            AmazonWebServiceRequest request) {

        CollectionModel collection = resourceModel.getCollection(name);
        if (collection == null) {
            throw new IllegalArgumentException("No collection named " + name);
        }

        return new ResourceCollectionImpl(
                this,
                collection.getListAction(),
                request);
    }

    /**
     * Get a subresource of this resource by identifier, (optionally mapping
     * in other identifiers of this resource).
     *
     * @param name the name of the subresource type
     * @param parameter the parameter for this request
     * @return the referenced subresource
     */
    public ResourceImpl getSubResource(String name, Object parameter) {

        SubResourceGetterModel getter =
                resourceModel.getSubResourceGetter(name);
        if (getter == null) {
            throw new UnsupportedOperationException(
                    "No sub-resource named " + name);
        }

        Map<String, Object> ids = new HashMap<>();

        if (getter.getParameterMapping() != null) {
            ids.put(getter.getParameterMapping().getTarget(), parameter);
        }

        if (getter.getIdentifierMappings() != null) {
            for (FlatMapping mapping : getter.getIdentifierMappings()) {
                Object value = identifiers.get(mapping.getSource());
                if (value == null) {
                    throw new IllegalStateException(
                        "The " + name + " subresource model has a mapping "
                        + "for the " + mapping.getSource() + " identifier, but "
                        + "this resource doesn't have an identifier of that "
                        + "name!");
                }
                ids.put(mapping.getTarget(), value);
            }
        }

        ResourceModel refTypeModel = serviceModel.getResource(name);

        return new ResourceImpl(serviceModel, refTypeModel, client, ids);
    }

    /**
     * Performs the given action on this resource. This always involves a
     * request to the service. It may mark the cached attributes of this
     * resource object dirty.
     *
     * @param name the name of the action to perform
     * @param request the client-specified request object
     * @param extractor an optional result extractor object
     * @return the result of executing the action
     */
    public ActionResult performAction(
            String name,
            AmazonWebServiceRequest request,
            ResultCapture<?> extractor) {

        ActionModel action = resourceModel.getAction(name);
        if (action == null) {
            throw new UnsupportedOperationException(
                    "Resource does not support the action " + name);
        }

        // The facade generator will ensure we only ever pass in an
        // appropriately-typed extractor object.
        @SuppressWarnings("unchecked")
        ResultCapture<Object> erasedExtractor =
                (ResultCapture<Object>) extractor;

        return ActionUtils.perform(this, action, request, erasedExtractor);
    }

    @Override
    public String toString() {
        return "{identifiers=" + identifiers
                + ", attributes=" + attributes
                + "}";
    }
}
