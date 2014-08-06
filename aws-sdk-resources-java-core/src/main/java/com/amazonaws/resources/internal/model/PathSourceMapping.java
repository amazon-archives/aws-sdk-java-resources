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
