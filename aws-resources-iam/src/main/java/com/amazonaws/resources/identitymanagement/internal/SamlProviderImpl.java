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
package com.amazonaws.resources.identitymanagement.internal;

import java.util.Date;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.SamlProvider;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteSAMLProviderRequest
;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderRequest;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderResult;
import com.amazonaws.services.identitymanagement.model.UpdateSAMLProviderRequest
;
import com.amazonaws.services.identitymanagement.model.UpdateSAMLProviderResult;

class SamlProviderImpl implements SamlProvider {
    public static final ResourceCodec<SamlProvider> CODEC = new Codec();

    private final ResourceImpl resource;

    public SamlProviderImpl(ResourceImpl resource) {
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
    public boolean load(GetSAMLProviderRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetSAMLProviderRequest request,
            ResultCapture<GetSAMLProviderResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getArn() {
        return (String) resource.getIdentifier("Arn");
    }

    @Override
    public Date getValidUntil() {
        return (Date) resource.getAttribute("ValidUntil");
    }

    @Override
    public String getSAMLMetadataDocument() {
        return (String) resource.getAttribute("SAMLMetadataDocument");
    }

    @Override
    public Date getCreateDate() {
        return (Date) resource.getAttribute("CreateDate");
    }

    @Override
    public UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request) {
        return update(request, null);
    }

    @Override
    public UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request,
            ResultCapture<UpdateSAMLProviderResult> extractor) {

        ActionResult result = resource.performAction("Update", request,
                extractor);

        if (result == null) return null;
        return (UpdateSAMLProviderResult) result.getData();
    }

    @Override
    public void delete(DeleteSAMLProviderRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteSAMLProviderRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<SamlProvider> {
        @Override
        public SamlProvider transform(ResourceImpl resource) {
            return new SamlProviderImpl(resource);
        }
    }
}
