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
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model definition of an action result. An action result describes how to
 * collect data (from either the low-level client response, or the request
 * parameters, or the parent resource object) to create the resource-level
 * result of an action.
 */
public class ResponseModel {

    private final ResourceMapping resourceMapping;
    private final DataMapping dataMapping;
    private final List<String> nextTokenPath;

    @JsonCreator
    public ResponseModel(
            @JsonProperty(value="ResourceMapping", required=false)
            ResourceMapping resourceMapping,
            @JsonProperty(value="DataMapping", required=false)
            DataMapping dataMapping,
            @JsonProperty(value="NextTokenPath", required=false)
            List<String> nextTokenPath) {

        this.resourceMapping = resourceMapping;
        this.dataMapping = dataMapping;
        this.nextTokenPath = Utils.makeImmutableOrNull(nextTokenPath);
    }

    /**
     * @return a mapping from the response to a resource or set of resources
     */
    @JsonProperty(value="ResourceMapping")
    public ResourceMapping getResourceMapping() {
        return resourceMapping;
    }

    /**
     * @return a mapping from the response to some data
     */
    @JsonProperty(value="DataMapping")
    public DataMapping getDataMapping() {
        return dataMapping;
    }

    /**
     * @return the path to the 'next page' token in the response when
     *         enumerating a collection
     */
    @JsonProperty(value="NextTokenPath")
    public List<String> getNextTokenPath() {
        return nextTokenPath;
    }

    @Override
    public String toString() {
        return "{resourceMapping=" + resourceMapping
                + ", dataMapping=" + dataMapping
                + ", nextTokenPath=" + nextTokenPath
                + "}";
    }
}
