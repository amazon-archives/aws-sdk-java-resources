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
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AssociateDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsResult;
import com.amazonaws.services.ec2.model.DhcpConfiguration;
import com.amazonaws.services.ec2.model.Tag;

class DhcpOptionsImpl implements DhcpOptions {
    public static final ResourceCodec<DhcpOptions> CODEC = new Codec();

    private final ResourceImpl resource;

    public DhcpOptionsImpl(ResourceImpl resource) {
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
    public boolean load(DescribeDhcpOptionsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeDhcpOptionsRequest request,
            ResultCapture<DescribeDhcpOptionsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public List<DhcpConfiguration> getDhcpConfigurations() {
        return (List<DhcpConfiguration>)
                resource.getAttribute("DhcpConfigurations");
    }

    @Override
    public List<Tag> getTags() {
        return (List<Tag>) resource.getAttribute("Tags");
    }

    @Override
    public void delete(DeleteDhcpOptionsRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteDhcpOptionsRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteDhcpOptionsRequest request = new DeleteDhcpOptionsRequest();
        delete(request, extractor);
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
    public void associateWithVpc(AssociateDhcpOptionsRequest request) {
        associateWithVpc(request, null);
    }

    @Override
    public void associateWithVpc(AssociateDhcpOptionsRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AssociateWithVpc", request, extractor);
    }

    private static class Codec implements ResourceCodec<DhcpOptions> {
        @Override
        public DhcpOptions transform(ResourceImpl resource) {
            return new DhcpOptionsImpl(resource);
        }
    }
}
