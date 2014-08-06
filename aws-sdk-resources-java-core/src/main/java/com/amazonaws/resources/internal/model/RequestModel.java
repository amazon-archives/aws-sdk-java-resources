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
 * The model definition of a request. A request describes how to map an action
 * on a resource to a request to be made using the underlying client.
 */
public class RequestModel {

    private final String method;
    private final String clientRequestType;

    private final List<PathTargetMapping> identifierMappings;
    private final List<PathTargetMapping> attributeMappings;
    private final List<ParameterModel> parameterMappings;
    private final List<PathTargetMapping> constantMappings;
    private final List<String> tokenPath;
    private final List<String> limitPath;

    @JsonCreator
    public RequestModel(
            @JsonProperty(value="Method", required=true)
            String method,
            @JsonProperty(value="ClientRequestType", required=true)
            String clientRequestType,
            @JsonProperty(value="IdentifierMappings", required=false)
            List<PathTargetMapping> identifierMappings,
            @JsonProperty(value="AttributeMappings", required=false)
            List<PathTargetMapping> attributeMappings,
            @JsonProperty(value="ParameterMappings", required=false)
            List<ParameterModel> parameterMappings,
            @JsonProperty(value="ConstantMappings", required=false)
            List<PathTargetMapping> constantMappings,
            @JsonProperty(value="TokenPath", required=false)
            List<String> tokenPath,
            @JsonProperty(value="LimitPath", required=false)
            List<String> limitPath) {

        this.method = method;
        this.clientRequestType = clientRequestType;
        this.identifierMappings = Utils.makeImmutable(identifierMappings);
        this.attributeMappings = Utils.makeImmutable(attributeMappings);
        this.parameterMappings = Utils.makeImmutable(parameterMappings);
        this.constantMappings = Utils.makeImmutable(constantMappings);
        this.tokenPath = Utils.makeImmutableOrNull(tokenPath);
        this.limitPath = Utils.makeImmutableOrNull(limitPath);
    }

    /**
     * @return the name of the method to call on the underlying client
     */
    @JsonProperty(value="Method")
    public String getMethod() {
        return method;
    }

    /**
     * @return the type of the corresponding client request object
     */
    @JsonProperty(value="ClientRequestType")
    public String getClientRequestType() {
        return clientRequestType;
    }

    /**
     * @return mappings from the identifiers of the resource the action is
     *         being called on to the client-level request object
     */
    @JsonProperty(value="IdentifierMappings")
    public List<PathTargetMapping> getIdentifierMappings() {
        return identifierMappings;
    }

    /**
     * @return mappings from the attributes of the resource the action is
     *         being called on to the client-level request object
     */
    @JsonProperty(value="AttributeMappings")
    public List<PathTargetMapping> getAttributeMappings() {
        return attributeMappings;
    }

    /**
     * @return mappings from attributes of the resource-level request object on
     *         to the client-level request object
     */
    @JsonProperty(value="ParameterMappings")
    public List<ParameterModel> getParameterMappings() {
        return parameterMappings;
    }

    /**
     * @return mappings of constant values on to the client-level request
     *         object
     */
    @JsonProperty(value="ConstantMappings")
    public List<PathTargetMapping> getConstantMappings() {
        return constantMappings;
    }

    /**
     * @return a path to the place to inject the 'next page' token in the
     *         client-level request when enumerating a collection
     */
    @JsonProperty(value="TokenPath")
    public List<String> getTokenPath() {
        return tokenPath;
    }

    /**
     * @return a path to the place to inject the 'limit' parameter in the
     *         client-level request when enumerating a collection
     */
    @JsonProperty(value="LimitPath")
    public List<String> getLimitPath() {
        return limitPath;
    }

    @Override
    public String toString() {
        return "{method=" + method
                + ", identifierMappings=" + identifierMappings
                + ", attributeMappings=" + attributeMappings
                + ", parameterMappings=" + parameterMappings
                + ", constantMappings=" + constantMappings
                + ", tokenPath=" + tokenPath
                + ", limitPath=" + limitPath
                + "}";
    }
}
