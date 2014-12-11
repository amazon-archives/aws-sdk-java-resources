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
import com.amazonaws.resources.ec2.NetworkAcl;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.CreateNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkAclRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsResult;
import com.amazonaws.services.ec2.model.NetworkAclAssociation;
import com.amazonaws.services.ec2.model.NetworkAclEntry;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclAssociationRequest;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclAssociationResult;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.Tag;

class NetworkAclImpl implements NetworkAcl {
    public static final ResourceCodec<NetworkAcl> CODEC = new Codec();

    private final ResourceImpl resource;

    public NetworkAclImpl(ResourceImpl resource) {
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
    public boolean load(DescribeNetworkAclsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeNetworkAclsRequest request,
            ResultCapture<DescribeNetworkAclsResult> extractor) {

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
    public Boolean getIsDefault() {
        return (Boolean) resource.getAttribute("IsDefault");
    }

    @Override
    public List<NetworkAclAssociation> getAssociations() {
        return (List<NetworkAclAssociation>)
                resource.getAttribute("Associations");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public List<NetworkAclEntry> getEntries() {
        return (List<NetworkAclEntry>) resource.getAttribute("Entries");
    }

    @Override
    public Vpc getVpc() {
        ResourceImpl result = resource.getReference("Vpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public void createEntry(CreateNetworkAclEntryRequest request) {
        createEntry(request, null);
    }

    @Override
    public void createEntry(CreateNetworkAclEntryRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("CreateEntry", request, extractor);
    }

    @Override
    public void replaceEntry(ReplaceNetworkAclEntryRequest request) {
        replaceEntry(request, null);
    }

    @Override
    public void replaceEntry(ReplaceNetworkAclEntryRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ReplaceEntry", request, extractor);
    }

    @Override
    public void delete(DeleteNetworkAclRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteNetworkAclRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public ReplaceNetworkAclAssociationResult replaceAssociation(
            ReplaceNetworkAclAssociationRequest request) {

        return replaceAssociation(request, null);
    }

    @Override
    public ReplaceNetworkAclAssociationResult replaceAssociation(
            ReplaceNetworkAclAssociationRequest request,
            ResultCapture<ReplaceNetworkAclAssociationResult> extractor) {

        ActionResult result = resource.performAction("ReplaceAssociation",
                request, extractor);

        if (result == null) return null;
        return (ReplaceNetworkAclAssociationResult) result.getData();
    }

    @Override
    public void deleteEntry(DeleteNetworkAclEntryRequest request) {
        deleteEntry(request, null);
    }

    @Override
    public void deleteEntry(DeleteNetworkAclEntryRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("DeleteEntry", request, extractor);
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

    private static class Codec implements ResourceCodec<NetworkAcl> {
        @Override
        public NetworkAcl transform(ResourceImpl resource) {
            return new NetworkAclImpl(resource);
        }
    }
}
