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
package com.amazonaws.resources.ec2;

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;
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

/**
 * The EC2 service.
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.ec2.internal.EC2Impl")

public interface EC2 extends Service<AmazonEC2> {
    /**
     * Gets a subresource.
     */
    Vpc getVpc(String id);

    /**
     * Gets a subresource.
     */
    Instance getInstance(String id);

    /**
     * Gets a subresource.
     */
    Subnet getSubnet(String id);

    /**
     * Retrieves the Vpcs collection referenced by this resource.
     */
    VpcCollection getVpcs();

    /**
     * Retrieves the Vpcs collection referenced by this resource.
     */
    VpcCollection getVpcs(DescribeVpcsRequest request);

    /**
     * Retrieves the Subnets collection referenced by this resource.
     */
    SubnetCollection getSubnets();

    /**
     * Retrieves the Subnets collection referenced by this resource.
     */
    SubnetCollection getSubnets(DescribeSubnetsRequest request);

    /**
     * Retrieves the Instances collection referenced by this resource.
     */
    InstanceCollection getInstances();

    /**
     * Retrieves the Instances collection referenced by this resource.
     */
    InstanceCollection getInstances(DescribeInstancesRequest request);

    /**
     * Performs an action.
     */
    Vpc createVpc(CreateVpcRequest request);

    /**
     * Performs an action.
     */
    Vpc createVpc(CreateVpcRequest request, ResultCapture<CreateVpcResult>
            extractor);

    /**
     * Performs an action.
     */
    Subnet createSubnet(CreateSubnetRequest request);

    /**
     * Performs an action.
     */
    Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor);

    /**
     * Performs an action.
     */
    List<Instance> createInstances(RunInstancesRequest request);

    /**
     * Performs an action.
     */
    List<Instance> createInstances(RunInstancesRequest request,
            ResultCapture<RunInstancesResult> extractor);
}
