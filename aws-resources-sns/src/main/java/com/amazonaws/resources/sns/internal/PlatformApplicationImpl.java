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
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sns.PlatformApplication;
import com.amazonaws.resources.sns.PlatformEndpoint;
import com.amazonaws.resources.sns.PlatformEndpointCollection;
import com.amazonaws.services.sns.model.CreatePlatformEndpointRequest;
import com.amazonaws.services.sns.model.CreatePlatformEndpointResult;
import com.amazonaws.services.sns.model.DeletePlatformApplicationRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesRequest;
import com.amazonaws.services.sns.model.GetPlatformApplicationAttributesResult;
import
com.amazonaws.services.sns.model.ListEndpointsByPlatformApplicationRequest;
import com.amazonaws.services.sns.model.SetPlatformApplicationAttributesRequest;

class PlatformApplicationImpl implements PlatformApplication {
    public static final ResourceCodec<PlatformApplication> CODEC = new Codec();

    private final ResourceImpl resource;

    public PlatformApplicationImpl(ResourceImpl resource) {
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
    public boolean load(GetPlatformApplicationAttributesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetPlatformApplicationAttributesRequest request,
            ResultCapture<GetPlatformApplicationAttributesResult> extractor) {

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
    public PlatformEndpointCollection getEndpoints() {
        return getEndpoints(null);
    }

    @Override
    public PlatformEndpointCollection getEndpoints(
            ListEndpointsByPlatformApplicationRequest request) {

        ResourceCollectionImpl result = resource.getCollection("Endpoints",
                request);

        if (result == null) return null;
        return new PlatformEndpointCollectionImpl(result);
    }

    @Override
    public PlatformEndpoint createPlatformEndpoint(CreatePlatformEndpointRequest
            request) {

        return createPlatformEndpoint(request, null);
    }

    @Override
    public PlatformEndpoint createPlatformEndpoint(CreatePlatformEndpointRequest
            request, ResultCapture<CreatePlatformEndpointResult> extractor) {

        ActionResult result = resource.performAction("CreatePlatformEndpoint",
                request, extractor);

        if (result == null) return null;
        return new PlatformEndpointImpl(result.getResource());
    }

    @Override
    public void setAttributes(SetPlatformApplicationAttributesRequest request) {
        setAttributes(request, null);
    }

    @Override
    public void setAttributes(SetPlatformApplicationAttributesRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("SetAttributes", request, extractor);
    }

    @Override
    public void delete(DeletePlatformApplicationRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeletePlatformApplicationRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<PlatformApplication> {
        @Override
        public PlatformApplication transform(ResourceImpl resource) {
            return new PlatformApplicationImpl(resource);
        }
    }
}
