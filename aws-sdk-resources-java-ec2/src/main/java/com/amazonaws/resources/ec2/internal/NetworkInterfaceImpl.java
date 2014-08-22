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
import com.amazonaws.resources.ec2.NetworkInterface;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AssignPrivateIpAddressesRequest;
import com.amazonaws.services.ec2.model.AttachNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.AttachNetworkInterfaceResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfaceAttributeRequest
;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfaceAttributeResult;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesResult;
import com.amazonaws.services.ec2.model.DetachNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.ModifyNetworkInterfaceAttributeRequest;
import com.amazonaws.services.ec2.model.NetworkInterfaceAssociation;
import com.amazonaws.services.ec2.model.NetworkInterfaceAttachment;
import com.amazonaws.services.ec2.model.NetworkInterfacePrivateIpAddress;
import com.amazonaws.services.ec2.model.ResetNetworkInterfaceAttributeRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.UnassignPrivateIpAddressesRequest;

class NetworkInterfaceImpl implements NetworkInterface {
    public static final ResourceCodec<NetworkInterface> CODEC = new Codec();

    private final ResourceImpl resource;

    public NetworkInterfaceImpl(ResourceImpl resource) {
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
    public boolean load(DescribeNetworkInterfacesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeNetworkInterfacesRequest request,
            ResultCapture<DescribeNetworkInterfacesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public String getDescription() {
        return (String) resource.getAttribute("Description");
    }

    @Override
    public NetworkInterfaceAttachment getAttachment() {
        return (NetworkInterfaceAttachment) resource.getAttribute("Attachment");
    }

    @Override
    public Boolean getSourceDestCheck() {
        return (Boolean) resource.getAttribute("SourceDestCheck");
    }

    @Override
    public NetworkInterfaceAssociation getAssociation() {
        return (NetworkInterfaceAssociation)
                resource.getAttribute("Association");
    }

    @Override
    public String getOwnerId() {
        return (String) resource.getAttribute("OwnerId");
    }

    @Override
    public String getAvailabilityZone() {
        return (String) resource.getAttribute("AvailabilityZone");
    }

    @Override
    public List<Tag> getTagSet() {
        return (List<Tag>) resource.getAttribute("TagSet");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public List<GroupIdentifier> getGroups() {
        return (List<GroupIdentifier>) resource.getAttribute("Groups");
    }

    @Override
    public String getStatus() {
        return (String) resource.getAttribute("Status");
    }

    @Override
    public String getMacAddress() {
        return (String) resource.getAttribute("MacAddress");
    }

    @Override
    public List<NetworkInterfacePrivateIpAddress> getPrivateIpAddresses() {
        return (List<NetworkInterfacePrivateIpAddress>)
                resource.getAttribute("PrivateIpAddresses");
    }

    @Override
    public String getPrivateDnsName() {
        return (String) resource.getAttribute("PrivateDnsName");
    }

    @Override
    public String getRequesterId() {
        return (String) resource.getAttribute("RequesterId");
    }

    @Override
    public String getSubnetId() {
        return (String) resource.getAttribute("SubnetId");
    }

    @Override
    public Boolean getRequesterManaged() {
        return (Boolean) resource.getAttribute("RequesterManaged");
    }

    @Override
    public String getPrivateIpAddress() {
        return (String) resource.getAttribute("PrivateIpAddress");
    }

    @Override
    public Vpc getVpc() {
        ResourceImpl result = resource.getReference("Vpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public Subnet getSubnet() {
        ResourceImpl result = resource.getReference("Subnet");
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public void unassignPrivateIpAddresses(UnassignPrivateIpAddressesRequest
            request) {

        unassignPrivateIpAddresses(request, null);
    }

    @Override
    public void unassignPrivateIpAddresses(UnassignPrivateIpAddressesRequest
            request, ResultCapture<Void> extractor) {

        resource.performAction("UnassignPrivateIpAddresses", request,
                extractor);
    }

    @Override
    public void detach(DetachNetworkInterfaceRequest request) {
        detach(request, null);
    }

    @Override
    public void detach(DetachNetworkInterfaceRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Detach", request, extractor);
    }

    @Override
    public void resetAttribute(ResetNetworkInterfaceAttributeRequest request) {
        resetAttribute(request, null);
    }

    @Override
    public void resetAttribute(ResetNetworkInterfaceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetAttribute", request, extractor);
    }

    @Override
    public void modifyAttribute(ModifyNetworkInterfaceAttributeRequest request)
            {

        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifyNetworkInterfaceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ModifyAttribute", request, extractor);
    }

    @Override
    public DescribeNetworkInterfaceAttributeResult describeAttribute(
            DescribeNetworkInterfaceAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeNetworkInterfaceAttributeResult describeAttribute(
            DescribeNetworkInterfaceAttributeRequest request,
            ResultCapture<DescribeNetworkInterfaceAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeNetworkInterfaceAttributeResult) result.getData();
    }

    @Override
    public AttachNetworkInterfaceResult attach(AttachNetworkInterfaceRequest
            request) {

        return attach(request, null);
    }

    @Override
    public AttachNetworkInterfaceResult attach(AttachNetworkInterfaceRequest
            request, ResultCapture<AttachNetworkInterfaceResult> extractor) {

        ActionResult result = resource.performAction("Attach", request,
                extractor);

        if (result == null) return null;
        return (AttachNetworkInterfaceResult) result.getData();
    }

    @Override
    public void delete(DeleteNetworkInterfaceRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteNetworkInterfaceRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void assignPrivateIpAddresses(AssignPrivateIpAddressesRequest request
            ) {

        assignPrivateIpAddresses(request, null);
    }

    @Override
    public void assignPrivateIpAddresses(AssignPrivateIpAddressesRequest request
            , ResultCapture<Void> extractor) {

        resource.performAction("AssignPrivateIpAddresses", request, extractor);
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

    private static class Codec implements ResourceCodec<NetworkInterface> {
        @Override
        public NetworkInterface transform(ResourceImpl resource) {
            return new NetworkInterfaceImpl(resource);
        }
    }
}
