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

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.Tag;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.DeleteTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsResult;

class TagImpl implements Tag {
    public static final ResourceCodec<Tag> CODEC = new Codec();

    private final ResourceImpl resource;

    public TagImpl(ResourceImpl resource) {
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
    public boolean load(DescribeTagsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeTagsRequest request,
            ResultCapture<DescribeTagsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getValue() {
        return (String) resource.getIdentifier("Value");
    }

    @Override
    public String getKey() {
        return (String) resource.getIdentifier("Key");
    }

    @Override
    public String getResourceId() {
        return (String) resource.getIdentifier("ResourceId");
    }

    @Override
    public String getResourceType() {
        return (String) resource.getAttribute("ResourceType");
    }

    @Override
    public void delete(DeleteTagsRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteTagsRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<Tag> {
        @Override
        public Tag transform(ResourceImpl resource) {
            return new TagImpl(resource);
        }
    }
}
