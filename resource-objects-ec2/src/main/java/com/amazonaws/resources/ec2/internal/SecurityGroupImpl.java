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
import com.amazonaws.resources.ec2.SecurityGroup;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupEgressRequest;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupEgressRequest;
import com.amazonaws.services.ec2.model.RevokeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.Tag;

class SecurityGroupImpl implements SecurityGroup {
    public static final ResourceCodec<SecurityGroup> CODEC = new Codec();

    private final ResourceImpl resource;

    public SecurityGroupImpl(ResourceImpl resource) {
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
    public boolean load(DescribeSecurityGroupsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeSecurityGroupsRequest request,
            ResultCapture<DescribeSecurityGroupsResult> extractor) {

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
    public List<IpPermission> getIpPermissions() {
        return (List<IpPermission>) resource.getAttribute("IpPermissions");
    }

    @Override
    public String getDescription() {
        return (String) resource.getAttribute("Description");
    }

    @Override
    public String getGroupName() {
        return (String) resource.getAttribute("GroupName");
    }

    @Override
    public String getOwnerId() {
        return (String) resource.getAttribute("OwnerId");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public List<IpPermission> getIpPermissionsEgress() {
        return (List<IpPermission>)
                resource.getAttribute("IpPermissionsEgress");
    }

    @Override
    public void revokeIngress(RevokeSecurityGroupIngressRequest request) {
        revokeIngress(request, null);
    }

    @Override
    public void revokeIngress(RevokeSecurityGroupIngressRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RevokeIngress", request, extractor);
    }

    @Override
    public void revokeEgress(RevokeSecurityGroupEgressRequest request) {
        revokeEgress(request, null);
    }

    @Override
    public void revokeEgress(RevokeSecurityGroupEgressRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RevokeEgress", request, extractor);
    }

    @Override
    public void authorizeEgress(AuthorizeSecurityGroupEgressRequest request) {
        authorizeEgress(request, null);
    }

    @Override
    public void authorizeEgress(AuthorizeSecurityGroupEgressRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AuthorizeEgress", request, extractor);
    }

    @Override
    public void authorizeIngress(AuthorizeSecurityGroupIngressRequest request) {
        authorizeIngress(request, null);
    }

    @Override
    public void authorizeIngress(AuthorizeSecurityGroupIngressRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AuthorizeIngress", request, extractor);
    }

    @Override
    public void delete(DeleteSecurityGroupRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteSecurityGroupRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
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

    private static class Codec implements ResourceCodec<SecurityGroup> {
        @Override
        public SecurityGroup transform(ResourceImpl resource) {
            return new SecurityGroupImpl(resource);
        }
    }
}
