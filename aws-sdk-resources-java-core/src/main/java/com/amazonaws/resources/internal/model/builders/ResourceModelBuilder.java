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
package com.amazonaws.resources.internal.model.builders;

import java.util.Map;

import com.amazonaws.resources.internal.model.ActionModel;
import com.amazonaws.resources.internal.model.AttributeModel;
import com.amazonaws.resources.internal.model.CollectionModel;
import com.amazonaws.resources.internal.model.IdentifierModel;
import com.amazonaws.resources.internal.model.ReferenceModel;
import com.amazonaws.resources.internal.model.ResourceModel;
import com.amazonaws.resources.internal.model.SubResourceGetterModel;

public class ResourceModelBuilder implements Builder<ResourceModel> {

    private Map<String, IdentifierModel> identifiers;
    private Map<String, AttributeModel> attributes;
    private Map<String, ReferenceModel> references;
    private Map<String, SubResourceGetterModel> subResourceGetters;
    private Map<String, CollectionModel> collections;
    private ActionModel loadAction;

    public Map<String, SubResourceGetterModel> getSubResourceGetters() {
        return subResourceGetters;
    }

    public void setSubResourceGetters(
            Map<String, SubResourceGetterModel> subResourceGetters) {
        this.subResourceGetters = subResourceGetters;
    }

    public Map<String, CollectionModel> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, CollectionModel> collections) {
        this.collections = collections;
    }

    private Map<String, ActionModel> actions;

    public void setIdentifiers(Map<String, IdentifierModel> identifiers) {
        this.identifiers = identifiers;
    }

    public void setAttributes(Map<String, AttributeModel> attributes) {
        this.attributes = attributes;
    }

    public void setReferences(Map<String, ReferenceModel> references) {
        this.references = references;
    }

    public void setLoadAction(ActionModel loadAction) {
        this.loadAction = loadAction;
    }

    public void setActions(Map<String, ActionModel> actions) {
        this.actions = actions;
    }

    /**
     * @return the full set of identifiers for this resource type
     */
    public Map<String, IdentifierModel> getIdentifiers() {
        return identifiers;
    }

    /**
     * @param name
     *            the name of an identifier
     * @return information about the given identifier
     */
    public IdentifierModel getIdentifier(String name) {
        return identifiers.get(name);
    }

    /**
     * @return the full set of attributes for this resource type
     */
    public Map<String, AttributeModel> getAttributes() {
        return attributes;
    }

    /**
     * @param name
     *            the name of an attribute
     * @return information about the given attribute
     */
    public AttributeModel getAttribute(String name) {
        return attributes.get(name);
    }

    /**
     * @return the full set of references for this resource type
     */
    public Map<String, ReferenceModel> getReferences() {
        return references;
    }

    /**
     * @param name
     *            the name of a reference
     * @return information about the given reference
     */
    public ReferenceModel getReference(String name) {
        return references.get(name);
    }

    /**
     * @return information about how to load this resource
     */
    public ActionModel getLoadAction() {
        return loadAction;
    }

    /**
     * @return the full set of actions on this resource type
     */
    public Map<String, ActionModel> getActions() {
        return actions;
    }

    /**
     * @param name
     *            the name of an action on this resource
     * @return information about the given action
     */
    public ActionModel getAction(String name) {
        return actions.get(name);
    }

    @Override
    public String toString() {
        return "{identifiers=" + identifiers + ", attributes=" + attributes
                + ", references=" + references + ", loadAction=" + loadAction
                + ", actions=" + actions + "}";
    }

    @Override
    public ResourceModel build() {

        return new ResourceModel(identifiers, attributes, references,
                subResourceGetters, collections, loadAction, actions);
    }

}
