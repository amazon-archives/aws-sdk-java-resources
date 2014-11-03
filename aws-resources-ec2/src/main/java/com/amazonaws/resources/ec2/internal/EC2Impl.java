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

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.DhcpOptions;
import com.amazonaws.resources.ec2.DhcpOptionsCollection;
import com.amazonaws.resources.ec2.EC2;
import com.amazonaws.resources.ec2.Image;
import com.amazonaws.resources.ec2.ImageCollection;
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.InstanceCollection;
import com.amazonaws.resources.ec2.InternetGateway;
import com.amazonaws.resources.ec2.InternetGatewayCollection;
import com.amazonaws.resources.ec2.KeyPair;
import com.amazonaws.resources.ec2.KeyPairCollection;
import com.amazonaws.resources.ec2.NetworkAcl;
import com.amazonaws.resources.ec2.NetworkAclCollection;
import com.amazonaws.resources.ec2.NetworkInterface;
import com.amazonaws.resources.ec2.NetworkInterfaceCollection;
import com.amazonaws.resources.ec2.PlacementGroup;
import com.amazonaws.resources.ec2.PlacementGroupCollection;
import com.amazonaws.resources.ec2.RouteTable;
import com.amazonaws.resources.ec2.RouteTableAssociation;
import com.amazonaws.resources.ec2.RouteTableCollection;
import com.amazonaws.resources.ec2.SecurityGroup;
import com.amazonaws.resources.ec2.SecurityGroupCollection;
import com.amazonaws.resources.ec2.Snapshot;
import com.amazonaws.resources.ec2.SnapshotCollection;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.SubnetCollection;
import com.amazonaws.resources.ec2.Tag;
import com.amazonaws.resources.ec2.Volume;
import com.amazonaws.resources.ec2.VolumeCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.ec2.VpcCollection;
import com.amazonaws.resources.ec2.VpcPeeringConnection;
import com.amazonaws.resources.ec2.VpcPeeringConnectionCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.V1ModelLoader;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
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

public class EC2Impl implements EC2 {

    private final ServiceImpl<AmazonEC2> service;

    /**
     * Construct a service implementation using the specified client object.
     *
     * @param client The low-level client which the service implementation will
     *         use to make API calls.
     */
    public EC2Impl(AmazonEC2Client client) {
        this(client, null);
    }

    /**
     * Construct a service implementation using the specified client object and
     * AWS region enum.
     *
     * @param client The low-level client which the service implementation will
     *         use to make API calls.
     * @param region The AWS region where the service API calls will be sent to.
     */
    public EC2Impl(AmazonEC2Client client, Regions region) {
        if (region != null) {
            client.setRegion(Region.getRegion(region));
        }

        ServiceModel model = V1ModelLoader.load(EC2.class,
                EC2.class.getAnnotation(V1ServiceInterface.class).model());

        this.service = new ServiceImpl<AmazonEC2>(model, client);
    }

    public EC2Impl(ServiceImpl<AmazonEC2> service) {
        this.service = service;
    }

    @Override
    public AmazonEC2 client() {
        return service.getClient();
    }

    @Override
    public KeyPair getKeyPair(String name) {
        ResourceImpl result = service.getSubResource("KeyPair", name);
        if (result == null) return null;
        return new KeyPairImpl(result);
    }

    @Override
    public RouteTable getRouteTable(String id) {
        ResourceImpl result = service.getSubResource("RouteTable", id);
        if (result == null) return null;
        return new RouteTableImpl(result);
    }

    @Override
    public Instance getInstance(String id) {
        ResourceImpl result = service.getSubResource("Instance", id);
        if (result == null) return null;
        return new InstanceImpl(result);
    }

    @Override
    public NetworkInterface getNetworkInterface(String id) {
        ResourceImpl result = service.getSubResource("NetworkInterface", id);
        if (result == null) return null;
        return new NetworkInterfaceImpl(result);
    }

    @Override
    public Volume getVolume(String id) {
        ResourceImpl result = service.getSubResource("Volume", id);
        if (result == null) return null;
        return new VolumeImpl(result);
    }

    @Override
    public DhcpOptions getDhcpOptions(String id) {
        ResourceImpl result = service.getSubResource("DhcpOptions", id);
        if (result == null) return null;
        return new DhcpOptionsImpl(result);
    }

    @Override
    public PlacementGroup getPlacementGroup(String name) {
        ResourceImpl result = service.getSubResource("PlacementGroup", name);
        if (result == null) return null;
        return new PlacementGroupImpl(result);
    }

    @Override
    public InternetGateway getInternetGateway(String id) {
        ResourceImpl result = service.getSubResource("InternetGateway", id);
        if (result == null) return null;
        return new InternetGatewayImpl(result);
    }

    @Override
    public RouteTableAssociation getRouteTableAssociation(String id) {
        ResourceImpl result = service.getSubResource("RouteTableAssociation",
                id);

        if (result == null) return null;
        return new RouteTableAssociationImpl(result);
    }

    @Override
    public Subnet getSubnet(String id) {
        ResourceImpl result = service.getSubResource("Subnet", id);
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public VpcPeeringConnection getVpcPeeringConnection(String id) {
        ResourceImpl result = service.getSubResource("VpcPeeringConnection",
                id);

        if (result == null) return null;
        return new VpcPeeringConnectionImpl(result);
    }

    @Override
    public Snapshot getSnapshot(String id) {
        ResourceImpl result = service.getSubResource("Snapshot", id);
        if (result == null) return null;
        return new SnapshotImpl(result);
    }

    @Override
    public Vpc getVpc(String id) {
        ResourceImpl result = service.getSubResource("Vpc", id);
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public SecurityGroup getSecurityGroup(String id) {
        ResourceImpl result = service.getSubResource("SecurityGroup", id);
        if (result == null) return null;
        return new SecurityGroupImpl(result);
    }

    @Override
    public Image getImage(String id) {
        ResourceImpl result = service.getSubResource("Image", id);
        if (result == null) return null;
        return new ImageImpl(result);
    }

    @Override
    public NetworkAcl getNetworkAcl(String id) {
        ResourceImpl result = service.getSubResource("NetworkAcl", id);
        if (result == null) return null;
        return new NetworkAclImpl(result);
    }

    @Override
    public VpcPeeringConnectionCollection getVpcPeeringConnections() {
        return
                getVpcPeeringConnections((DescribeVpcPeeringConnectionsRequest)null);
    }

    @Override
    public VpcPeeringConnectionCollection getVpcPeeringConnections(
            DescribeVpcPeeringConnectionsRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("VpcPeeringConnections", request);

        if (result == null) return null;
        return new VpcPeeringConnectionCollectionImpl(result);
    }

    @Override
    public InternetGatewayCollection getInternetGateways() {
        return getInternetGateways((DescribeInternetGatewaysRequest)null);
    }

    @Override
    public InternetGatewayCollection getInternetGateways(
            DescribeInternetGatewaysRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("InternetGateways", request);

        if (result == null) return null;
        return new InternetGatewayCollectionImpl(result);
    }

    @Override
    public KeyPairCollection getKeyPairs() {
        return getKeyPairs((DescribeKeyPairsRequest)null);
    }

    @Override
    public KeyPairCollection getKeyPairs(DescribeKeyPairsRequest request) {
        ResourceCollectionImpl result = service.getCollection("KeyPairs",
                request);

        if (result == null) return null;
        return new KeyPairCollectionImpl(result);
    }

    @Override
    public DhcpOptionsCollection getDhcpOptionsSets() {
        return getDhcpOptionsSets((DescribeDhcpOptionsRequest)null);
    }

    @Override
    public DhcpOptionsCollection getDhcpOptionsSets(DescribeDhcpOptionsRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("DhcpOptionsSets",
                request);

        if (result == null) return null;
        return new DhcpOptionsCollectionImpl(result);
    }

    @Override
    public NetworkInterfaceCollection getNetworkInterfaces() {
        return getNetworkInterfaces((DescribeNetworkInterfacesRequest)null);
    }

    @Override
    public NetworkInterfaceCollection getNetworkInterfaces(
            DescribeNetworkInterfacesRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("NetworkInterfaces", request);

        if (result == null) return null;
        return new NetworkInterfaceCollectionImpl(result);
    }

    @Override
    public InstanceCollection getInstances() {
        return getInstances((DescribeInstancesRequest)null);
    }

    @Override
    public InstanceCollection getInstances(DescribeInstancesRequest request) {
        ResourceCollectionImpl result = service.getCollection("Instances",
                request);

        if (result == null) return null;
        return new InstanceCollectionImpl(result);
    }

    @Override
    public SecurityGroupCollection getSecurityGroups() {
        return getSecurityGroups((DescribeSecurityGroupsRequest)null);
    }

    @Override
    public SecurityGroupCollection getSecurityGroups(
            DescribeSecurityGroupsRequest request) {

        ResourceCollectionImpl result = service.getCollection("SecurityGroups",
                request);

        if (result == null) return null;
        return new SecurityGroupCollectionImpl(result);
    }

    @Override
    public ImageCollection getImages() {
        return getImages((DescribeImagesRequest)null);
    }

    @Override
    public ImageCollection getImages(DescribeImagesRequest request) {
        ResourceCollectionImpl result = service.getCollection("Images",
                request);

        if (result == null) return null;
        return new ImageCollectionImpl(result);
    }

    @Override
    public VpcCollection getVpcs() {
        return getVpcs((DescribeVpcsRequest)null);
    }

    @Override
    public VpcCollection getVpcs(DescribeVpcsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Vpcs", request);
        if (result == null) return null;
        return new VpcCollectionImpl(result);
    }

    @Override
    public SubnetCollection getSubnets() {
        return getSubnets((DescribeSubnetsRequest)null);
    }

    @Override
    public SubnetCollection getSubnets(DescribeSubnetsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Subnets",
                request);

        if (result == null) return null;
        return new SubnetCollectionImpl(result);
    }

    @Override
    public SnapshotCollection getSnapshots() {
        return getSnapshots((DescribeSnapshotsRequest)null);
    }

    @Override
    public SnapshotCollection getSnapshots(DescribeSnapshotsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Snapshots",
                request);

        if (result == null) return null;
        return new SnapshotCollectionImpl(result);
    }

    @Override
    public PlacementGroupCollection getPlacementGroups() {
        return getPlacementGroups((DescribePlacementGroupsRequest)null);
    }

    @Override
    public PlacementGroupCollection getPlacementGroups(
            DescribePlacementGroupsRequest request) {

        ResourceCollectionImpl result = service.getCollection("PlacementGroups",
                request);

        if (result == null) return null;
        return new PlacementGroupCollectionImpl(result);
    }

    @Override
    public RouteTableCollection getRouteTables() {
        return getRouteTables((DescribeRouteTablesRequest)null);
    }

    @Override
    public RouteTableCollection getRouteTables(DescribeRouteTablesRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("RouteTables",
                request);

        if (result == null) return null;
        return new RouteTableCollectionImpl(result);
    }

    @Override
    public NetworkAclCollection getNetworkAcls() {
        return getNetworkAcls((DescribeNetworkAclsRequest)null);
    }

    @Override
    public NetworkAclCollection getNetworkAcls(DescribeNetworkAclsRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("NetworkAcls",
                request);

        if (result == null) return null;
        return new NetworkAclCollectionImpl(result);
    }

    @Override
    public VolumeCollection getVolumes() {
        return getVolumes((DescribeVolumesRequest)null);
    }

    @Override
    public VolumeCollection getVolumes(DescribeVolumesRequest request) {
        ResourceCollectionImpl result = service.getCollection("Volumes",
                request);

        if (result == null) return null;
        return new VolumeCollectionImpl(result);
    }

    @Override
    public SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request)
            {

        return createSecurityGroup(request, null);
    }

    @Override
    public SecurityGroup createSecurityGroup(CreateSecurityGroupRequest request,
            ResultCapture<CreateSecurityGroupResult> extractor) {

        ActionResult result = service.performAction("CreateSecurityGroup",
                request, extractor);

        if (result == null) return null;
        return new SecurityGroupImpl(result.getResource());
    }

    @Override
    public SecurityGroup createSecurityGroup(String description, String
            groupName) {

        return createSecurityGroup(description, groupName,
                (ResultCapture<CreateSecurityGroupResult>)null);
    }

    @Override
    public SecurityGroup createSecurityGroup(String description, String
            groupName, ResultCapture<CreateSecurityGroupResult> extractor) {

        CreateSecurityGroupRequest request = new CreateSecurityGroupRequest()
            .withDescription(description)
            .withGroupName(groupName);
        return createSecurityGroup(request, extractor);
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
    public Subnet createSubnet(String cidrBlock, String vpcId) {
        return createSubnet(cidrBlock, vpcId,
                (ResultCapture<CreateSubnetResult>)null);
    }

    @Override
    public Subnet createSubnet(String cidrBlock, String vpcId,
            ResultCapture<CreateSubnetResult> extractor) {

        CreateSubnetRequest request = new CreateSubnetRequest()
            .withCidrBlock(cidrBlock)
            .withVpcId(vpcId);
        return createSubnet(request, extractor);
    }

    @Override
    public InternetGateway createInternetGateway(CreateInternetGatewayRequest
            request) {

        return createInternetGateway(request, null);
    }

    @Override
    public InternetGateway createInternetGateway(CreateInternetGatewayRequest
            request, ResultCapture<CreateInternetGatewayResult> extractor) {

        ActionResult result = service.performAction("CreateInternetGateway",
                request, extractor);

        if (result == null) return null;
        return new InternetGatewayImpl(result.getResource());
    }

    @Override
    public NetworkAcl createNetworkAcl(CreateNetworkAclRequest request) {
        return createNetworkAcl(request, null);
    }

    @Override
    public NetworkAcl createNetworkAcl(CreateNetworkAclRequest request,
            ResultCapture<CreateNetworkAclResult> extractor) {

        ActionResult result = service.performAction("CreateNetworkAcl", request,
                extractor);

        if (result == null) return null;
        return new NetworkAclImpl(result.getResource());
    }

    @Override
    public KeyPair importKeyPair(ImportKeyPairRequest request) {
        return importKeyPair(request, null);
    }

    @Override
    public KeyPair importKeyPair(ImportKeyPairRequest request,
            ResultCapture<ImportKeyPairResult> extractor) {

        ActionResult result = service.performAction("ImportKeyPair", request,
                extractor);

        if (result == null) return null;
        return new KeyPairImpl(result.getResource());
    }

    @Override
    public KeyPair importKeyPair(String publicKeyMaterial, String keyName) {
        return importKeyPair(publicKeyMaterial, keyName,
                (ResultCapture<ImportKeyPairResult>)null);
    }

    @Override
    public KeyPair importKeyPair(String publicKeyMaterial, String keyName,
            ResultCapture<ImportKeyPairResult> extractor) {

        ImportKeyPairRequest request = new ImportKeyPairRequest()
            .withPublicKeyMaterial(publicKeyMaterial)
            .withKeyName(keyName);
        return importKeyPair(request, extractor);
    }

    @Override
    public RouteTable createRouteTable(CreateRouteTableRequest request) {
        return createRouteTable(request, null);
    }

    @Override
    public RouteTable createRouteTable(CreateRouteTableRequest request,
            ResultCapture<CreateRouteTableResult> extractor) {

        ActionResult result = service.performAction("CreateRouteTable", request,
                extractor);

        if (result == null) return null;
        return new RouteTableImpl(result.getResource());
    }

    @Override
    public PlacementGroup createPlacementGroup(CreatePlacementGroupRequest
            request) {

        return createPlacementGroup(request, null);
    }

    @Override
    public PlacementGroup createPlacementGroup(CreatePlacementGroupRequest
            request, ResultCapture<Void> extractor) {

        ActionResult result = service.performAction("CreatePlacementGroup",
                request, extractor);

        if (result == null) return null;
        return new PlacementGroupImpl(result.getResource());
    }

    @Override
    public PlacementGroup createPlacementGroup(String groupName, String strategy
            ) {

        return createPlacementGroup(groupName, strategy,
                (ResultCapture<Void>)null);
    }

    @Override
    public PlacementGroup createPlacementGroup(String groupName, String strategy
            , ResultCapture<Void> extractor) {

        CreatePlacementGroupRequest request = new CreatePlacementGroupRequest()
            .withGroupName(groupName)
            .withStrategy(strategy);
        return createPlacementGroup(request, extractor);
    }

    @Override
    public DhcpOptions createDhcpOptions(CreateDhcpOptionsRequest request) {
        return createDhcpOptions(request, null);
    }

    @Override
    public DhcpOptions createDhcpOptions(CreateDhcpOptionsRequest request,
            ResultCapture<CreateDhcpOptionsResult> extractor) {

        ActionResult result = service.performAction("CreateDhcpOptions",
                request, extractor);

        if (result == null) return null;
        return new DhcpOptionsImpl(result.getResource());
    }

    @Override
    public DhcpOptions createDhcpOptions(List<DhcpConfiguration>
            dhcpConfigurations) {

        return createDhcpOptions(dhcpConfigurations,
                (ResultCapture<CreateDhcpOptionsResult>)null);
    }

    @Override
    public DhcpOptions createDhcpOptions(List<DhcpConfiguration>
            dhcpConfigurations, ResultCapture<CreateDhcpOptionsResult> extractor
            ) {

        CreateDhcpOptionsRequest request = new CreateDhcpOptionsRequest()
            .withDhcpConfigurations(dhcpConfigurations);
        return createDhcpOptions(request, extractor);
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

    @Override
    public List<Instance> createInstances(String imageId, Integer minCount,
            Integer maxCount) {

        return createInstances(imageId, minCount, maxCount,
                (ResultCapture<RunInstancesResult>)null);
    }

    @Override
    public List<Instance> createInstances(String imageId, Integer minCount,
            Integer maxCount, ResultCapture<RunInstancesResult> extractor) {

        RunInstancesRequest request = new RunInstancesRequest()
            .withImageId(imageId)
            .withMinCount(minCount)
            .withMaxCount(maxCount);
        return createInstances(request, extractor);
    }

    @Override
    public Image createVolume(CreateVolumeRequest request) {
        return createVolume(request, null);
    }

    @Override
    public Image createVolume(CreateVolumeRequest request,
            ResultCapture<CreateVolumeResult> extractor) {

        ActionResult result = service.performAction("CreateVolume", request,
                extractor);

        if (result == null) return null;
        return new ImageImpl(result.getResource());
    }

    @Override
    public Image createVolume(String availabilityZone, Integer size) {
        return createVolume(availabilityZone, size,
                (ResultCapture<CreateVolumeResult>)null);
    }

    @Override
    public Image createVolume(String availabilityZone, Integer size,
            ResultCapture<CreateVolumeResult> extractor) {

        CreateVolumeRequest request = new CreateVolumeRequest()
            .withAvailabilityZone(availabilityZone)
            .withSize(size);
        return createVolume(request, extractor);
    }

    @Override
    public Image createVolume(String snapshotId, String availabilityZone) {
        return createVolume(snapshotId, availabilityZone,
                (ResultCapture<CreateVolumeResult>)null);
    }

    @Override
    public Image createVolume(String snapshotId, String availabilityZone,
            ResultCapture<CreateVolumeResult> extractor) {

        CreateVolumeRequest request = new CreateVolumeRequest()
            .withSnapshotId(snapshotId)
            .withAvailabilityZone(availabilityZone);
        return createVolume(request, extractor);
    }

    @Override
    public void disassociateRouteTable(DisassociateRouteTableRequest request) {
        disassociateRouteTable(request, null);
    }

    @Override
    public void disassociateRouteTable(DisassociateRouteTableRequest request,
            ResultCapture<Void> extractor) {

        service.performAction("DisassociateRouteTable", request, extractor);
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
    public Vpc createVpc(String cidrBlock) {
        return createVpc(cidrBlock, (ResultCapture<CreateVpcResult>)null);
    }

    @Override
    public Vpc createVpc(String cidrBlock, ResultCapture<CreateVpcResult>
            extractor) {

        CreateVpcRequest request = new CreateVpcRequest()
            .withCidrBlock(cidrBlock);
        return createVpc(request, extractor);
    }

    @Override
    public NetworkInterface createNetworkInterface(CreateNetworkInterfaceRequest
            request) {

        return createNetworkInterface(request, null);
    }

    @Override
    public NetworkInterface createNetworkInterface(CreateNetworkInterfaceRequest
            request, ResultCapture<CreateNetworkInterfaceResult> extractor) {

        ActionResult result = service.performAction("CreateNetworkInterface",
                request, extractor);

        if (result == null) return null;
        return new NetworkInterfaceImpl(result.getResource());
    }

    @Override
    public KeyPair createKeyPair(CreateKeyPairRequest request) {
        return createKeyPair(request, null);
    }

    @Override
    public KeyPair createKeyPair(CreateKeyPairRequest request,
            ResultCapture<CreateKeyPairResult> extractor) {

        ActionResult result = service.performAction("CreateKeyPair", request,
                extractor);

        if (result == null) return null;
        return new KeyPairImpl(result.getResource());
    }

    @Override
    public KeyPair createKeyPair(String keyName) {
        return createKeyPair(keyName, (ResultCapture<CreateKeyPairResult>)null);
    }

    @Override
    public KeyPair createKeyPair(String keyName,
            ResultCapture<CreateKeyPairResult> extractor) {

        CreateKeyPairRequest request = new CreateKeyPairRequest()
            .withKeyName(keyName);
        return createKeyPair(request, extractor);
    }

    @Override
    public VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request) {

        return createVpcPeeringConnection(request, null);
    }

    @Override
    public VpcPeeringConnection createVpcPeeringConnection(
            CreateVpcPeeringConnectionRequest request,
            ResultCapture<CreateVpcPeeringConnectionResult> extractor) {

        ActionResult result =
                service.performAction("CreateVpcPeeringConnection", request,
                extractor);

        if (result == null) return null;
        return new VpcPeeringConnectionImpl(result.getResource());
    }

    @Override
    public List<Tag> createTags(CreateTagsRequest request) {
        return createTags(request, null);
    }

    @Override
    public List<Tag> createTags(CreateTagsRequest request, ResultCapture<Void>
            extractor) {

        ActionResult result = service.performAction("CreateTags", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), TagImpl.CODEC);
    }

    @Override
    public List<Tag> createTags(List<com.amazonaws.services.ec2.model.Tag> tags,
            List<String> resources) {

        return createTags(tags, resources, (ResultCapture<Void>)null);
    }

    @Override
    public List<Tag> createTags(List<com.amazonaws.services.ec2.model.Tag> tags,
            List<String> resources, ResultCapture<Void> extractor) {

        CreateTagsRequest request = new CreateTagsRequest()
            .withTags(tags)
            .withResources(resources);
        return createTags(request, extractor);
    }

    @Override
    public Image registerImage(RegisterImageRequest request) {
        return registerImage(request, null);
    }

    @Override
    public Image registerImage(RegisterImageRequest request,
            ResultCapture<RegisterImageResult> extractor) {

        ActionResult result = service.performAction("RegisterImage", request,
                extractor);

        if (result == null) return null;
        return new ImageImpl(result.getResource());
    }

    @Override
    public Image registerImage(String imageLocation) {
        return registerImage(imageLocation,
                (ResultCapture<RegisterImageResult>)null);
    }

    @Override
    public Image registerImage(String imageLocation,
            ResultCapture<RegisterImageResult> extractor) {

        RegisterImageRequest request = new RegisterImageRequest()
            .withImageLocation(imageLocation);
        return registerImage(request, extractor);
    }

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request) {
        return createSnapshot(request, null);
    }

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request,
            ResultCapture<CreateSnapshotResult> extractor) {

        ActionResult result = service.performAction("CreateSnapshot", request,
                extractor);

        if (result == null) return null;
        return new SnapshotImpl(result.getResource());
    }

    @Override
    public Snapshot createSnapshot(String description, String volumeId) {
        return createSnapshot(description, volumeId,
                (ResultCapture<CreateSnapshotResult>)null);
    }

    @Override
    public Snapshot createSnapshot(String description, String volumeId,
            ResultCapture<CreateSnapshotResult> extractor) {

        CreateSnapshotRequest request = new CreateSnapshotRequest()
            .withDescription(description)
            .withVolumeId(volumeId);
        return createSnapshot(request, extractor);
    }
}
