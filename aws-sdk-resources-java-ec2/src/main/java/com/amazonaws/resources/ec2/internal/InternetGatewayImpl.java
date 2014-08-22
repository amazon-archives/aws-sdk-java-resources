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
import com.amazonaws.resources.ec2.InternetGateway;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DetachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.InternetGatewayAttachment;
import com.amazonaws.services.ec2.model.Tag;

class InternetGatewayImpl implements InternetGateway {
    public static final ResourceCodec<InternetGateway> CODEC = new Codec();

    private final ResourceImpl resource;

    public InternetGatewayImpl(ResourceImpl resource) {
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
    public boolean load(DescribeInternetGatewaysRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeInternetGatewaysRequest request,
            ResultCapture<DescribeInternetGatewaysResult> extractor) {

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
    public List<InternetGatewayAttachment> getAttachments() {
        return (List<InternetGatewayAttachment>)
                resource.getAttribute("Attachments");
    }

    @Override
    public void detachFromVpc(DetachInternetGatewayRequest request) {
        detachFromVpc(request, null);
    }

    @Override
    public void detachFromVpc(DetachInternetGatewayRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("DetachFromVpc", request, extractor);
    }

    @Override
    public void delete(DeleteInternetGatewayRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteInternetGatewayRequest request, ResultCapture<Void>
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

    @Override
    public void attachToVpc(AttachInternetGatewayRequest request) {
        attachToVpc(request, null);
    }

    @Override
    public void attachToVpc(AttachInternetGatewayRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AttachToVpc", request, extractor);
    }

    private static class Codec implements ResourceCodec<InternetGateway> {
        @Override
        public InternetGateway transform(ResourceImpl resource) {
            return new InternetGatewayImpl(resource);
        }
    }
}
