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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.FlatMapping;
import com.amazonaws.resources.internal.model.PathSourceMapping;
import com.amazonaws.resources.internal.model.PathTargetMapping;
import com.amazonaws.resources.internal.model.RequestModel;
import com.amazonaws.resources.internal.model.ResourceMapping;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.ResponseModel;

/**
 * A helper class containing logic for performing actions on a resource or a
 * collection.
 */
final class ActionUtils {
    /**
     * Performs the given action passing the given parameters.
     *
     * @param context the context on which this action is being called
     * @param action the model of the action to perform
     * @param request the client-supplied request object
     * @param extractor a result extractor object
     * @return the result of the action
     */
    public static ActionResult perform(
            ActionContext context,
            ActionModel action,
            AmazonWebServiceRequest request,
            ResultCapture<Object> extractor) {

        return perform(context, action, request, extractor, null);
    }

    /**
     * Performs the given action passing the given parameters.
     *
     * @param context the context on which this action is being called
     * @param action the model of the action to perform
     * @param request the client-supplied request object
     * @param token the pagination token for the action
     * @param extractor a result extractor object
     * @return the result of the action
     */
    public static ActionResult perform(
            ActionContext context,
            ActionModel action,
            AmazonWebServiceRequest request,
            ResultCapture<Object> extractor,
            Object token) {

        try {

            Method method = findClientMethod(
                    context.getClient(), action.getRequest().getMethod());

            request = generateRequest(
                    context,
                    method.getParameterTypes()[0],
                    action.getRequest(),
                    request,
                    token);

            Object clientResult = method.invoke(context.getClient(), request);

            if (extractor != null) {
                Map<String, String> responseMetadata =
                        getResponseMetadata(context, request);

                extractor.setResponseMetadata(responseMetadata);
                extractor.setClientResult(clientResult);
            }

            ResponseModel responseModel = action.getResponse();

            Object data = null;
            List<ResourceImpl> resources = null;
            Object nextToken = null;

            if (responseModel != null) {
                if (responseModel.getDataMapping() != null) {
                    data = getResultAttributes(
                        responseModel.getDataMapping().getSource(),
                        clientResult);

                } else if (responseModel.getResourceMapping() != null) {
                    resources = getResultResources(
                        context,
                        responseModel.getResourceMapping(),
                        request,
                        clientResult);

                } else {
                    data = clientResult;
                }

                if (responseModel.getNextTokenPath() != null) {
                    nextToken = ReflectionUtils.getByPath(
                        clientResult, responseModel.getNextTokenPath());
                }
            }

            return new ActionResult(data, resources, nextToken);

        } catch (IllegalAccessException | InstantiationException exception) {
            throw new IllegalStateException("BOOM", exception);

        } catch (InvocationTargetException exception) {
            if (exception.getCause() instanceof RuntimeException) {
                throw (RuntimeException) exception.getCause();
            }
            throw new IllegalStateException("BOOM", exception);
        }
    }

    /**
     * Generates a client-level request by extracting the user parameters (if
     */
    private static AmazonWebServiceRequest generateRequest(
            ActionContext context,
            Class<?> type,
            RequestModel model,
            AmazonWebServiceRequest request,
            Object token)
                    throws InstantiationException, IllegalAccessException {

        if (request == null) {
            request = (AmazonWebServiceRequest) type.newInstance();
        }

        for (PathTargetMapping mapping : model.getIdentifierMappings()) {
            Object value = context.getIdentifier(mapping.getSource());
            if (value == null) {
                throw new IllegalStateException(
                        "Action has a mapping for identifier "
                        + mapping.getSource() + ", but the target has no "
                        + "identifier of that name!");
            }
            ReflectionUtils.setByPath(request, value, mapping.getTarget());
        }

        for (PathTargetMapping mapping : model.getAttributeMappings()) {
            Object value = context.getAttribute(mapping.getSource());
            if (value == null) {
                // TODO: Is this ever valid?
                throw new IllegalStateException(
                        "Action has a mapping for attribute "
                        + mapping.getSource() + ", but the target has no "
                        + "attribute of that name!");
            }
            ReflectionUtils.setByPath(request, value, mapping.getTarget());
        }

        for (PathTargetMapping mapping : model.getConstantMappings()) {
            // TODO: This only works for strings; can we do better?
            ReflectionUtils.setByPath(
                    request, mapping.getSource(), mapping.getTarget());
        }

        if (token != null) {
            List<String> tokenPath = model.getTokenPath();
            if (tokenPath == null) {
                throw new IllegalArgumentException(
                        "Cannot pass a token with a null token path");
            }

            ReflectionUtils.setByPath(request, token, tokenPath);
        }

        return request;
    }

    private static Object getResultAttributes(
            List<String> basePath,
            Object result) {

        boolean multivalued = basePath.contains("*");

        if (multivalued) {
            return Collections.unmodifiableList(
                    ReflectionUtils.getAllByPath(result, basePath));
        } else {
            return ReflectionUtils.getByPath(result, basePath);
        }
    }

    private static List<ResourceImpl> getResultResources(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request,
            Object result) {

        List<Map<String, Object>> identifiers =
                extractIdentifiers(context, mapping, request, result);

        List<Object> data = null;
        if (mapping.getPath() != null) {
            data = ReflectionUtils.getAllByPath(result, mapping.getPath());
        }

        ResourceModel refTypeModel =
                context.getServiceModel().getResource(mapping.getType());

        List<ResourceImpl> rval = new ArrayList<>(identifiers.size());

        for (int i = 0; i < identifiers.size(); ++i) {
            Object attributes = null;
            if (data != null) {
                attributes = data.get(i);
            }

            rval.add(new ResourceImpl(
                    context.getServiceModel(),
                    refTypeModel,
                    context.getClient(),
                    identifiers.get(i),
                    attributes));
        }

        return Collections.unmodifiableList(rval);
    }

    private static List<Map<String, Object>> extractIdentifiers(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request,
            Object result) {

        // Identifier sourced from the parent's resource id can only have one
        // value.
        Map<String, Object> ids =
                extractParentIdentifiers(context, mapping, request);

        // Identifier extracted from the request or response data could be
        // multi-valued.
        Map<String, List<Object>> multiIds =
                extractMultiValuedIdentifiers(mapping, request, result);

        if (multiIds.isEmpty()) {
            return Collections.singletonList(ids);
        }

        List<Map<String, Object>> rval = new ArrayList<>();

        for (Map.Entry<String, List<Object>> entry : multiIds.entrySet()) {
            String key = entry.getKey();
            List<Object> values = entry.getValue();

            for (int i = 0; i < values.size(); ++i) {
                if (rval.size() == i) {
                    rval.add(new HashMap<String, Object>());
                }
                rval.get(i).put(key, values.get(i));
            }
        }

        for (Map.Entry<String, Object> entry : ids.entrySet()) {
            for (Map<String, Object> map : rval) {
                map.put(entry.getKey(), entry.getValue());
            }
        }

        return rval;
    }

    private static Map<String, Object> extractParentIdentifiers(
            ActionContext context,
            ResourceMapping mapping,
            AmazonWebServiceRequest request) {

        Map<String, Object> ids = new HashMap<>();

        for (FlatMapping m : mapping.getParentIdentifierMappings()) {
            Object value = context.getIdentifier(m.getSource());
            if (value == null) {
                throw new IllegalStateException(
                        "This action metadata has a mapping "
                        + "for the " + m.getSource() + " identifier, but "
                        + "this resource doesn't have an identifier of that "
                        + "name!");
            }
            ids.put(m.getTarget(), value);
        }

        return ids;
    }

    private static Map<String, List<Object>> extractMultiValuedIdentifiers(
            ResourceMapping mapping,
            AmazonWebServiceRequest request,
            Object result) {

        Map<String, List<Object>> ids = new HashMap<>();

        int listSize = -1;

        for (PathSourceMapping m : mapping.getResponseIdentifierMappings()) {
            List<Object> values =
                    ReflectionUtils.getAllByPath(result, m.getSource());

            if (listSize == -1) {
                listSize = values.size();
            } else if (values.size() != listSize) {
                throw new IllegalStateException(
                        "List size mismatch! " + listSize + " vs "
                        + values.size());
            }

            ids.put(m.getTarget(), values);
        }

        for (PathSourceMapping m : mapping.getRequestParamMappings()) {
            List<Object> values;

            /*
             * When the response contains multiple resources, the source of a
             * request param mapping could be either single-valued (e.g. in
             * Glacier.getVaults() action, the single-valued "AccountId" param
             * is mapped to the "AccountId"s of all the returned vaults), or
             * multi-valued (e.g. in EC2.Instance.createTags() action, multiple
             * "Tag[].Key" parameters are mapped to the "Key"s of all the
             * returned Tag resources.
             */
            if (m.isMultiValued()) {
                values =
                        ReflectionUtils.getAllByPath(request, m.getSource());

                if (listSize == -1) {
                    listSize = values.size();
                } else if (values.size() != listSize) {
                    throw new IllegalStateException(
                            "List size mismatch! " + listSize + " vs "
                            + values.size());
                }
            }
            else {
                // If single valued, augment the value into a list of ids, which
                // match the length of the ids extracted from response.
                Object singleValue =
                        ReflectionUtils.getByPath(request, m.getSource());

                values = new ArrayList<Object>(listSize);
                for (int i = 0; i < listSize; i++ ) {
                    values.add(singleValue);
                }
            }

            ids.put(m.getTarget(), values);
        }

        return ids;
    }

    private static Map<String, String> getResponseMetadata(
            ActionContext context,
            Object parameter)
                    throws IllegalAccessException, InvocationTargetException {

        Method method = tryFindClientMethod(
                context.getClient(), "getCachedResponseMetadata");
        if (method == null) {
            return Collections.<String, String>emptyMap();
        }

        Object result = method.invoke(context.getClient(), parameter);
        if (result == null) {
            return Collections.<String, String>emptyMap();
        }

        Map<String, String> metadata = new HashMap<>();

        for (Method getter : result.getClass().getMethods()) {
            String name = getter.getName();
            if (name.startsWith("get")
                    && getter.getParameterTypes().length == 0
                    && getter.getReturnType().equals(String.class)) {

                String value = (String) getter.invoke(result);
                metadata.put(name.substring(3), value);
            }
        }

        return Collections.unmodifiableMap(metadata);
    }

    private static Method findClientMethod(Object client, String name) {
        Method result = tryFindClientMethod(client, name);

        if (result == null) {
            throw new RuntimeException("No client method named " + name);
        }

        return result;
    }

    private static Method tryFindClientMethod(Object client, String name) {
        // TODO: Cache me.
        for (Method method : client.getClass().getMethods()) {
            if (!method.getName().equals(name)) {
                continue;
            }

            Class<?>[] parameters = method.getParameterTypes();
            if (parameters.length != 1) {
                continue;
            }

            // This is the inverse of the normal approach of findMethod() -
            // we're looking for a method which will accept a specific subtype
            // of AmazonWebServiceRequest, without worrying overmuch about
            // what subtype it is. We'll create an object of the appropriate
            // type and fill it in.
            if (AmazonWebServiceRequest.class.isAssignableFrom(parameters[0])) {
                return method;
            }
        }

        return null;
    }

    private ActionUtils() {
    }
}
