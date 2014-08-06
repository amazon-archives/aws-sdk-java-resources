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
