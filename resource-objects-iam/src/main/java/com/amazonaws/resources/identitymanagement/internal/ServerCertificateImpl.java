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

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.ServerCertificate;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import
com.amazonaws.services.identitymanagement.model.DeleteServerCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.GetServerCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.GetServerCertificateResult;
import com.amazonaws.services.identitymanagement.model.ServerCertificateMetadata
;
import
com.amazonaws.services.identitymanagement.model.UpdateServerCertificateRequest;

class ServerCertificateImpl implements ServerCertificate {
    public static final ResourceCodec<ServerCertificate> CODEC = new Codec();

    private final ResourceImpl resource;

    public ServerCertificateImpl(ResourceImpl resource) {
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
    public boolean load(GetServerCertificateRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetServerCertificateRequest request,
            ResultCapture<GetServerCertificateResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public ServerCertificateMetadata getServerCertificateMetadata() {
        return (ServerCertificateMetadata)
                resource.getAttribute("ServerCertificateMetadata");
    }

    @Override
    public String getCertificateBody() {
        return (String) resource.getAttribute("CertificateBody");
    }

    @Override
    public String getCertificateChain() {
        return (String) resource.getAttribute("CertificateChain");
    }

    @Override
    public ServerCertificate update(UpdateServerCertificateRequest request) {
        return update(request, null);
    }

    @Override
    public ServerCertificate update(UpdateServerCertificateRequest request,
            ResultCapture<Void> extractor) {

        ActionResult result = resource.performAction("Update", request,
                extractor);

        if (result == null) return null;
        return new ServerCertificateImpl(result.getResource());
    }

    @Override
    public ServerCertificate update() {
        return update((ResultCapture<Void>)null);
    }

    @Override
    public ServerCertificate update(ResultCapture<Void> extractor) {
        UpdateServerCertificateRequest request = new
                UpdateServerCertificateRequest();

        return update(request, extractor);
    }

    @Override
    public void delete(DeleteServerCertificateRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteServerCertificateRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteServerCertificateRequest request = new
                DeleteServerCertificateRequest();

        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<ServerCertificate> {
        @Override
        public ServerCertificate transform(ResourceImpl resource) {
            return new ServerCertificateImpl(resource);
        }
    }
}
