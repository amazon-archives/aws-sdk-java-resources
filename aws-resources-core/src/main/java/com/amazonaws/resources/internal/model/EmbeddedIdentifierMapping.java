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
 * A mapping between the name of an attribute of one resource type and the name
 * of an identifier of a referenced resource type. It expands on the basic
 * FlatMapping by optionally being able to 'unpack' a source List and map
 * each element of the list separately as the identifier of a list of
 * resource types.
 * <p/>
 * For example, the SecurityGroups attribute of an EC2 Instance resource is
 * a list of Strings containing the names of any security groups to which
 * the instance belongs. We want to expose this as a link to a list of
 * SecurityGroup resources, which requires mapping each element of the
 * SecurityGroups list to the Name identifier of a different SecurityGroup
 * instance.
 */
public class EmbeddedIdentifierMapping {

    private final String source;
    private final String target;
    private final boolean multiValued;

    @JsonCreator
    public EmbeddedIdentifierMapping(
            @JsonProperty(value="Source", required=true)
            String source,
            @JsonProperty(value="Target", required=true)
            String target,
            @JsonProperty(value="MultiValued", required=false)
            Boolean multiValued) {

        this.source = source;
        this.target = target;
        this.multiValued = (multiValued == null) ? false : multiValued;
    }

    /**
     * @return the source of this mapping - the name of an attribute of the
     *         referencing resource
     */
    @JsonProperty(value="Source")
    public String getSource() {
        return source;
    }

    /**
     * @return the target of this mapping - the name of an identifier of the
     *         referenced resource
     */
    @JsonProperty(value="Target")
    public String getTarget() {
        return target;
    }

    /**
     * @return true if the source of this mapping is multi-valued and needs to
     *         be unpacked into multiple resource objects
     */
    public boolean isMultiValued() {
        return multiValued;
    }

    @Override
    public String toString() {
        return "{source=" + source
                + ", target=" + target
                + ", multiValued=" + multiValued
                + "}";
    }
}
