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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.DeleteSubnetRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

class SubnetImpl implements Subnet {
    public static final ResourceCodec<Subnet> CODEC = new Codec();

    private final ResourceImpl resource;

    public SubnetImpl(ResourceImpl resource) {
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
    public boolean load(DescribeSubnetsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeSubnetsRequest request,
            ResultCapture<DescribeSubnetsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public List<Tag> getTags() {
        return (List<Tag>) resource.getAttribute("Tags");
    }

    @Override
    public String getCidrBlock() {
        return (String) resource.getAttribute("CidrBlock");
    }

    @Override
    public Boolean getMapPublicIpOnLaunch() {
        return (Boolean) resource.getAttribute("MapPublicIpOnLaunch");
    }

    @Override
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public String getSubnetId() {
        return (String) resource.getAttribute("SubnetId");
    }

    @Override
    public Boolean getDefaultForAz() {
        return (Boolean) resource.getAttribute("DefaultForAz");
    }

    @Override
    public Integer getAvailableIpAddressCount() {
        return (Integer) resource.getAttribute("AvailableIpAddressCount");
    }

    @Override
    public String getAvailabilityZone() {
        return (String) resource.getAttribute("AvailabilityZone");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public Vpc getVpc() {
        ResourceImpl result = resource.getReference("Vpc");
        if (result == null) return null;
        return new VpcImpl(result);
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
    public void delete(DeleteSubnetRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteSubnetRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public List<Instance> createInstances(RunInstancesRequest request) {
        return createInstances(request, null);
    }

    @Override
    public List<Instance> createInstances(RunInstancesRequest request,
            ResultCapture<RunInstancesResult> extractor) {

        ActionResult result = resource.performAction("CreateInstances", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), InstanceImpl.CODEC);
    }

    private static class Codec implements ResourceCodec<Subnet> {
        @Override
        public Subnet transform(ResourceImpl resource) {
            return new SubnetImpl(resource);
        }
    }
}
