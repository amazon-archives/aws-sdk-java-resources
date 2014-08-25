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
 * The model definition for an entire service.
 */
public class ServiceModel {

    private final String name;
    private final String abbreviation;

    private final String clientInterface;
    private final String clientImplementation;

    private final Map<String, ResourceModel> resources;

    private final Map<String, SubResourceGetterModel> subResourceGetters;
    private final Map<String, CollectionModel> collections;
    private final Map<String, ActionModel> actions;

    @JsonCreator
    public ServiceModel(
            @JsonProperty(value="Name", required=true)
            String name,
            @JsonProperty(value="Abbreviation", required=true)
            String abbreviation,
            @JsonProperty(value="ClientInterface", required=true)
            String clientInterface,
            @JsonProperty(value="ClientImplementation", required=true)
            String clientImplementation,
            @JsonProperty(value="Resources", required=false)
            Map<String, ResourceModel> resources,
            @JsonProperty(value="SubResourceGetters", required=false)
            Map<String, SubResourceGetterModel> subResourceGetters,
            @JsonProperty(value="Collections", required=false)
            Map<String, CollectionModel> collections,
            @JsonProperty(value="Actions", required=false)
            Map<String, ActionModel> actions) {

        this.name = name;
        this.abbreviation = abbreviation;
        this.clientInterface = clientInterface;
        this.clientImplementation = clientImplementation;
        this.resources = Utils.makeImmutable(resources);
        this.subResourceGetters = Utils.makeImmutable(subResourceGetters);
        this.collections = Utils.makeImmutable(collections);
        this.actions = Utils.makeImmutable(actions);
    }

    /**
     * @return the full name of this service, suitable for use as the name of
     *         the generated service interface; for example
     *         "AmazonEC2Service"
     */
    @JsonProperty(value="Name")
    public String getName() {
        return name;
    }

    /**
     * @return the abbreviation for this service, suitable for use as the Java
     *         package name where this service's generated request and result
     *         classes will live; for example "ec2"
     */
    @JsonProperty(value="Abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    @JsonProperty(value="ClientInterface")
    public String getClientInterface() {
        return clientInterface;
    }

    /**
     * @return the fully-qualified name of the type of low-level client
     *         object to create if the user does not explicitly provide one;
     *         for example "com.amazonaws.services.ec2.AmazonEC2Client"
     */
    @JsonProperty(value="ClientImplementation")
    public String getClientImplementation() {
        return clientImplementation;
    }

    /**
     * @return the full set of resource types exposed by this service
     */
    @JsonProperty(value="Resources")
    public Map<String, ResourceModel> getResources() {
        return resources;
    }

    /**
     * @param name the name of a resource type
     * @return information about the type of resource
     */
    public ResourceModel getResource(String name) {
        return resources.get(name);
    }

    @JsonProperty(value="SubResourceGetters")
    public Map<String, SubResourceGetterModel> getSubResourceGetters() {
        return subResourceGetters;
    }

    public SubResourceGetterModel getSubResourceGetter(String name) {
        return subResourceGetters.get(name);
    }

    /**
     * @return the full set of collection types exposed by this service
     */
    @JsonProperty(value="Collections")
    public Map<String, CollectionModel> getCollections() {
        return collections;
    }

    /**
     * @param name the name of a collection type
     * @return information about the type of collection
     */
    public CollectionModel getCollection(String name) {
        return collections.get(name);
    }

    @JsonProperty(value="Actions")
    public Map<String, ActionModel> getActions() {
        return actions;
    }

    public ActionModel getAction(String name) {
        return actions.get(name);
    }

    @Override
    public String toString() {
        return "{name=" + name
                + ", abbreviation=" + abbreviation
                + ", clientInterface=" + clientInterface
                + ", clientImplementation=" + clientImplementation
                + ", resources=" + resources
                + ", subResourceGetters=" + subResourceGetters
                + ", collections=" + collections
                + ", actions=" + actions
                + "}";
    }
}
