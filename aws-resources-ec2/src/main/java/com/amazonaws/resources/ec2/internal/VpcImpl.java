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
import com.amazonaws.resources.ec2.DhcpOptions;
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.InternetGatewayCollection;
import com.amazonaws.resources.ec2.NetworkAcl;
import com.amazonaws.resources.ec2.NetworkAclCollection;
import com.amazonaws.resources.ec2.NetworkInterfaceCollection;
import com.amazonaws.resources.ec2.RouteTable;
import com.amazonaws.resources.ec2.RouteTableCollection;
import com.amazonaws.resources.ec2.SecurityGroup;
import com.amazonaws.resources.ec2.SecurityGroupCollection;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.SubnetCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.ec2.VpcPeeringConnection;
import com.amazonaws.resources.ec2.VpcPeeringConnectionCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
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
    public DhcpOptions getDhcpOptions() {
        ResourceImpl result = resource.getReference("DhcpOptions");
        if (result == null) return null;
        return new DhcpOptionsImpl(result);
    }

    @Override
    public InternetGatewayCollection getInternetGateways() {
        return getInternetGateways(null);
    }

    @Override
    public InternetGatewayCollection getInternetGateways(
            DescribeInternetGatewaysRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("InternetGateways", request);

        if (result == null) return null;
        return new InternetGatewayCollectionImpl(result);
    }

    @Override
    public VpcPeeringConnectionCollection getAcceptedVpcPeeringConnections() {
        return getAcceptedVpcPeeringConnections(null);
    }

    @Override
    public VpcPeeringConnectionCollection getAcceptedVpcPeeringConnections(
            DescribeVpcPeeringConnectionsRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("AcceptedVpcPeeringConnections",
                request);

        if (result == null) return null;
        return new VpcPeeringConnectionCollectionImpl(result);
    }

    @Override
    public NetworkInterfaceCollection getNetworkInterfaces() {
        return getNetworkInterfaces(null);
    }

    @Override
    public NetworkInterfaceCollection getNetworkInterfaces(
            DescribeNetworkInterfacesRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("NetworkInterfaces", request);

        if (result == null) return null;
        return new NetworkInterfaceCollectionImpl(result);
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
    public SecurityGroupCollection getSecurityGroups() {
        return getSecurityGroups(null);
    }

    @Override
    public SecurityGroupCollection getSecurityGroups(
            DescribeSecurityGroupsRequest request) {

        ResourceCollectionImpl result = resource.getCollection("SecurityGroups",
                request);

        if (result == null) return null;
        return new SecurityGroupCollectionImpl(result);
    }

    @Override
    public RouteTableCollection getRouteTables() {
        return getRouteTables(null);
    }

    @Override
    public RouteTableCollection getRouteTables(DescribeRouteTablesRequest
            request) {

        ResourceCollectionImpl result = resource.getCollection("RouteTables",
                request);

        if (result == null) return null;
        return new RouteTableCollectionImpl(result);
    }

    @Override
    public NetworkAclCollection getNetworkAcls() {
        return getNetworkAcls(null);
    }

    @Override
    public NetworkAclCollection getNetworkAcls(DescribeNetworkAclsRequest
            request) {

        ResourceCollectionImpl result = resource.getCollection("NetworkAcls",
                request);

        if (result == null) return null;
        return new NetworkAclCollectionImpl(result);
    }

    @Override
    public VpcPeeringConnectionCollection getRequestedVpcPeeringConnections() {
        return getRequestedVpcPeeringConnections(null);
    }

    @Override
    public VpcPeeringConnectionCollection getRequestedVpcPeeringConnections(
            DescribeVpcPeeringConnectionsRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("RequestedVpcPeeringConnections",
                request);

        if (result == null) return null;
        return new VpcPeeringConnectionCollectionImpl(result);
    }

    @Override
    public void associateDhcpOptions(AssociateDhcpOptionsRequest request) {
        associateDhcpOptions(request, null);
    }

    @Override
    public void associateDhcpOptions(AssociateDhcpOptionsRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AssociateDhcpOptions", request, extractor);
    }

    @Override
    public void associateDhcpOptions() {
        associateDhcpOptions((ResultCapture<Void>)null);
    }

    @Override
    public void associateDhcpOptions(ResultCapture<Void> extractor) {
        AssociateDhcpOptionsRequest request = new AssociateDhcpOptionsRequest();
        associateDhcpOptions(request, extractor);
    }

    @Override
    public SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request)
            {

        return createSecurityGroup(request, null);
    }

    @Override
    public SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request,
            ResultCapture<CreateSecurityGroupResult> extractor) {

        ActionResult result = resource.performAction("CreateSecurityGroup",
                request, extractor);

        if (result == null) return null;
        return new SecurityGroupImpl(result.getResource());
    }

    @Override
    public VpcPeeringConnection requestVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request) {

        return requestVpcPeeringConnection(request, null);
    }

    @Override
    public VpcPeeringConnection requestVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request,
            ResultCapture<CreateVpcPeeringConnectionResult> extractor) {

        ActionResult result =
                resource.performAction("RequestVpcPeeringConnection", request,
                extractor);

        if (result == null) return null;
        return new VpcPeeringConnectionImpl(result.getResource());
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
    public Subnet createSubnet(String cidrBlock) {
        return createSubnet(cidrBlock, (ResultCapture<CreateSubnetResult>)null);
    }

    @Override
    public Subnet createSubnet(String cidrBlock,
            ResultCapture<CreateSubnetResult> extractor) {

        CreateSubnetRequest request = new CreateSubnetRequest()
            .withCidrBlock(cidrBlock);
        return createSubnet(request, extractor);
    }

    @Override
    public NetworkAcl createNetworkAcl(CreateNetworkAclRequest request) {
        return createNetworkAcl(request, null);
    }

    @Override
    public NetworkAcl createNetworkAcl(CreateNetworkAclRequest request,
            ResultCapture<CreateNetworkAclResult> extractor) {

        ActionResult result = resource.performAction("CreateNetworkAcl",
                request, extractor);

        if (result == null) return null;
        return new NetworkAclImpl(result.getResource());
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
    public void detachInternetGateway(DetachInternetGatewayRequest request) {
        detachInternetGateway(request, null);
    }

    @Override
    public void detachInternetGateway(DetachInternetGatewayRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("DetachInternetGateway", request, extractor);
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

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteVpcRequest request = new DeleteVpcRequest();
        delete(request, extractor);
    }

    @Override
    public void attachInternetGateway(AttachInternetGatewayRequest request) {
        attachInternetGateway(request, null);
    }

    @Override
    public void attachInternetGateway(AttachInternetGatewayRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AttachInternetGateway", request, extractor);
    }

    @Override
    public RouteTable createRouteTable(CreateRouteTableRequest request) {
        return createRouteTable(request, null);
    }

    @Override
    public RouteTable createRouteTable(CreateRouteTableRequest request,
            ResultCapture<CreateRouteTableResult> extractor) {

        ActionResult result = resource.performAction("CreateRouteTable",
                request, extractor);

        if (result == null) return null;
        return new RouteTableImpl(result.getResource());
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest
            request) {

        return createTags(request, null);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest
            request, ResultCapture<Void> extractor) {

        ActionResult result = resource.performAction("CreateTags", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), TagImpl.CODEC);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags) {
        return createTags(tags, (ResultCapture<Void>)null);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags,
            ResultCapture<Void> extractor) {

        CreateTagsRequest request = new CreateTagsRequest()
            .withTags(tags);
        return createTags(request, extractor);
    }

    private static class Codec implements ResourceCodec<Vpc> {
        @Override
        public Vpc transform(ResourceImpl resource) {
            return new VpcImpl(resource);
        }
    }
}
