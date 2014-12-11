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
package com.amazonaws.resources.sns.internal;

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sns.PlatformEndpoint;
import com.amazonaws.services.sns.model.DeleteEndpointRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesRequest;
import com.amazonaws.services.sns.model.GetEndpointAttributesResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SetEndpointAttributesRequest;

class PlatformEndpointImpl implements PlatformEndpoint {
    public static final ResourceCodec<PlatformEndpoint> CODEC = new Codec();

    private final ResourceImpl resource;

    public PlatformEndpointImpl(ResourceImpl resource) {
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
    public boolean load(GetEndpointAttributesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetEndpointAttributesRequest request,
            ResultCapture<GetEndpointAttributesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getArn() {
        return (String) resource.getIdentifier("Arn");
    }

    @Override
    public Map<String, String> getAttributes() {
        return (Map<String, String>) resource.getAttribute("Attributes");
    }

    @Override
    public PublishResult publish(PublishRequest request) {
        return publish(request, null);
    }

    @Override
    public PublishResult publish(PublishRequest request,
            ResultCapture<PublishResult> extractor) {

        ActionResult result = resource.performAction("Publish", request,
                extractor);

        if (result == null) return null;
        return (PublishResult) result.getData();
    }

    @Override
    public void setAttributes(SetEndpointAttributesRequest request) {
        setAttributes(request, null);
    }

    @Override
    public void setAttributes(SetEndpointAttributesRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("SetAttributes", request, extractor);
    }

    @Override
    public void delete(DeleteEndpointRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteEndpointRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<PlatformEndpoint> {
        @Override
        public PlatformEndpoint transform(ResourceImpl resource) {
            return new PlatformEndpointImpl(resource);
        }
    }
}
