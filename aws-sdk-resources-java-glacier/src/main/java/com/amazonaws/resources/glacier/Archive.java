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
import com.amazonaws.services.glacier.model.DeleteArchiveRequest;
import com.amazonaws.services.glacier.model.InitiateJobRequest;
import com.amazonaws.services.glacier.model.InitiateJobResult;

/**
 * The Archive resource.
 */
public interface Archive {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

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
     * Retrieves the <code>Vault</code> resource referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Archive</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
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
     *     <b><code>ArchiveId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteArchiveRequest
     */
    void delete(DeleteArchiveRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Archive</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
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
     *     <b><code>ArchiveId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteArchiveRequest
     */
    void delete(DeleteArchiveRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteArchiveRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteArchiveRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>InitiateArchiveRetreival</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Archive</code> resource, and any conflicting parameter value set in
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
     *     <b><code>JobParameters.ArchiveId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobParameters.Type</code></b>
     *         - constant value <code>archive-retrieval</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Job</code> resource object associated with the result
     *         of this action.
     * @see InitiateJobRequest
     */
    Job initiateArchiveRetreival(InitiateJobRequest request);

    /**
     * Performs the <code>InitiateArchiveRetreival</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Archive</code> resource, and any conflicting parameter value set in
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
     *     <b><code>JobParameters.ArchiveId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>JobParameters.Type</code></b>
     *         - constant value <code>archive-retrieval</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Job</code> resource object associated with the result
     *         of this action.
     * @see InitiateJobRequest
     */
    Job initiateArchiveRetreival(InitiateJobRequest request,
            ResultCapture<InitiateJobResult> extractor);

    /**
     * The convenient method form for the <code>InitiateArchiveRetreival</code>
     * action.
     *
     * @see #initiateArchiveRetreival(InitiateJobRequest)
     */
    Job initiateArchiveRetreival();

    /**
     * The convenient method form for the <code>InitiateArchiveRetreival</code>
     * action.
     *
     * @see #initiateArchiveRetreival(InitiateJobRequest, ResultCapture)
     */
    Job initiateArchiveRetreival(ResultCapture<InitiateJobResult> extractor);
}
