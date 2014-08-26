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
package com.amazonaws.resources.internal.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model definition of a type of resource object.
 */
public class ResourceModel {

    private final Map<String, IdentifierModel> identifiers;
    private final Map<String, AttributeModel> attributes;
    private final Map<String, ReferenceModel> references;
    private final Map<String, SubResourceGetterModel> subResourceGetters;
    private final Map<String, CollectionModel> collections;
    private final ActionModel loadAction;
    private final Map<String, ActionModel> actions;

    // Other actions.

    @JsonCreator
    public ResourceModel(
            @JsonProperty(value="Identifiers", required=true)
            Map<String, IdentifierModel> identifiers,
            @JsonProperty(value="Attributes", required=false)
            Map<String, AttributeModel> attributes,
            @JsonProperty(value="References", required=false)
            Map<String, ReferenceModel> references,
            @JsonProperty(value="SubResourceGetters", required=false)
            Map<String, SubResourceGetterModel> subResourceGetters,
            @JsonProperty(value="Collections", required=false)
            Map<String, CollectionModel> collections,
            @JsonProperty(value="LoadAction", required=false)
            ActionModel loadAction,
            @JsonProperty(value="Actions", required=false)
            Map<String, ActionModel> actions) {

        this.identifiers = Utils.makeImmutable(identifiers);
        this.attributes = Utils.makeImmutable(attributes);
        this.references = Utils.makeImmutable(references);
        this.subResourceGetters = Utils.makeImmutable(subResourceGetters);
        this.collections = Utils.makeImmutable(collections);
        this.loadAction = loadAction;
        this.actions = Utils.makeImmutable(actions);
    }

    /**
     * @return the full set of identifiers for this resource type
     */
    @JsonProperty(value="Identifiers")
    public Map<String, IdentifierModel> getIdentifiers() {
        return identifiers;
    }

    /**
     * @param name the name of an identifier
     * @return information about the given identifier
     */
    public IdentifierModel getIdentifier(String name) {
        return identifiers.get(name);
    }

    /**
     * @return the full set of attributes for this resource type
     */
    @JsonProperty(value="Attributes")
    public Map<String, AttributeModel> getAttributes() {
        return attributes;
    }

    /**
     * @param name the name of an attribute
     * @return information about the given attribute
     */
    public AttributeModel getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * @return the full set of references for this resource type
     */
    @JsonProperty(value="References")
    public Map<String, ReferenceModel> getReferences() {
        return references;
    }

    /**
     * @param name the name of a reference
     * @return information about the given reference
     */
    public ReferenceModel getReference(String name) {
        return references.get(name);
    }

    /**
     * @return a map from subresource name to subresource getter information
     */
    @JsonProperty(value="SubResourceGetters")
    public Map<String, SubResourceGetterModel> getSubResourceGetters() {
        return subResourceGetters;
    }

    /**
     * @param name the name of a subresource type
     * @return information about the subresource getter
     */
    public SubResourceGetterModel getSubResourceGetter(String name) {
        return subResourceGetters.get(name);
    }

    /**
     * @return a map from collection name to collection information
     */
    @JsonProperty(value="Collections")
    public Map<String, CollectionModel> getCollections() {
        return collections;
    }

    /**
     * @param name the name of the collection reference
     * @return information about the collection reference
     */
    public CollectionModel getCollection(String name) {
        return collections.get(name);
    }

    /**
     * @return information about how to load this resource
     */
    @JsonProperty(value="LoadAction")
    public ActionModel getLoadAction() {
        return loadAction;
    }

    /**
     * @return the full set of actions on this resource type
     */
    @JsonProperty(value="Actions")
    public Map<String, ActionModel> getActions() {
        return actions;
    }

    /**
     * @param name the name of an action on this resource
     * @return information about the given action
     */
    public ActionModel getAction(String name) {
        return actions.get(name);
    }

    @Override
    public String toString() {
        return "{identifiers=" + identifiers
                + ", attributes=" + attributes
                + ", references=" + references
                + ", subresourceGetters=" + subResourceGetters
                + ", loadAction=" + loadAction
                + ", actions=" + actions
                + "}";
    }
}
