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
 * The model definition of a type of collection object.
 */
public class CollectionModel {

    private final String type;
    private final ActionModel listAction;

    @JsonCreator
    public CollectionModel(
            @JsonProperty(value="Type", required=true)
            String type,
            @JsonProperty(value="ListAction", required=false)
            ActionModel listAction) {

        this.type = type;
        this.listAction = listAction;
    }

    /**
     * @return the type of resource contained in this collection
     */
    @JsonProperty(value="Type")
    public String getType() {
        return type;
    }

    /**
     * @return a description of how to list the elements of this collection
     */
    @JsonProperty(value="ListAction")
    public ActionModel getListAction() {
        return listAction;
    }

    @Override
    public String toString() {
        return "{type=" + type + ", listAction=" + listAction + "}";
    }
}
