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
