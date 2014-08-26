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
package com.amazonaws.resources.internal.model.builders;

import java.util.List;

import com.amazonaws.resources.internal.model.DataMapping;
import com.amazonaws.resources.internal.model.ResourceMapping;
import com.amazonaws.resources.internal.model.ResponseModel;

public class ResponseModelBuilder implements Builder<ResponseModel> {

    private ResourceMapping resourceMapping;
    private DataMapping dataMapping;
    private List<String> nextTokenPath;

    public ResourceMapping getResourceMapping() {
        return resourceMapping;
    }

    public void setResourceMapping(ResourceMapping resourceMapping) {
        this.resourceMapping = resourceMapping;
    }

    public DataMapping getDataMapping() {
        return dataMapping;
    }

    public void setDataMapping(DataMapping dataMapping) {
        this.dataMapping = dataMapping;
    }

    public List<String> getNextTokenPath() {
        return nextTokenPath;
    }

    public void setNextTokenPath(List<String> nextTokenPath) {
        this.nextTokenPath = nextTokenPath;
    }

    @Override
    public ResponseModel build() {
        return new ResponseModel(resourceMapping, dataMapping, nextTokenPath);
    }
}
