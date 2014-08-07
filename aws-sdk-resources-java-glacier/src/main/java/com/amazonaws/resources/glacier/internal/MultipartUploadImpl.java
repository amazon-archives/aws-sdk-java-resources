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
package com.amazonaws.resources.glacier.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.glacier.MultipartUpload;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.AbortMultipartUploadRequest;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadResult;
import com.amazonaws.services.glacier.model.ListPartsRequest;
import com.amazonaws.services.glacier.model.ListPartsResult;
import com.amazonaws.services.glacier.model.UploadMultipartPartRequest;
import com.amazonaws.services.glacier.model.UploadMultipartPartResult;

class MultipartUploadImpl implements MultipartUpload {
    public static final ResourceCodec<MultipartUpload> CODEC = new Codec();

    private final ResourceImpl resource;

    public MultipartUploadImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getAccountId() {
        return (String) resource.getIdentifier("AccountId");
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public String getVaultName() {
        return (String) resource.getIdentifier("VaultName");
    }

    @Override
    public String getVaultARN() {
        return (String) resource.getAttribute("VaultARN");
    }

    @Override
    public Long getPartSizeInBytes() {
        return (Long) resource.getAttribute("PartSizeInBytes");
    }

    @Override
    public String getCreationDate() {
        return (String) resource.getAttribute("CreationDate");
    }

    @Override
    public String getArchiveDescription() {
        return (String) resource.getAttribute("ArchiveDescription");
    }

    @Override
    public Vault getVault() {
        ResourceImpl result = resource.getReference("Vault");
        if (result == null) return null;
        return new VaultImpl(result);
    }

    @Override
    public ListPartsResult parts(ListPartsRequest request) {
        return parts(request, null);
    }

    @Override
    public ListPartsResult parts(ListPartsRequest request,
            ResultCapture<ListPartsResult> extractor) {

        ActionResult result = resource.performAction("Parts", request,
                extractor);

        if (result == null) return null;
        return (ListPartsResult) result.getData();
    }

    @Override
    public void abort(AbortMultipartUploadRequest request) {
        abort(request, null);
    }

    @Override
    public void abort(AbortMultipartUploadRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Abort", request, extractor);
    }

    @Override
    public UploadMultipartPartResult uploadPart(UploadMultipartPartRequest
            request) {

        return uploadPart(request, null);
    }

    @Override
    public UploadMultipartPartResult uploadPart(UploadMultipartPartRequest
            request, ResultCapture<UploadMultipartPartResult> extractor) {

        ActionResult result = resource.performAction("UploadPart", request,
                extractor);

        if (result == null) return null;
        return (UploadMultipartPartResult) result.getData();
    }

    @Override
    public CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request) {

        return complete(request, null);
    }

    @Override
    public CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request, ResultCapture<CompleteMultipartUploadResult> extractor) {

        ActionResult result = resource.performAction("Complete", request,
                extractor);

        if (result == null) return null;
        return (CompleteMultipartUploadResult) result.getData();
    }

    private static class Codec implements ResourceCodec<MultipartUpload> {
        @Override
        public MultipartUpload transform(ResourceImpl resource) {
            return new MultipartUploadImpl(resource);
        }
    }
}
