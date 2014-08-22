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
 * The model definition of a reference from one resource to another. The
 * reference model describes how to construct a referenced resource (or set of
 * resources) based on the identifiers and/or attributes of the referencing
 * resource.
 */
public class ReferenceModel {

    private final String type;
    private final List<FlatMapping> identifierMappings;
    private final List<PathSourceMapping> attributeMappings;

    @JsonCreator
    public ReferenceModel(
            @JsonProperty(value="Type", required=true)
            String type,
            @JsonProperty(value="IdentifierMappings", required=false)
            List<FlatMapping> identifierMappings,
            @JsonProperty(value="AttributeMappings", required=false)
            List<PathSourceMapping> attributeMappings) {

        this.type = type;
        this.identifierMappings = Utils.makeImmutable(identifierMappings);
        this.attributeMappings = Utils.makeImmutable(attributeMappings);
    }

    /**
     * @return the type of resource being referenced
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    /**
     * @return a set of mappings from the identifiers of the referencing
     *         resource to the corresponding identifiers of the referenced
     *         resource
     */
    @JsonProperty(value="IdentifierMappings")
    public List<FlatMapping> getIdentifierMappings() {
        return identifierMappings;
    }

    /**
     * @return a set of mappings from the attributes of the referencing
     *         resource to the corresponding identifiers of the referenced
     *         resource
     */
    @JsonProperty(value="AttributeMappings")
    public List<PathSourceMapping> getAttributeMappings() {
        return attributeMappings;
    }

    /**
     * @return true if this reference is multi-valued
     */
    @JsonIgnore
    public boolean isMultiValued() {
        for (PathSourceMapping mapping : attributeMappings) {
            if (mapping.isMultiValued()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "{type=" + type
                + ", identifierMappings=" + identifierMappings
                + ", attributeMappings=" + attributeMappings
                + "}";
    }
}
