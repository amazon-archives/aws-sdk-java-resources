package com.amazonaws.resources.internal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model definition of an identifier of a resource. The set of identifiers
 * serve as the "primary key" for a resource, uniquely identifying it and
 * serving as input parameters to any operation that affects the resource.
 */
public class IdentifierModel {

    private final String type;

    @JsonCreator
    public IdentifierModel(
            @JsonProperty(value="Type", required=true) String type) {

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
