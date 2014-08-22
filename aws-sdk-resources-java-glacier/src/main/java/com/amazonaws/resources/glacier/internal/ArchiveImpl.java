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
import com.amazonaws.resources.glacier.Archive;
import com.amazonaws.resources.glacier.Job;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.DeleteArchiveRequest;
import com.amazonaws.services.glacier.model.InitiateJobRequest;
import com.amazonaws.services.glacier.model.InitiateJobResult;

class ArchiveImpl implements Archive {
    public static final ResourceCodec<Archive> CODEC = new Codec();

    private final ResourceImpl resource;

    public ArchiveImpl(ResourceImpl resource) {
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
    public Vault getVault() {
        ResourceImpl result = resource.getReference("Vault");
        if (result == null) return null;
        return new VaultImpl(result);
    }

    @Override
    public void delete(DeleteArchiveRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteArchiveRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteArchiveRequest request = new DeleteArchiveRequest();
        delete(request, extractor);
    }

    @Override
    public Job initiateArchiveRetreival(InitiateJobRequest request) {
        return initiateArchiveRetreival(request, null);
    }

    @Override
    public Job initiateArchiveRetreival(InitiateJobRequest request,
            ResultCapture<InitiateJobResult> extractor) {

        ActionResult result = resource.performAction("InitiateArchiveRetreival",
                request, extractor);

        if (result == null) return null;
        return new JobImpl(result.getResource());
    }

    @Override
    public Job initiateArchiveRetreival() {
        return initiateArchiveRetreival((ResultCapture<InitiateJobResult>)null);
    }

    @Override
    public Job initiateArchiveRetreival(ResultCapture<InitiateJobResult>
            extractor) {

        InitiateJobRequest request = new InitiateJobRequest();
        return initiateArchiveRetreival(request, extractor);
    }

    private static class Codec implements ResourceCodec<Archive> {
        @Override
        public Archive transform(ResourceImpl resource) {
            return new ArchiveImpl(resource);
        }
    }
}
