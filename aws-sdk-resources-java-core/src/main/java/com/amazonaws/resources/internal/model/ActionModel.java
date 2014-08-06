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
