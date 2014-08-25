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
 * A mapping from an attribute of a resource-level request object to location
 * within the corresponding client-level request object. Retains the type of
 * the parameter as well.
 */
public class ParameterModel {

    private final String type;
    private final String name;

    @JsonCreator
    public ParameterModel(
            @JsonProperty(value="Type", required=true) String type,
            @JsonProperty(value="Name", required=true) String name) {

        this.type = type;
        this.name = name;
    }

    /**
     * @return the type of this parameter
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    /**
     * @return the name of this parameter
     */
    @JsonProperty(value="Name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{type=" + type
                + ", name=" + name
                +"}";
    }
}
