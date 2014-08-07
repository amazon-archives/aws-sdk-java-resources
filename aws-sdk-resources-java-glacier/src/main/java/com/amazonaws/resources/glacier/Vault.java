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
package com.amazonaws.resources.glacier;

import com.amazonaws.resources.ResultCapture;
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

/**
 * The Vault resource.
 */
public interface Vault {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeVaultRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeVaultRequest request,
            ResultCapture<DescribeVaultResult> extractor);

    /**
     * Gets the value of the Name identifier.
     */
    String getName();

    /**
     * Gets the value of the AccountId identifier.
     */
    String getAccountId();

    /**
     * Gets the value of the LastInventoryDate attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getLastInventoryDate();

    /**
     * Gets the value of the NumberOfArchives attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Long getNumberOfArchives();

    /**
     * Gets the value of the VaultARN attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVaultARN();

    /**
     * Gets the value of the CreationDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCreationDate();

    /**
     * Gets the value of the SizeInBytes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Long getSizeInBytes();

    /**
     * Retrieves the Account referenced by this resource.
     */
    Account getAccount();

    /**
     * Gets a subresource.
     */
    Job getJob(String id);

    /**
     * Gets a subresource.
     */
    Archive getArchive(String id);

    /**
     * Gets a subresource.
     */
    MultipartUpload getMultipartUpload(String id);

    /**
     * Gets a subresource.
     */
    Notification getNotification();

    /**
     * Retrieves the JobsInProgress collection referenced by this resource.
     */
    JobCollection getJobsInProgress();

    /**
     * Retrieves the JobsInProgress collection referenced by this resource.
     */
    JobCollection getJobsInProgress(ListJobsRequest request);

    /**
     * Retrieves the Jobs collection referenced by this resource.
     */
    JobCollection getJobs();

    /**
     * Retrieves the Jobs collection referenced by this resource.
     */
    JobCollection getJobs(ListJobsRequest request);

    /**
     * Retrieves the SucceededJobs collection referenced by this resource.
     */
    JobCollection getSucceededJobs();

    /**
     * Retrieves the SucceededJobs collection referenced by this resource.
     */
    JobCollection getSucceededJobs(ListJobsRequest request);

    /**
     * Retrieves the CompletedJobs collection referenced by this resource.
     */
    JobCollection getCompletedJobs();

    /**
     * Retrieves the CompletedJobs collection referenced by this resource.
     */
    JobCollection getCompletedJobs(ListJobsRequest request);

    /**
     * Retrieves the FailedJobs collection referenced by this resource.
     */
    JobCollection getFailedJobs();

    /**
     * Retrieves the FailedJobs collection referenced by this resource.
     */
    JobCollection getFailedJobs(ListJobsRequest request);

    /**
     * Retrieves the MultipartUplaods collection referenced by this resource.
     */
    MultipartUploadCollection getMultipartUplaods();

    /**
     * Retrieves the MultipartUplaods collection referenced by this resource.
     */
    MultipartUploadCollection getMultipartUplaods(ListMultipartUploadsRequest
            request);

    /**
     * Performs an action.
     */
    Job initiateInventoryRetrieval(InitiateJobRequest request);

    /**
     * Performs an action.
     */
    Job initiateInventoryRetrieval(InitiateJobRequest request,
            ResultCapture<InitiateJobResult> extractor);

    /**
     * Performs an action.
     */
    Archive uploadArchive(UploadArchiveRequest request);

    /**
     * Performs an action.
     */
    Archive uploadArchive(UploadArchiveRequest request,
            ResultCapture<UploadArchiveResult> extractor);

    /**
     * Performs an action.
     */
    CreateVaultResult create(CreateVaultRequest request);

    /**
     * Performs an action.
     */
    CreateVaultResult create(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor);

    /**
     * Performs an action.
     */
    MultipartUpload initiateMultipartUpload(InitiateMultipartUploadRequest
            request);

    /**
     * Performs an action.
     */
    MultipartUpload initiateMultipartUpload(InitiateMultipartUploadRequest
            request, ResultCapture<InitiateMultipartUploadResult> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteVaultRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteVaultRequest request, ResultCapture<Void> extractor);
}
