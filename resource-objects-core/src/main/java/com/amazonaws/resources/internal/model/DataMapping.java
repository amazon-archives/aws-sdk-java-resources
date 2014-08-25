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

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mapping from elements of a raw client-level response to a data object
 * to return.
 */
public class DataMapping {

    private final String type;
    private final List<String> source;

    public DataMapping(
            @JsonProperty(value="Type", required=true) String type,
            @JsonProperty(value="Source", required=true) List<String> source) {

        this.type = type;
        this.source = Collections.unmodifiableList(source);
    }

    /**
     * @return the type of this attribute
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    /**
     * @return the source of this mapping - a path into the client-level
     *         response object
     */
    @JsonProperty(value="Source")
    public List<String> getSource() {
        return source;
    }


    @Override
    public String toString() {
        return "{type=" + type
                + ", source=" + source
                + "}";
    }
}
