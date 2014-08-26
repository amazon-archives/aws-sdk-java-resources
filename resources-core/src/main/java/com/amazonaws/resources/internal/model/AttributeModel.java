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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model definition of an attribute of a resource. An attribute of a
 * resource is non-identifier data about the resource which can be read by
 * loading the resource.
 */
public class AttributeModel {

    private final String type;

    @JsonCreator
    public AttributeModel(
            @JsonProperty(value="Type", required=true) String type) {

        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        this.type = type;
    }

    /**
     * @return the type of this identifier
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "{type=" + type + "}";
    }
}
