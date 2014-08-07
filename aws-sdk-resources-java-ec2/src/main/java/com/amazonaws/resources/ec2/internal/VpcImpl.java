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
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.SubnetCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeVpcAttributeResult;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.ModifyVpcAttributeRequest;
import com.amazonaws.services.ec2.model.Tag;

class VpcImpl implements Vpc {
    public static final ResourceCodec<Vpc> CODEC = new Codec();

    private final ResourceImpl resource;

    public VpcImpl(ResourceImpl resource) {
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
    public boolean load(DescribeVpcsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeVpcsRequest request,
            ResultCapture<DescribeVpcsResult> extractor) {

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
    public String getDhcpOptionsId() {
        return (String) resource.getAttribute("DhcpOptionsId");
    }

    @Override
    public String getCidrBlock() {
        return (String) resource.getAttribute("CidrBlock");
    }

    @Override
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public Boolean getIsDefault() {
        return (Boolean) resource.getAttribute("IsDefault");
    }

    @Override
    public String getInstanceTenancy() {
        return (String) resource.getAttribute("InstanceTenancy");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public SubnetCollection getSubnets() {
        return getSubnets(null);
    }

    @Override
    public SubnetCollection getSubnets(DescribeSubnetsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Subnets",
                request);

        if (result == null) return null;
        return new SubnetCollectionImpl(result);
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
    public Subnet createSubnet(CreateSubnetRequest request) {
        return createSubnet(request, null);
    }

    @Override
    public Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor) {

        ActionResult result = resource.performAction("CreateSubnet", request,
                extractor);

        if (result == null) return null;
        return new SubnetImpl(result.getResource());
    }

    @Override
    public void modifyAttribute(ModifyVpcAttributeRequest request) {
        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifyVpcAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ModifyAttribute", request, extractor);
    }

    @Override
    public DescribeVpcAttributeResult describeAttribute(
            DescribeVpcAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeVpcAttributeResult describeAttribute(
            DescribeVpcAttributeRequest request,
            ResultCapture<DescribeVpcAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeVpcAttributeResult) result.getData();
    }

    @Override
    public void delete(DeleteVpcRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteVpcRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<Vpc> {
        @Override
        public Vpc transform(ResourceImpl resource) {
            return new VpcImpl(resource);
        }
    }
}
