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
import com.amazonaws.resources.glacier.Account;
import com.amazonaws.resources.glacier.Archive;
import com.amazonaws.resources.glacier.Job;
import com.amazonaws.resources.glacier.JobCollection;
import com.amazonaws.resources.glacier.MultipartUpload;
import com.amazonaws.resources.glacier.MultipartUploadCollection;
import com.amazonaws.resources.glacier.Notification;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.CreateVaultRequest;
import com.amazonaws.services.glacier.model.CreateVaultResult;
import com.amazonaws.services.glacier.model.DeleteVaultRequest;
import com.amazonaws.services.glacier.model.DescribeVaultRequest;
import com.amazonaws.services.glacier.model.DescribeVaultResult;
import com.amazonaws.services.glacier.model.InitiateJobRequest;
import com.amazonaws.services.glacier.model.InitiateJobResult;
import com.amazonaws.services.glacier.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.glacier.model.InitiateMultipartUploadResult;
import com.amazonaws.services.glacier.model.ListJobsRequest;
import com.amazonaws.services.glacier.model.ListMultipartUploadsRequest;
import com.amazonaws.services.glacier.model.UploadArchiveRequest;
import com.amazonaws.services.glacier.model.UploadArchiveResult;

class VaultImpl implements Vault {
    public static final ResourceCodec<Vault> CODEC = new Codec();

    private final ResourceImpl resource;

    public VaultImpl(ResourceImpl resource) {
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
    public boolean load(DescribeVaultRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeVaultRequest request,
            ResultCapture<DescribeVaultResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getAccountId() {
        return (String) resource.getIdentifier("AccountId");
    }

    @Override
    public String getLastInventoryDate() {
        return (String) resource.getAttribute("LastInventoryDate");
    }

    @Override
    public Long getNumberOfArchives() {
        return (Long) resource.getAttribute("NumberOfArchives");
    }

    @Override
    public String getVaultARN() {
        return (String) resource.getAttribute("VaultARN");
    }

    @Override
    public String getCreationDate() {
        return (String) resource.getAttribute("CreationDate");
    }

    @Override
    public Long getSizeInBytes() {
        return (Long) resource.getAttribute("SizeInBytes");
    }

    @Override
    public Account getAccount() {
        ResourceImpl result = resource.getReference("Account");
        if (result == null) return null;
        return new AccountImpl(result);
    }

    @Override
    public Job getJob(String id) {
        ResourceImpl result = resource.getSubResource("Job", id);
        if (result == null) return null;
        return new JobImpl(result);
    }

    @Override
    public Archive getArchive(String id) {
        ResourceImpl result = resource.getSubResource("Archive", id);
        if (result == null) return null;
        return new ArchiveImpl(result);
    }

    @Override
    public MultipartUpload getMultipartUpload(String id) {
        ResourceImpl result = resource.getSubResource("MultipartUpload", id);
        if (result == null) return null;
        return new MultipartUploadImpl(result);
    }

    @Override
    public Notification getNotification() {
        ResourceImpl result = resource.getSubResource("Notification", null);
        if (result == null) return null;
        return new NotificationImpl(result);
    }

    @Override
    public JobCollection getJobsInProgress() {
        return getJobsInProgress(null);
    }

    @Override
    public JobCollection getJobsInProgress(ListJobsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("JobsInProgress",
                request);

        if (result == null) return null;
        return new JobCollectionImpl(result);
    }

    @Override
    public JobCollection getJobs() {
        return getJobs(null);
    }

    @Override
    public JobCollection getJobs(ListJobsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Jobs", request);
        if (result == null) return null;
        return new JobCollectionImpl(result);
    }

    @Override
    public JobCollection getSucceededJobs() {
        return getSucceededJobs(null);
    }

    @Override
    public JobCollection getSucceededJobs(ListJobsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("SucceededJobs",
                request);

        if (result == null) return null;
        return new JobCollectionImpl(result);
    }

    @Override
    public JobCollection getCompletedJobs() {
        return getCompletedJobs(null);
    }

    @Override
    public JobCollection getCompletedJobs(ListJobsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("CompletedJobs",
                request);

        if (result == null) return null;
        return new JobCollectionImpl(result);
    }

    @Override
    public JobCollection getFailedJobs() {
        return getFailedJobs(null);
    }

    @Override
    public JobCollection getFailedJobs(ListJobsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("FailedJobs",
                request);

        if (result == null) return null;
        return new JobCollectionImpl(result);
    }

    @Override
    public MultipartUploadCollection getMultipartUplaods() {
        return getMultipartUplaods(null);
    }

    @Override
    public MultipartUploadCollection getMultipartUplaods(
            ListMultipartUploadsRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("MultipartUplaods", request);

        if (result == null) return null;
        return new MultipartUploadCollectionImpl(result);
    }

    @Override
    public Job initiateInventoryRetrieval(InitiateJobRequest request) {
        return initiateInventoryRetrieval(request, null);
    }

    @Override
    public Job initiateInventoryRetrieval(InitiateJobRequest request,
            ResultCapture<InitiateJobResult> extractor) {

        ActionResult result =
                resource.performAction("InitiateInventoryRetrieval", request,
                extractor);

        if (result == null) return null;
        return new JobImpl(result.getResource());
    }

    @Override
    public Archive uploadArchive(UploadArchiveRequest request) {
        return uploadArchive(request, null);
    }

    @Override
    public Archive uploadArchive(UploadArchiveRequest request,
            ResultCapture<UploadArchiveResult> extractor) {

        ActionResult result = resource.performAction("UploadArchive", request,
                extractor);

        if (result == null) return null;
        return new ArchiveImpl(result.getResource());
    }

    @Override
    public CreateVaultResult create(CreateVaultRequest request) {
        return create(request, null);
    }

    @Override
    public CreateVaultResult create(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor) {

        ActionResult result = resource.performAction("Create", request,
                extractor);

        if (result == null) return null;
        return (CreateVaultResult) result.getData();
    }

    @Override
    public MultipartUpload initiateMultipartUpload(
            InitiateMultipartUploadRequest request) {

        return initiateMultipartUpload(request, null);
    }

    @Override
    public MultipartUpload initiateMultipartUpload(
            InitiateMultipartUploadRequest request,
            ResultCapture<InitiateMultipartUploadResult> extractor) {

        ActionResult result = resource.performAction("InitiateMultipartUpload",
                request, extractor);

        if (result == null) return null;
        return new MultipartUploadImpl(result.getResource());
    }

    @Override
    public void delete(DeleteVaultRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteVaultRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<Vault> {
        @Override
        public Vault transform(ResourceImpl resource) {
            return new VaultImpl(resource);
        }
    }
}
