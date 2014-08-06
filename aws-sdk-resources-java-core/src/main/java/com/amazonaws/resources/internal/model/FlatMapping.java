package com.amazonaws.resources.internal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mapping between two strings, for example between the name of an identifier
 * in one resource type and the name of the corresponding identifier in a
 * different resource type.
 */
public class FlatMapping {

    private final String source;
    private final String target;

    @JsonCreator
    public FlatMapping(
            @JsonProperty(value="Source", required=true) String source,
            @JsonProperty(value="Target", required=true) String target) {

        this.source = source;
        this.target = target;
    }

    /**
     * @return the source of this mapping
     */
    @JsonProperty(value="Source")
    public String getSource() {
        return source;
    }

    /**
     * @return the target of this mapping
     */
    @JsonProperty(value="Target")
    public String getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "{source=" + source
                + ", target=" + target
                + "}";
    }
}
