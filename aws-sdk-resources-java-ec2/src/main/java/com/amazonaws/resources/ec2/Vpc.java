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

/**
 * The Vpc resource.
 */
public interface Vpc {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeVpcsRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeVpcsRequest request, ResultCapture<DescribeVpcsResult>
            extractor);

    /**
     * Gets the value of the Id identifier.
     */
    String getId();

    /**
     * Gets the value of the Tags attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    List<Tag> getTags();

    /**
     * Gets the value of the DhcpOptionsId attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getDhcpOptionsId();

    /**
     * Gets the value of the CidrBlock attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCidrBlock();

    /**
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getState();

    /**
     * Gets the value of the IsDefault attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getIsDefault();

    /**
     * Gets the value of the InstanceTenancy attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getInstanceTenancy();

    /**
     * Gets the value of the VpcId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVpcId();

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
    Subnet createSubnet(CreateSubnetRequest request);

    /**
     * Performs an action.
     */
    Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor);

    /**
     * Performs an action.
     */
    void modifyAttribute(ModifyVpcAttributeRequest request);

    /**
     * Performs an action.
     */
    void modifyAttribute(ModifyVpcAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    DescribeVpcAttributeResult describeAttribute(DescribeVpcAttributeRequest
            request);

    /**
     * Performs an action.
     */
    DescribeVpcAttributeResult describeAttribute(DescribeVpcAttributeRequest
            request, ResultCapture<DescribeVpcAttributeResult> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteVpcRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteVpcRequest request, ResultCapture<Void> extractor);
}
