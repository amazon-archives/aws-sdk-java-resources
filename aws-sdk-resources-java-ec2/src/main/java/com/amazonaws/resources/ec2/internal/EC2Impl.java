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
import com.amazonaws.resources.ec2.EC2;
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.SubnetCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.ec2.VpcCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class EC2Impl implements EC2 {

    private final ServiceImpl<AmazonEC2> service;

    public EC2Impl(ServiceImpl<AmazonEC2> service) {
        this.service = service;
    }

    @Override
    public AmazonEC2 client() {
        return service.getClient();
    }

    @Override
    public Vpc getVpc(String id) {
        ResourceImpl result = service.getSubResource("Vpc", id);
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public Instance getInstance(String id) {
        ResourceImpl result = service.getSubResource("Instance", id);
        if (result == null) return null;
        return new InstanceImpl(result);
    }

    @Override
    public Subnet getSubnet(String id) {
        ResourceImpl result = service.getSubResource("Subnet", id);
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public VpcCollection getVpcs() {
        return getVpcs(null);
    }

    @Override
    public VpcCollection getVpcs(DescribeVpcsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Vpcs", request);
        if (result == null) return null;
        return new VpcCollectionImpl(result);
    }

    @Override
    public SubnetCollection getSubnets() {
        return getSubnets(null);
    }

    @Override
    public SubnetCollection getSubnets(DescribeSubnetsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Subnets",
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
        ResourceCollectionImpl result = service.getCollection("Instances",
                request);

        if (result == null) return null;
        return new InstanceCollectionImpl(result);
    }

    @Override
    public Vpc createVpc(CreateVpcRequest request) {
        return createVpc(request, null);
    }

    @Override
    public Vpc createVpc(CreateVpcRequest request,
            ResultCapture<CreateVpcResult> extractor) {

        ActionResult result = service.performAction("CreateVpc", request,
                extractor);

        if (result == null) return null;
        return new VpcImpl(result.getResource());
    }

    @Override
    public Subnet createSubnet(CreateSubnetRequest request) {
        return createSubnet(request, null);
    }

    @Override
    public Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor) {

        ActionResult result = service.performAction("CreateSubnet", request,
                extractor);

        if (result == null) return null;
        return new SubnetImpl(result.getResource());
    }

    @Override
    public List<Instance> createInstances(RunInstancesRequest request) {
        return createInstances(request, null);
    }

    @Override
    public List<Instance> createInstances(RunInstancesRequest request,
            ResultCapture<RunInstancesResult> extractor) {

        ActionResult result = service.performAction("CreateInstances", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), InstanceImpl.CODEC);
    }
}
