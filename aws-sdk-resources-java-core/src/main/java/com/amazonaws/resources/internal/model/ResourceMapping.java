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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mapping between a portion of a client-level response and a resource
 * object (or list of resource objects).
 */
public class ResourceMapping {

    private final String type;
    private final List<FlatMapping> parentIdentifierMappings;
    private final List<PathSourceMapping> requestParamMappings;
    private final List<PathSourceMapping> responseIdentifierMappings;
    private final List<String> path;

    @JsonCreator
    public ResourceMapping(
            @JsonProperty(value="Type", required=true)
            String type,
            @JsonProperty(value="ParentIdentifierMappings", required=false)
            List<FlatMapping> parentIdentifierMappings,
            @JsonProperty(value="RequestParamMappings", required=false)
            List<PathSourceMapping> requestParamMappings,
            @JsonProperty(value="ResponseIdentifierMappings", required=false)
            List<PathSourceMapping> responseIdentifierMappings,
            @JsonProperty(value="Path", required=false)
            List<String> path) {

        this.type = type;
        this.parentIdentifierMappings =
                Utils.makeImmutable(parentIdentifierMappings);
        this.requestParamMappings =
                Utils.makeImmutable(requestParamMappings);
        this.responseIdentifierMappings =
                Utils.makeImmutable(responseIdentifierMappings);
        this.path = Utils.makeImmutableOrNull(path);
    }

    /**
     * @return the type of resource to extract
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    /**
     * @return true if this mapping may extract multiple resources from the
     *         response
     */
    @JsonIgnore
    public boolean isMultiValued() {

        // If we've got a data path, just check if that is multi-valued.
        if (path != null) {
            return isMultiValued(path);
        }

        // Otherwise check whether any of the response identifier mappings
        // are multi-valued.
        for (PathSourceMapping mapping : responseIdentifierMappings) {
            if (isMultiValued(mapping.getSource())) {
                return true;
            }
        }

        return false;
    }

    private static boolean isMultiValued(List<String> expression) {
        for (String element : expression) {
            if ("*".equals(element) || element.startsWith("*:")) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return mappings from identifiers of the parent resource to identifiers
     *         of the extracted resource(s)
     */
    @JsonProperty(value="ParentIdentifierMappings")
    public List<FlatMapping> getParentIdentifierMappings() {
        return parentIdentifierMappings;
    }

    /**
     * @return mappings from request parameters to identifiers of the extracted
     *         resource (mainly for create-by-name)
     */
    @JsonProperty(value="RequestParamMappings")
    public List<PathSourceMapping> getRequestParamMappings() {
        return requestParamMappings;
    }

    /**
     * @return mappings from the portion of the response identified by the
     *         base path to identifiers of the extracted resource
     */
    @JsonProperty(value="ResponseIdentifierMappings")
    public List<PathSourceMapping> getResponseIdentifierMappings() {
        return responseIdentifierMappings;
    }

    /**
     * @return an optional path to an object containing the attributes of the
     *         extracted resource(s)
     */
    @JsonProperty(value="Path")
    public List<String> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "{type=" + type
                + ", parentIdentifierMappings=" + parentIdentifierMappings
                + ", requestParamMappings=" + requestParamMappings
                + ", responseIdentifierMappings=" + responseIdentifierMappings
                + ", path=" + path
                + "}";
    }
}
