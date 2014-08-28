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
import com.amazonaws.services.glacier.model.DescribeJobRequest;
import com.amazonaws.services.glacier.model.DescribeJobResult;
import com.amazonaws.services.glacier.model.GetJobOutputRequest;
import com.amazonaws.services.glacier.model.GetJobOutputResult;
import com.amazonaws.services.glacier.model.InventoryRetrievalJobDescription;

/**
 * The <code>Job</code> resource.
 * Each <code>Job</code> object is uniquely identified by these identifier(s):
 * <ul>
 *   <li>AccountId</li>
 *   <li>Id</li>
 *   <li>VaultName</li>
 * </ul>
 */
public interface Job {
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
     * @see #load(DescribeJobRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Job</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeJobRequest
     */
    boolean load(DescribeJobRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Job</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeJobRequest
     */
    boolean load(DescribeJobRequest request, ResultCapture<DescribeJobResult>
            extractor);

    /**
     * Gets the value of the AccountId identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getAccountId();

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the VaultName identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getVaultName();

    /**
     * Gets the value of the StatusCode attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getStatusCode();

    /**
     * Gets the value of the ArchiveSHA256TreeHash attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    String getArchiveSHA256TreeHash();

    /**
     * Gets the value of the ArchiveId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getArchiveId();

    /**
     * Gets the value of the CompletionDate attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCompletionDate();

    /**
     * Gets the value of the Completed attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getCompleted();

    /**
     * Gets the value of the CreationDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCreationDate();

    /**
     * Gets the value of the JobDescription attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getJobDescription();

    /**
     * Gets the value of the SNSTopic attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSNSTopic();

    /**
     * Gets the value of the Action attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getAction();

    /**
     * Gets the value of the ArchiveSizeInBytes attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    Long getArchiveSizeInBytes();

    /**
     * Gets the value of the VaultARN attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVaultARN();

    /**
     * Gets the value of the SHA256TreeHash attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSHA256TreeHash();

    /**
     * Gets the value of the StatusMessage attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getStatusMessage();

    /**
     * Gets the value of the InventorySizeInBytes attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    Long getInventorySizeInBytes();

    /**
     * Gets the value of the RetrievalByteRange attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getRetrievalByteRange();

    /**
     * Gets the value of the InventoryRetrievalParameters attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    InventoryRetrievalJobDescription getInventoryRetrievalParameters();

    /**
     * Retrieves the <code>Vault</code> resource referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs the <code>GetOutput</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Job</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetJobOutputRequest
     */
    GetJobOutputResult getOutput(GetJobOutputRequest request);

    /**
     * Performs the <code>GetOutput</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Job</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetJobOutputRequest
     */
    GetJobOutputResult getOutput(GetJobOutputRequest request,
            ResultCapture<GetJobOutputResult> extractor);

    /**
     * The convenient method form for the <code>GetOutput</code> action.
     *
     * @see #getOutput(GetJobOutputRequest)
     */
    GetJobOutputResult getOutput(String range);

    /**
     * The convenient method form for the <code>GetOutput</code> action.
     *
     * @see #getOutput(GetJobOutputRequest, ResultCapture)
     */
    GetJobOutputResult getOutput(String range, ResultCapture<GetJobOutputResult>
            extractor);
}
