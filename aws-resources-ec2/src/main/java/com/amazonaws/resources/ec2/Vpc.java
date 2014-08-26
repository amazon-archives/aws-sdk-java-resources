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
import com.amazonaws.services.ec2.model.AssociateDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateNetworkAclRequest;
import com.amazonaws.services.ec2.model.CreateNetworkAclResult;
import com.amazonaws.services.ec2.model.CreateRouteTableRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeVpcAttributeResult;
import com.amazonaws.services.ec2.model.DescribeVpcPeeringConnectionsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.DetachInternetGatewayRequest;
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
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(DescribeVpcsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVpcsRequest
     */
    boolean load(DescribeVpcsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVpcsRequest
     */
    boolean load(DescribeVpcsRequest request, ResultCapture<DescribeVpcsResult>
            extractor);

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
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
     * Retrieves the <code>DhcpOptions</code> resource referenced by this
     * resource.
     */
    DhcpOptions getDhcpOptions();

    /**
     * Retrieves the AcceptedVpcPeeringConnections collection referenced by this
     * resource.
     */
    VpcPeeringConnectionCollection getAcceptedVpcPeeringConnections();

    /**
     * Retrieves the AcceptedVpcPeeringConnections collection referenced by this
     * resource.
     */
    VpcPeeringConnectionCollection getAcceptedVpcPeeringConnections(
            DescribeVpcPeeringConnectionsRequest request);

    /**
     * Retrieves the InternetGateways collection referenced by this resource.
     */
    InternetGatewayCollection getInternetGateways();

    /**
     * Retrieves the InternetGateways collection referenced by this resource.
     */
    InternetGatewayCollection getInternetGateways(
            DescribeInternetGatewaysRequest request);

    /**
     * Retrieves the NetworkInterfaces collection referenced by this resource.
     */
    NetworkInterfaceCollection getNetworkInterfaces();

    /**
     * Retrieves the NetworkInterfaces collection referenced by this resource.
     */
    NetworkInterfaceCollection getNetworkInterfaces(
            DescribeNetworkInterfacesRequest request);

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
     * Retrieves the SecurityGroups collection referenced by this resource.
     */
    SecurityGroupCollection getSecurityGroups();

    /**
     * Retrieves the SecurityGroups collection referenced by this resource.
     */
    SecurityGroupCollection getSecurityGroups(DescribeSecurityGroupsRequest
            request);

    /**
     * Retrieves the RouteTables collection referenced by this resource.
     */
    RouteTableCollection getRouteTables();

    /**
     * Retrieves the RouteTables collection referenced by this resource.
     */
    RouteTableCollection getRouteTables(DescribeRouteTablesRequest request);

    /**
     * Retrieves the NetworkAcls collection referenced by this resource.
     */
    NetworkAclCollection getNetworkAcls();

    /**
     * Retrieves the NetworkAcls collection referenced by this resource.
     */
    NetworkAclCollection getNetworkAcls(DescribeNetworkAclsRequest request);

    /**
     * Retrieves the RequestedVpcPeeringConnections collection referenced by
     * this resource.
     */
    VpcPeeringConnectionCollection getRequestedVpcPeeringConnections();

    /**
     * Retrieves the RequestedVpcPeeringConnections collection referenced by
     * this resource.
     */
    VpcPeeringConnectionCollection getRequestedVpcPeeringConnections(
            DescribeVpcPeeringConnectionsRequest request);

    /**
     * Performs the <code>AssociateDhcpOptions</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AssociateDhcpOptionsRequest
     */
    void associateDhcpOptions(AssociateDhcpOptionsRequest request);

    /**
     * Performs the <code>AssociateDhcpOptions</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AssociateDhcpOptionsRequest
     */
    void associateDhcpOptions(AssociateDhcpOptionsRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>AssociateDhcpOptions</code>
     * action.
     *
     * @see #associateDhcpOptions(AssociateDhcpOptionsRequest)
     */
    void associateDhcpOptions();

    /**
     * The convenient method form for the <code>AssociateDhcpOptions</code>
     * action.
     *
     * @see #associateDhcpOptions(AssociateDhcpOptionsRequest, ResultCapture)
     */
    void associateDhcpOptions(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateSecurityGroup</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>SecurityGroup</code> resource object associated with
     *         the result of this action.
     * @see CreateSecurityGroupRequest
     */
    SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request);

    /**
     * Performs the <code>CreateSecurityGroup</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>SecurityGroup</code> resource object associated with
     *         the result of this action.
     * @see CreateSecurityGroupRequest
     */
    SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request,
            ResultCapture<CreateSecurityGroupResult> extractor);

    /**
     * Performs the <code>RequestVpcPeeringConnection</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>VpcPeeringConnection</code> resource object associated
     *         with the result of this action.
     * @see CreateVpcPeeringConnectionRequest
     */
    VpcPeeringConnection requestVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request);

    /**
     * Performs the <code>RequestVpcPeeringConnection</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>VpcPeeringConnection</code> resource object associated
     *         with the result of this action.
     * @see CreateVpcPeeringConnectionRequest
     */
    VpcPeeringConnection requestVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request,
            ResultCapture<CreateVpcPeeringConnectionResult> extractor);

    /**
     * Performs the <code>CreateSubnet</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subnet</code> resource object associated with the
     *         result of this action.
     * @see CreateSubnetRequest
     */
    Subnet createSubnet(CreateSubnetRequest request);

    /**
     * Performs the <code>CreateSubnet</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subnet</code> resource object associated with the
     *         result of this action.
     * @see CreateSubnetRequest
     */
    Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor);

    /**
     * The convenient method form for the <code>CreateSubnet</code> action.
     *
     * @see #createSubnet(CreateSubnetRequest)
     */
    Subnet createSubnet(String cidrBlock);

    /**
     * The convenient method form for the <code>CreateSubnet</code> action.
     *
     * @see #createSubnet(CreateSubnetRequest, ResultCapture)
     */
    Subnet createSubnet(String cidrBlock, ResultCapture<CreateSubnetResult>
            extractor);

    /**
     * Performs the <code>CreateNetworkAcl</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>NetworkAcl</code> resource object associated with the
     *         result of this action.
     * @see CreateNetworkAclRequest
     */
    NetworkAcl createNetworkAcl(CreateNetworkAclRequest request);

    /**
     * Performs the <code>CreateNetworkAcl</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>NetworkAcl</code> resource object associated with the
     *         result of this action.
     * @see CreateNetworkAclRequest
     */
    NetworkAcl createNetworkAcl(CreateNetworkAclRequest request,
            ResultCapture<CreateNetworkAclResult> extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyVpcAttributeRequest
     */
    void modifyAttribute(ModifyVpcAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyVpcAttributeRequest
     */
    void modifyAttribute(ModifyVpcAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVpcAttributeRequest
     */
    DescribeVpcAttributeResult describeAttribute(DescribeVpcAttributeRequest
            request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVpcAttributeRequest
     */
    DescribeVpcAttributeResult describeAttribute(DescribeVpcAttributeRequest
            request, ResultCapture<DescribeVpcAttributeResult> extractor);

    /**
     * Performs the <code>DetachInternetGateway</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DetachInternetGatewayRequest
     */
    void detachInternetGateway(DetachInternetGatewayRequest request);

    /**
     * Performs the <code>DetachInternetGateway</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DetachInternetGatewayRequest
     */
    void detachInternetGateway(DetachInternetGatewayRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>AttachInternetGateway</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AttachInternetGatewayRequest
     */
    void attachInternetGateway(AttachInternetGatewayRequest request);

    /**
     * Performs the <code>AttachInternetGateway</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AttachInternetGatewayRequest
     */
    void attachInternetGateway(AttachInternetGatewayRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVpcRequest
     */
    void delete(DeleteVpcRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVpcRequest
     */
    void delete(DeleteVpcRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVpcRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVpcRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateRouteTable</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTable</code> resource object associated with the
     *         result of this action.
     * @see CreateRouteTableRequest
     */
    RouteTable createRouteTable(CreateRouteTableRequest request);

    /**
     * Performs the <code>CreateRouteTable</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTable</code> resource object associated with the
     *         result of this action.
     * @see CreateRouteTableRequest
     */
    RouteTable createRouteTable(CreateRouteTableRequest request,
            ResultCapture<CreateRouteTableResult> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest request);

    /**
     * Performs the <code>CreateTags</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vpc</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest)
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest, ResultCapture)
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags,
            ResultCapture<Void> extractor);
}
