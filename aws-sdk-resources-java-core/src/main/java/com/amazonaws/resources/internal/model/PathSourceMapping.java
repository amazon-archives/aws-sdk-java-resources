package com.amazonaws.resources.internal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mapping whose source is a path.
 */
public class PathSourceMapping {

    private final List<String> source;
    private final String target;

    @JsonCreator
    public PathSourceMapping(
            @JsonProperty(value="Source", required=true) List<String> source,
            @JsonProperty(value="Target", required=true) String target) {

        this.source = Utils.makeImmutable(source);
        this.target = target;
    }

    @JsonProperty(value="Source")
    public List<String> getSource() {
        return source;
    }

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
