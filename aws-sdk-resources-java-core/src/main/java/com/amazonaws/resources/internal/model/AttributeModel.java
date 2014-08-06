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
