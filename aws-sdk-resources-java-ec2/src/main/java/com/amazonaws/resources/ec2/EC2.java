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
import com.amazonaws.services.ec2.model.CreateDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.CreateDhcpOptionsResult;
import com.amazonaws.services.ec2.model.CreateInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateInternetGatewayResult;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateNetworkAclRequest;
import com.amazonaws.services.ec2.model.CreateNetworkAclResult;
import com.amazonaws.services.ec2.model.CreateNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.CreateNetworkInterfaceResult;
import com.amazonaws.services.ec2.model.CreatePlacementGroupRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.CreateVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVpcPeeringConnectionsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DisassociateRouteTableRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairResult;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;
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
    KeyPair getKeyPair(String name);

    /**
     * Gets a subresource.
     */
    RouteTable getRouteTable(String id);

    /**
     * Gets a subresource.
     */
    Instance getInstance(String id);

    /**
     * Gets a subresource.
     */
    NetworkInterface getNetworkInterface(String id);

    /**
     * Gets a subresource.
     */
    Volume getVolume(String id);

    /**
     * Gets a subresource.
     */
    DhcpOptions getDhcpOptions(String id);

    /**
     * Gets a subresource.
     */
    PlacementGroup getPlacementGroup(String name);

    /**
     * Gets a subresource.
     */
    InternetGateway getInternetGateway(String id);

    /**
     * Gets a subresource.
     */
    RouteTableAssociation getRouteTableAssociation(String id);

    /**
     * Gets a subresource.
     */
    Subnet getSubnet(String id);

    /**
     * Gets a subresource.
     */
    VpcPeeringConnection getVpcPeeringConnection(String id);

    /**
     * Gets a subresource.
     */
    Snapshot getSnapshot(String id);

    /**
     * Gets a subresource.
     */
    Vpc getVpc(String id);

    /**
     * Gets a subresource.
     */
    SecurityGroup getSecurityGroup(String id);

    /**
     * Gets a subresource.
     */
    Image getImage(String id);

    /**
     * Gets a subresource.
     */
    NetworkAcl getNetworkAcl(String id);

    /**
     * Retrieves the VpcPeeringConnections collection referenced by this
     * resource.
     */
    VpcPeeringConnectionCollection getVpcPeeringConnections();

    /**
     * Retrieves the VpcPeeringConnections collection referenced by this
     * resource.
     */
    VpcPeeringConnectionCollection getVpcPeeringConnections(
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
     * Retrieves the KeyPairs collection referenced by this resource.
     */
    KeyPairCollection getKeyPairs();

    /**
     * Retrieves the KeyPairs collection referenced by this resource.
     */
    KeyPairCollection getKeyPairs(DescribeKeyPairsRequest request);

    /**
     * Retrieves the DhcpOptionsSets collection referenced by this resource.
     */
    DhcpOptionsCollection getDhcpOptionsSets();

    /**
     * Retrieves the DhcpOptionsSets collection referenced by this resource.
     */
    DhcpOptionsCollection getDhcpOptionsSets(DescribeDhcpOptionsRequest request)
            ;

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
     * Retrieves the Images collection referenced by this resource.
     */
    ImageCollection getImages();

    /**
     * Retrieves the Images collection referenced by this resource.
     */
    ImageCollection getImages(DescribeImagesRequest request);

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
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots();

    /**
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots(DescribeSnapshotsRequest request);

    /**
     * Retrieves the PlacementGroups collection referenced by this resource.
     */
    PlacementGroupCollection getPlacementGroups();

    /**
     * Retrieves the PlacementGroups collection referenced by this resource.
     */
    PlacementGroupCollection getPlacementGroups(DescribePlacementGroupsRequest
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
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes();

    /**
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes(DescribeVolumesRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(
            CreateSecurityGroupRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(
            CreateSecurityGroupRequest request,
            ResultCapture<CreateSecurityGroupResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(CreateSubnetRequest request)
            ;

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.InternetGateway createInternetGateway(
            CreateInternetGatewayRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.InternetGateway createInternetGateway(
            CreateInternetGatewayRequest request,
            ResultCapture<CreateInternetGatewayResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.NetworkAcl createNetworkAcl(
            CreateNetworkAclRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.NetworkAcl createNetworkAcl(
            CreateNetworkAclRequest request,
            ResultCapture<CreateNetworkAclResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(ImportKeyPairRequest
            request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(ImportKeyPairRequest
            request, ResultCapture<ImportKeyPairResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.RouteTable createRouteTable(
            CreateRouteTableRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.RouteTable createRouteTable(
            CreateRouteTableRequest request,
            ResultCapture<CreateRouteTableResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(
            CreatePlacementGroupRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(
            CreatePlacementGroupRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            CreateDhcpOptionsRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            CreateDhcpOptionsRequest request,
            ResultCapture<CreateDhcpOptionsResult> extractor);

    /**
     * Performs an action.
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(
            RunInstancesRequest request);

    /**
     * Performs an action.
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(
            RunInstancesRequest request, ResultCapture<RunInstancesResult>
            extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Image createVolume(CreateVolumeRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Image createVolume(CreateVolumeRequest request,
            ResultCapture<CreateVolumeResult> extractor);

    /**
     * Performs an action.
     */
    void disassociateRouteTable(DisassociateRouteTableRequest request);

    /**
     * Performs an action.
     */
    void disassociateRouteTable(DisassociateRouteTableRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Vpc createVpc(CreateVpcRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Vpc createVpc(CreateVpcRequest request,
            ResultCapture<CreateVpcResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.NetworkInterface createNetworkInterface(
            CreateNetworkInterfaceRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.NetworkInterface createNetworkInterface(
            CreateNetworkInterfaceRequest request,
            ResultCapture<CreateNetworkInterfaceResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(CreateKeyPairRequest
            request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(CreateKeyPairRequest
            request, ResultCapture<CreateKeyPairResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request,
            ResultCapture<CreateVpcPeeringConnectionResult> extractor);

    /**
     * Performs an action.
     */
    List<Tag> createTags(CreateTagsRequest request);

    /**
     * Performs an action.
     */
    List<Tag> createTags(CreateTagsRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Image registerImage(RegisterImageRequest request
            );

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Image registerImage(RegisterImageRequest request
            , ResultCapture<RegisterImageResult> extractor);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(CreateSnapshotRequest
            request);

    /**
     * Performs an action.
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(CreateSnapshotRequest
            request, ResultCapture<CreateSnapshotResult> extractor);
}
