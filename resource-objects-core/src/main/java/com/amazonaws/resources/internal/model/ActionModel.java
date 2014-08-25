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
 * The model definition of an action which can be performed on a resource.
 */
public class ActionModel {

    private final RequestModel request;
    private final ResponseModel response;

    @JsonCreator
    public ActionModel(
            @JsonProperty(value="Request", required=true)
            RequestModel request,
            @JsonProperty(value="Response", required=false)
            ResponseModel response) {

        this.request = request;
        this.response = response;
    }

    /**
     * @return the request model
     */
    @JsonProperty(value="Request")
    public RequestModel getRequest() {
        return request;
    }

    /**
     * @return the response model
     */
    @JsonProperty(value="Response")
    public ResponseModel getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "{request=" + request
                + ", response=" + response
                + "}";
    }
}
