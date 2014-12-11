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
import com.amazonaws.services.ec2.model.DhcpConfiguration;
import com.amazonaws.services.ec2.model.DisassociateRouteTableRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairRequest;
import com.amazonaws.services.ec2.model.ImportKeyPairResult;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

/**
 * The <code>EC2</code> service.
 * This is the entry point to interact with the following service resources:<ul>
 *   <li>KeyPair</li>
 *   <li>Instance</li>
 *   <li>RouteTable</li>
 *   <li>NetworkInterface</li>
 *   <li>Volume</li>
 *   <li>DhcpOptions</li>
 *   <li>InternetGateway</li>
 *   <li>PlacementGroup</li>
 *   <li>Subnet</li>
 *   <li>RouteTableAssociation</li>
 *   <li>VpcPeeringConnection</li>
 *   <li>Snapshot</li>
 *   <li>Vpc</li>
 *   <li>SecurityGroup</li>
 *   <li>Tag</li>
 *   <li>Image</li>
 *   <li>NetworkAcl</li>
 * </ul>
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.ec2.internal.EC2Impl")

public interface EC2 extends Service<AmazonEC2> {
    /**
     * Gets an instance of {@code KeyPair} resource by its identifier(s).
     */
    KeyPair getKeyPair(String name);

    /**
     * Gets an instance of {@code Instance} resource by its identifier(s).
     */
    Instance getInstance(String id);

    /**
     * Gets an instance of {@code RouteTable} resource by its identifier(s).
     */
    RouteTable getRouteTable(String id);

    /**
     * Gets an instance of {@code NetworkInterface} resource by its
     * identifier(s).
     */
    NetworkInterface getNetworkInterface(String id);

    /**
     * Gets an instance of {@code Volume} resource by its identifier(s).
     */
    Volume getVolume(String id);

    /**
     * Gets an instance of {@code DhcpOptions} resource by its identifier(s).
     */
    DhcpOptions getDhcpOptions(String id);

    /**
     * Gets an instance of {@code InternetGateway} resource by its
     * identifier(s).
     */
    InternetGateway getInternetGateway(String id);

    /**
     * Gets an instance of {@code PlacementGroup} resource by its identifier(s).
     */
    PlacementGroup getPlacementGroup(String name);

    /**
     * Gets an instance of {@code Subnet} resource by its identifier(s).
     */
    Subnet getSubnet(String id);

    /**
     * Gets an instance of {@code RouteTableAssociation} resource by its
     * identifier(s).
     */
    RouteTableAssociation getRouteTableAssociation(String id);

    /**
     * Gets an instance of {@code VpcPeeringConnection} resource by its
     * identifier(s).
     */
    VpcPeeringConnection getVpcPeeringConnection(String id);

    /**
     * Gets an instance of {@code Snapshot} resource by its identifier(s).
     */
    Snapshot getSnapshot(String id);

    /**
     * Gets an instance of {@code Vpc} resource by its identifier(s).
     */
    Vpc getVpc(String id);

    /**
     * Gets an instance of {@code SecurityGroup} resource by its identifier(s).
     */
    SecurityGroup getSecurityGroup(String id);

    /**
     * Gets an instance of {@code Image} resource by its identifier(s).
     */
    Image getImage(String id);

    /**
     * Gets an instance of {@code NetworkAcl} resource by its identifier(s).
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
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots();

    /**
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots(DescribeSnapshotsRequest request);

    /**
     * Retrieves the Subnets collection referenced by this resource.
     */
    SubnetCollection getSubnets();

    /**
     * Retrieves the Subnets collection referenced by this resource.
     */
    SubnetCollection getSubnets(DescribeSubnetsRequest request);

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
     * Retrieves the NetworkAcls collection referenced by this resource.
     */
    NetworkAclCollection getNetworkAcls();

    /**
     * Retrieves the NetworkAcls collection referenced by this resource.
     */
    NetworkAclCollection getNetworkAcls(DescribeNetworkAclsRequest request);

    /**
     * Retrieves the RouteTables collection referenced by this resource.
     */
    RouteTableCollection getRouteTables();

    /**
     * Retrieves the RouteTables collection referenced by this resource.
     */
    RouteTableCollection getRouteTables(DescribeRouteTablesRequest request);

    /**
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes();

    /**
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes(DescribeVolumesRequest request);

    /**
     * Performs the <code>CreateSecurityGroup</code> action.
     *
     * <p>
     *
     * @return The <code>SecurityGroup</code> resource object associated with
     *         the result of this action.
     * @see CreateSecurityGroupRequest
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(
            CreateSecurityGroupRequest request);

    /**
     * Performs the <code>CreateSecurityGroup</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>SecurityGroup</code> resource object associated with
     *         the result of this action.
     * @see CreateSecurityGroupRequest
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(
            CreateSecurityGroupRequest request,
            ResultCapture<CreateSecurityGroupResult> extractor);

    /**
     * The convenient method form for the <code>CreateSecurityGroup</code>
     * action.
     *
     * @see #createSecurityGroup(CreateSecurityGroupRequest)
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(String
            description, String groupName);

    /**
     * The convenient method form for the <code>CreateSecurityGroup</code>
     * action.
     *
     * @see #createSecurityGroup(CreateSecurityGroupRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.SecurityGroup createSecurityGroup(String
            description, String groupName,
            ResultCapture<CreateSecurityGroupResult> extractor);

    /**
     * Performs the <code>CreateSubnet</code> action.
     *
     * <p>
     *
     * @return The <code>Subnet</code> resource object associated with the
     *         result of this action.
     * @see CreateSubnetRequest
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(CreateSubnetRequest request)
            ;

    /**
     * Performs the <code>CreateSubnet</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Subnet</code> resource object associated with the
     *         result of this action.
     * @see CreateSubnetRequest
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(CreateSubnetRequest request,
            ResultCapture<CreateSubnetResult> extractor);

    /**
     * The convenient method form for the <code>CreateSubnet</code> action.
     *
     * @see #createSubnet(CreateSubnetRequest)
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(String cidrBlock, String
            vpcId);

    /**
     * The convenient method form for the <code>CreateSubnet</code> action.
     *
     * @see #createSubnet(CreateSubnetRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Subnet createSubnet(String cidrBlock, String
            vpcId, ResultCapture<CreateSubnetResult> extractor);

    /**
     * Performs the <code>CreateInternetGateway</code> action.
     *
     * <p>
     *
     * @return The <code>InternetGateway</code> resource object associated with
     *         the result of this action.
     * @see CreateInternetGatewayRequest
     */
    com.amazonaws.resources.ec2.InternetGateway createInternetGateway(
            CreateInternetGatewayRequest request);

    /**
     * Performs the <code>CreateInternetGateway</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>InternetGateway</code> resource object associated with
     *         the result of this action.
     * @see CreateInternetGatewayRequest
     */
    com.amazonaws.resources.ec2.InternetGateway createInternetGateway(
            CreateInternetGatewayRequest request,
            ResultCapture<CreateInternetGatewayResult> extractor);

    /**
     * Performs the <code>CreateNetworkAcl</code> action.
     *
     * <p>
     *
     * @return The <code>NetworkAcl</code> resource object associated with the
     *         result of this action.
     * @see CreateNetworkAclRequest
     */
    com.amazonaws.resources.ec2.NetworkAcl createNetworkAcl(
            CreateNetworkAclRequest request);

    /**
     * Performs the <code>CreateNetworkAcl</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>NetworkAcl</code> resource object associated with the
     *         result of this action.
     * @see CreateNetworkAclRequest
     */
    com.amazonaws.resources.ec2.NetworkAcl createNetworkAcl(
            CreateNetworkAclRequest request,
            ResultCapture<CreateNetworkAclResult> extractor);

    /**
     * Performs the <code>ImportKeyPair</code> action.
     *
     * <p>
     *
     * @return The <code>KeyPair</code> resource object associated with the
     *         result of this action.
     * @see ImportKeyPairRequest
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(ImportKeyPairRequest
            request);

    /**
     * Performs the <code>ImportKeyPair</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>KeyPair</code> resource object associated with the
     *         result of this action.
     * @see ImportKeyPairRequest
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(ImportKeyPairRequest
            request, ResultCapture<ImportKeyPairResult> extractor);

    /**
     * The convenient method form for the <code>ImportKeyPair</code> action.
     *
     * @see #importKeyPair(ImportKeyPairRequest)
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(String publicKeyMaterial,
            String keyName);

    /**
     * The convenient method form for the <code>ImportKeyPair</code> action.
     *
     * @see #importKeyPair(ImportKeyPairRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.KeyPair importKeyPair(String publicKeyMaterial,
            String keyName, ResultCapture<ImportKeyPairResult> extractor);

    /**
     * Performs the <code>CreatePlacementGroup</code> action.
     *
     * <p>
     *
     * @return The <code>PlacementGroup</code> resource object associated with
     *         the result of this action.
     * @see CreatePlacementGroupRequest
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(
            CreatePlacementGroupRequest request);

    /**
     * Performs the <code>CreatePlacementGroup</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>PlacementGroup</code> resource object associated with
     *         the result of this action.
     * @see CreatePlacementGroupRequest
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(
            CreatePlacementGroupRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>CreatePlacementGroup</code>
     * action.
     *
     * @see #createPlacementGroup(CreatePlacementGroupRequest)
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(String
            groupName, String strategy);

    /**
     * The convenient method form for the <code>CreatePlacementGroup</code>
     * action.
     *
     * @see #createPlacementGroup(CreatePlacementGroupRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.PlacementGroup createPlacementGroup(String
            groupName, String strategy, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateRouteTable</code> action.
     *
     * <p>
     *
     * @return The <code>RouteTable</code> resource object associated with the
     *         result of this action.
     * @see CreateRouteTableRequest
     */
    com.amazonaws.resources.ec2.RouteTable createRouteTable(
            CreateRouteTableRequest request);

    /**
     * Performs the <code>CreateRouteTable</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>RouteTable</code> resource object associated with the
     *         result of this action.
     * @see CreateRouteTableRequest
     */
    com.amazonaws.resources.ec2.RouteTable createRouteTable(
            CreateRouteTableRequest request,
            ResultCapture<CreateRouteTableResult> extractor);

    /**
     * Performs the <code>CreateDhcpOptions</code> action.
     *
     * <p>
     *
     * @return The <code>DhcpOptions</code> resource object associated with the
     *         result of this action.
     * @see CreateDhcpOptionsRequest
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            CreateDhcpOptionsRequest request);

    /**
     * Performs the <code>CreateDhcpOptions</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>DhcpOptions</code> resource object associated with the
     *         result of this action.
     * @see CreateDhcpOptionsRequest
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            CreateDhcpOptionsRequest request,
            ResultCapture<CreateDhcpOptionsResult> extractor);

    /**
     * The convenient method form for the <code>CreateDhcpOptions</code> action.
     *
     * @see #createDhcpOptions(CreateDhcpOptionsRequest)
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            List<DhcpConfiguration> dhcpConfigurations);

    /**
     * The convenient method form for the <code>CreateDhcpOptions</code> action.
     *
     * @see #createDhcpOptions(CreateDhcpOptionsRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.DhcpOptions createDhcpOptions(
            List<DhcpConfiguration> dhcpConfigurations,
            ResultCapture<CreateDhcpOptionsResult> extractor);

    /**
     * Performs the <code>CreateVolume</code> action.
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see CreateVolumeRequest
     */
    com.amazonaws.resources.ec2.Image createVolume(CreateVolumeRequest request);

    /**
     * Performs the <code>CreateVolume</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see CreateVolumeRequest
     */
    com.amazonaws.resources.ec2.Image createVolume(CreateVolumeRequest request,
            ResultCapture<CreateVolumeResult> extractor);

    /**
     * The convenient method form for the <code>CreateVolume</code> action.
     *
     * @see #createVolume(CreateVolumeRequest)
     */
    com.amazonaws.resources.ec2.Image createVolume(String availabilityZone,
            Integer size);

    /**
     * The convenient method form for the <code>CreateVolume</code> action.
     *
     * @see #createVolume(CreateVolumeRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Image createVolume(String availabilityZone,
            Integer size, ResultCapture<CreateVolumeResult> extractor);

    /**
     * The convenient method form for the <code>CreateVolume</code> action.
     *
     * @see #createVolume(CreateVolumeRequest)
     */
    com.amazonaws.resources.ec2.Image createVolume(String snapshotId, String
            availabilityZone);

    /**
     * The convenient method form for the <code>CreateVolume</code> action.
     *
     * @see #createVolume(CreateVolumeRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Image createVolume(String snapshotId, String
            availabilityZone, ResultCapture<CreateVolumeResult> extractor);

    /**
     * Performs the <code>CreateInstances</code> action.
     *
     * <p>
     *
     * @return A list of <code>Instance</code> resource objects associated with
     *         the result of this action.
     * @see RunInstancesRequest
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(
            RunInstancesRequest request);

    /**
     * Performs the <code>CreateInstances</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return A list of <code>Instance</code> resource objects associated with
     *         the result of this action.
     * @see RunInstancesRequest
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(
            RunInstancesRequest request, ResultCapture<RunInstancesResult>
            extractor);

    /**
     * The convenient method form for the <code>CreateInstances</code> action.
     *
     * @see #createInstances(RunInstancesRequest)
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(String imageId,
            Integer minCount, Integer maxCount);

    /**
     * The convenient method form for the <code>CreateInstances</code> action.
     *
     * @see #createInstances(RunInstancesRequest, ResultCapture)
     */
    List<com.amazonaws.resources.ec2.Instance> createInstances(String imageId,
            Integer minCount, Integer maxCount,
            ResultCapture<RunInstancesResult> extractor);

    /**
     * Performs the <code>DisassociateRouteTable</code> action.
     *
     * <p>
     *
     * @see DisassociateRouteTableRequest
     */
    void disassociateRouteTable(DisassociateRouteTableRequest request);

    /**
     * Performs the <code>DisassociateRouteTable</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @see DisassociateRouteTableRequest
     */
    void disassociateRouteTable(DisassociateRouteTableRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateVpc</code> action.
     *
     * <p>
     *
     * @return The <code>Vpc</code> resource object associated with the result
     *         of this action.
     * @see CreateVpcRequest
     */
    com.amazonaws.resources.ec2.Vpc createVpc(CreateVpcRequest request);

    /**
     * Performs the <code>CreateVpc</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Vpc</code> resource object associated with the result
     *         of this action.
     * @see CreateVpcRequest
     */
    com.amazonaws.resources.ec2.Vpc createVpc(CreateVpcRequest request,
            ResultCapture<CreateVpcResult> extractor);

    /**
     * The convenient method form for the <code>CreateVpc</code> action.
     *
     * @see #createVpc(CreateVpcRequest)
     */
    com.amazonaws.resources.ec2.Vpc createVpc(String cidrBlock);

    /**
     * The convenient method form for the <code>CreateVpc</code> action.
     *
     * @see #createVpc(CreateVpcRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Vpc createVpc(String cidrBlock,
            ResultCapture<CreateVpcResult> extractor);

    /**
     * Performs the <code>CreateNetworkInterface</code> action.
     *
     * <p>
     *
     * @return The <code>NetworkInterface</code> resource object associated with
     *         the result of this action.
     * @see CreateNetworkInterfaceRequest
     */
    com.amazonaws.resources.ec2.NetworkInterface createNetworkInterface(
            CreateNetworkInterfaceRequest request);

    /**
     * Performs the <code>CreateNetworkInterface</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>NetworkInterface</code> resource object associated with
     *         the result of this action.
     * @see CreateNetworkInterfaceRequest
     */
    com.amazonaws.resources.ec2.NetworkInterface createNetworkInterface(
            CreateNetworkInterfaceRequest request,
            ResultCapture<CreateNetworkInterfaceResult> extractor);

    /**
     * Performs the <code>CreateKeyPair</code> action.
     *
     * <p>
     *
     * @return The <code>KeyPair</code> resource object associated with the
     *         result of this action.
     * @see CreateKeyPairRequest
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(CreateKeyPairRequest
            request);

    /**
     * Performs the <code>CreateKeyPair</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>KeyPair</code> resource object associated with the
     *         result of this action.
     * @see CreateKeyPairRequest
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(CreateKeyPairRequest
            request, ResultCapture<CreateKeyPairResult> extractor);

    /**
     * The convenient method form for the <code>CreateKeyPair</code> action.
     *
     * @see #createKeyPair(CreateKeyPairRequest)
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(String keyName);

    /**
     * The convenient method form for the <code>CreateKeyPair</code> action.
     *
     * @see #createKeyPair(CreateKeyPairRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.KeyPair createKeyPair(String keyName,
            ResultCapture<CreateKeyPairResult> extractor);

    /**
     * Performs the <code>CreateVpcPeeringConnection</code> action.
     *
     * <p>
     *
     * @return The <code>VpcPeeringConnection</code> resource object associated
     *         with the result of this action.
     * @see CreateVpcPeeringConnectionRequest
     */
    com.amazonaws.resources.ec2.VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request);

    /**
     * Performs the <code>CreateVpcPeeringConnection</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>VpcPeeringConnection</code> resource object associated
     *         with the result of this action.
     * @see CreateVpcPeeringConnectionRequest
     */
    com.amazonaws.resources.ec2.VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request,
            ResultCapture<CreateVpcPeeringConnectionResult> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<Tag> createTags(CreateTagsRequest request);

    /**
     * Performs the <code>CreateTags</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<Tag> createTags(CreateTagsRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest)
     */
    List<Tag> createTags(List<com.amazonaws.services.ec2.model.Tag> tags,
            List<String> resources);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest, ResultCapture)
     */
    List<Tag> createTags(List<com.amazonaws.services.ec2.model.Tag> tags,
            List<String> resources, ResultCapture<Void> extractor);

    /**
     * Performs the <code>RegisterImage</code> action.
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see RegisterImageRequest
     */
    com.amazonaws.resources.ec2.Image registerImage(RegisterImageRequest request
            );

    /**
     * Performs the <code>RegisterImage</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see RegisterImageRequest
     */
    com.amazonaws.resources.ec2.Image registerImage(RegisterImageRequest request
            , ResultCapture<RegisterImageResult> extractor);

    /**
     * The convenient method form for the <code>RegisterImage</code> action.
     *
     * @see #registerImage(RegisterImageRequest)
     */
    com.amazonaws.resources.ec2.Image registerImage(String imageLocation);

    /**
     * The convenient method form for the <code>RegisterImage</code> action.
     *
     * @see #registerImage(RegisterImageRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Image registerImage(String imageLocation,
            ResultCapture<RegisterImageResult> extractor);

    /**
     * Performs the <code>CreateSnapshot</code> action.
     *
     * <p>
     *
     * @return The <code>Snapshot</code> resource object associated with the
     *         result of this action.
     * @see CreateSnapshotRequest
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(CreateSnapshotRequest
            request);

    /**
     * Performs the <code>CreateSnapshot</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Snapshot</code> resource object associated with the
     *         result of this action.
     * @see CreateSnapshotRequest
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(CreateSnapshotRequest
            request, ResultCapture<CreateSnapshotResult> extractor);

    /**
     * The convenient method form for the <code>CreateSnapshot</code> action.
     *
     * @see #createSnapshot(CreateSnapshotRequest)
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(String description,
            String volumeId);

    /**
     * The convenient method form for the <code>CreateSnapshot</code> action.
     *
     * @see #createSnapshot(CreateSnapshotRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Snapshot createSnapshot(String description,
            String volumeId, ResultCapture<CreateSnapshotResult> extractor);
}
