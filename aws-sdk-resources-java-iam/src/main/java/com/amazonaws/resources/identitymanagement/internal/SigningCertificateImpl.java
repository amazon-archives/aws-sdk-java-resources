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
import com.amazonaws.resources.identitymanagement.SigningCertificate;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import
com.amazonaws.services.identitymanagement.model.DeleteSigningCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.UpdateSigningCertificateRequest;

class SigningCertificateImpl implements SigningCertificate {
    public static final ResourceCodec<SigningCertificate> CODEC = new Codec();

    private final ResourceImpl resource;

    public SigningCertificateImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public Date getUploadDate() {
        return (Date) resource.getAttribute("UploadDate");
    }

    @Override
    public String getStatus() {
        return (String) resource.getAttribute("Status");
    }

    @Override
    public String getCertificateBody() {
        return (String) resource.getAttribute("CertificateBody");
    }

    @Override
    public String getUserName() {
        return (String) resource.getAttribute("UserName");
    }

    @Override
    public void deactivate(UpdateSigningCertificateRequest request) {
        deactivate(request, null);
    }

    @Override
    public void deactivate(UpdateSigningCertificateRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Deactivate", request, extractor);
    }

    @Override
    public void activate(UpdateSigningCertificateRequest request) {
        activate(request, null);
    }

    @Override
    public void activate(UpdateSigningCertificateRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Activate", request, extractor);
    }

    @Override
    public void delete(DeleteSigningCertificateRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteSigningCertificateRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<SigningCertificate> {
        @Override
        public SigningCertificate transform(ResourceImpl resource) {
            return new SigningCertificateImpl(resource);
        }
    }
}
