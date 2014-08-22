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
package com.amazonaws.resources.ec2.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.PlacementGroup;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.DeletePlacementGroupRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsRequest;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsResult;

class PlacementGroupImpl implements PlacementGroup {
    public static final ResourceCodec<PlacementGroup> CODEC = new Codec();

    private final ResourceImpl resource;

    public PlacementGroupImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public boolean load() {
        return load(null, null);
    }

    @Override
    public boolean load(DescribePlacementGroupsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribePlacementGroupsRequest request,
            ResultCapture<DescribePlacementGroupsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public String getStrategy() {
        return (String) resource.getAttribute("Strategy");
    }

    @Override
    public InstanceCollection getInstances() {
        return getInstances(null);
    }

    @Override
    public InstanceCollection getInstances(DescribeInstancesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Instances",
                request);

        if (result == null) return null;
        return new InstanceCollectionImpl(result);
    }

    @Override
    public void delete(DeletePlacementGroupRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeletePlacementGroupRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeletePlacementGroupRequest request = new DeletePlacementGroupRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<PlacementGroup> {
        @Override
        public PlacementGroup transform(ResourceImpl resource) {
            return new PlacementGroupImpl(resource);
        }
    }
}
