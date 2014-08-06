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
