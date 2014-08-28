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

import java.io.InputStream;

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
 * The <code>Vault</code> resource.
 * Each <code>Vault</code> object is uniquely identified by these identifier(s):
 * <ul>
 *   <li>Name</li>
 *   <li>AccountId</li>
 * </ul>
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
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(DescribeVaultRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVaultRequest
     */
    boolean load(DescribeVaultRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVaultRequest
     */
    boolean load(DescribeVaultRequest request,
            ResultCapture<DescribeVaultResult> extractor);

    /**
     * Gets the value of the Name identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getName();

    /**
     * Gets the value of the AccountId identifier. This method always directly
     * returns the identifier and never involves a service call.
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
     * Retrieves the <code>Account</code> resource referenced by this resource.
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
     * Performs the <code>InitiateInventoryRetrieval</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobParameters.Type</code></b>
     *         - constant value <code>inventory-retrieval</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Job</code> resource object associated with the result
     *         of this action.
     * @see InitiateJobRequest
     */
    com.amazonaws.resources.glacier.Job initiateInventoryRetrieval(
            InitiateJobRequest request);

    /**
     * Performs the <code>InitiateInventoryRetrieval</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobParameters.Type</code></b>
     *         - constant value <code>inventory-retrieval</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Job</code> resource object associated with the result
     *         of this action.
     * @see InitiateJobRequest
     */
    com.amazonaws.resources.glacier.Job initiateInventoryRetrieval(
            InitiateJobRequest request, ResultCapture<InitiateJobResult>
            extractor);

    /**
     * The convenient method form for the
     * <code>InitiateInventoryRetrieval</code> action.
     *
     * @see #initiateInventoryRetrieval(InitiateJobRequest)
     */
    com.amazonaws.resources.glacier.Job initiateInventoryRetrieval();

    /**
     * The convenient method form for the
     * <code>InitiateInventoryRetrieval</code> action.
     *
     * @see #initiateInventoryRetrieval(InitiateJobRequest, ResultCapture)
     */
    com.amazonaws.resources.glacier.Job initiateInventoryRetrieval(
            ResultCapture<InitiateJobResult> extractor);

    /**
     * Performs the <code>UploadArchive</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Archive</code> resource object associated with the
     *         result of this action.
     * @see UploadArchiveRequest
     */
    com.amazonaws.resources.glacier.Archive uploadArchive(UploadArchiveRequest
            request);

    /**
     * Performs the <code>UploadArchive</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Archive</code> resource object associated with the
     *         result of this action.
     * @see UploadArchiveRequest
     */
    com.amazonaws.resources.glacier.Archive uploadArchive(UploadArchiveRequest
            request, ResultCapture<UploadArchiveResult> extractor);

    /**
     * The convenient method form for the <code>UploadArchive</code> action.
     *
     * @see #uploadArchive(UploadArchiveRequest)
     */
    com.amazonaws.resources.glacier.Archive uploadArchive(String checksum,
            InputStream body, String archiveDescription);

    /**
     * The convenient method form for the <code>UploadArchive</code> action.
     *
     * @see #uploadArchive(UploadArchiveRequest, ResultCapture)
     */
    com.amazonaws.resources.glacier.Archive uploadArchive(String checksum,
            InputStream body, String archiveDescription,
            ResultCapture<UploadArchiveResult> extractor);

    /**
     * Performs the <code>Create</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CreateVaultRequest
     */
    CreateVaultResult create(CreateVaultRequest request);

    /**
     * Performs the <code>Create</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CreateVaultRequest
     */
    CreateVaultResult create(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor);

    /**
     * The convenient method form for the <code>Create</code> action.
     *
     * @see #create(CreateVaultRequest)
     */
    CreateVaultResult create();

    /**
     * The convenient method form for the <code>Create</code> action.
     *
     * @see #create(CreateVaultRequest, ResultCapture)
     */
    CreateVaultResult create(ResultCapture<CreateVaultResult> extractor);

    /**
     * Performs the <code>InitiateMultipartUpload</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>MultipartUpload</code> resource object associated with
     *         the result of this action.
     * @see InitiateMultipartUploadRequest
     */
    com.amazonaws.resources.glacier.MultipartUpload initiateMultipartUpload(
            InitiateMultipartUploadRequest request);

    /**
     * Performs the <code>InitiateMultipartUpload</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>MultipartUpload</code> resource object associated with
     *         the result of this action.
     * @see InitiateMultipartUploadRequest
     */
    com.amazonaws.resources.glacier.MultipartUpload initiateMultipartUpload(
            InitiateMultipartUploadRequest request,
            ResultCapture<InitiateMultipartUploadResult> extractor);

    /**
     * The convenient method form for the <code>InitiateMultipartUpload</code>
     * action.
     *
     * @see #initiateMultipartUpload(InitiateMultipartUploadRequest)
     */
    com.amazonaws.resources.glacier.MultipartUpload initiateMultipartUpload(
            String partSize, String archiveDescription);

    /**
     * The convenient method form for the <code>InitiateMultipartUpload</code>
     * action.
     *
     * @see #initiateMultipartUpload(InitiateMultipartUploadRequest,
     *         ResultCapture)
     */
    com.amazonaws.resources.glacier.MultipartUpload initiateMultipartUpload(
            String partSize, String archiveDescription,
            ResultCapture<InitiateMultipartUploadResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVaultRequest
     */
    void delete(DeleteVaultRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Vault</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVaultRequest
     */
    void delete(DeleteVaultRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVaultRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVaultRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
