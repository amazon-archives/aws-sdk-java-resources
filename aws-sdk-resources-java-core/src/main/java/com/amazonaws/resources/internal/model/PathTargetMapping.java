package com.amazonaws.resources.internal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A mapping with a path for its target.
 */
public class PathTargetMapping {

    private final String source;
    private final List<String> target;

    public PathTargetMapping(
            @JsonProperty(value="Source", required=true) String source,
            @JsonProperty(value="Target", required=true) List<String> target) {

        this.source = source;
        this.target = Utils.makeImmutable(target);
    }

    @JsonProperty(value="Source")
    public String getSource() {
        return source;
    }

    @JsonProperty(value="Target")
    public List<String> getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "{source=" + source
                + ", target=" + target
                + "}";
    }
}
