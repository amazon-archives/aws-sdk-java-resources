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
import com.amazonaws.resources.glacier.Job;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.DescribeJobRequest;
import com.amazonaws.services.glacier.model.DescribeJobResult;
import com.amazonaws.services.glacier.model.GetJobOutputRequest;
import com.amazonaws.services.glacier.model.GetJobOutputResult;
import com.amazonaws.services.glacier.model.InventoryRetrievalJobDescription;

class JobImpl implements Job {
    public static final ResourceCodec<Job> CODEC = new Codec();

    private final ResourceImpl resource;

    public JobImpl(ResourceImpl resource) {
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
    public boolean load(DescribeJobRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeJobRequest request,
            ResultCapture<DescribeJobResult> extractor) {

        return resource.load(request, extractor);
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
    public String getStatusCode() {
        return (String) resource.getAttribute("StatusCode");
    }

    @Override
    public String getArchiveSHA256TreeHash() {
        return (String) resource.getAttribute("ArchiveSHA256TreeHash");
    }

    @Override
    public String getArchiveId() {
        return (String) resource.getAttribute("ArchiveId");
    }

    @Override
    public String getCompletionDate() {
        return (String) resource.getAttribute("CompletionDate");
    }

    @Override
    public Boolean getCompleted() {
        return (Boolean) resource.getAttribute("Completed");
    }

    @Override
    public String getCreationDate() {
        return (String) resource.getAttribute("CreationDate");
    }

    @Override
    public String getJobDescription() {
        return (String) resource.getAttribute("JobDescription");
    }

    @Override
    public String getSNSTopic() {
        return (String) resource.getAttribute("SNSTopic");
    }

    @Override
    public String getAction() {
        return (String) resource.getAttribute("Action");
    }

    @Override
    public Long getArchiveSizeInBytes() {
        return (Long) resource.getAttribute("ArchiveSizeInBytes");
    }

    @Override
    public String getVaultARN() {
        return (String) resource.getAttribute("VaultARN");
    }

    @Override
    public String getSHA256TreeHash() {
        return (String) resource.getAttribute("SHA256TreeHash");
    }

    @Override
    public String getStatusMessage() {
        return (String) resource.getAttribute("StatusMessage");
    }

    @Override
    public Long getInventorySizeInBytes() {
        return (Long) resource.getAttribute("InventorySizeInBytes");
    }

    @Override
    public String getRetrievalByteRange() {
        return (String) resource.getAttribute("RetrievalByteRange");
    }

    @Override
    public InventoryRetrievalJobDescription getInventoryRetrievalParameters() {
        return (InventoryRetrievalJobDescription)
                resource.getAttribute("InventoryRetrievalParameters");
    }

    @Override
    public Vault getVault() {
        ResourceImpl result = resource.getReference("Vault");
        if (result == null) return null;
        return new VaultImpl(result);
    }

    @Override
    public GetJobOutputResult getOutput(GetJobOutputRequest request) {
        return getOutput(request, null);
    }

    @Override
    public GetJobOutputResult getOutput(GetJobOutputRequest request,
            ResultCapture<GetJobOutputResult> extractor) {

        ActionResult result = resource.performAction("GetOutput", request,
                extractor);

        if (result == null) return null;
        return (GetJobOutputResult) result.getData();
    }

    @Override
    public GetJobOutputResult getOutput(String range) {
        return getOutput(range, (ResultCapture<GetJobOutputResult>)null);
    }

    @Override
    public GetJobOutputResult getOutput(String range,
            ResultCapture<GetJobOutputResult> extractor) {

        GetJobOutputRequest request = new GetJobOutputRequest()
            .withRange(range);
        return getOutput(request, extractor);
    }

    private static class Codec implements ResourceCodec<Job> {
        @Override
        public Job transform(ResourceImpl resource) {
            return new JobImpl(resource);
        }
    }
}
