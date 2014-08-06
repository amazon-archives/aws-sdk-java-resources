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
