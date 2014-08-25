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
import com.amazonaws.services.glacier.model.AbortMultipartUploadRequest;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.glacier.model.CompleteMultipartUploadResult;
import com.amazonaws.services.glacier.model.ListPartsRequest;
import com.amazonaws.services.glacier.model.ListPartsResult;
import com.amazonaws.services.glacier.model.UploadMultipartPartRequest;
import com.amazonaws.services.glacier.model.UploadMultipartPartResult;

/**
 * The MultipartUpload resource.
 */
public interface MultipartUpload {
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
     * Gets the value of the VaultARN attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVaultARN();

    /**
     * Gets the value of the PartSizeInBytes attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Long getPartSizeInBytes();

    /**
     * Gets the value of the CreationDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCreationDate();

    /**
     * Gets the value of the ArchiveDescription attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getArchiveDescription();

    /**
     * Retrieves the <code>Vault</code> resource referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs the <code>Parts</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ListPartsRequest
     */
    ListPartsResult parts(ListPartsRequest request);

    /**
     * Performs the <code>Parts</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ListPartsRequest
     */
    ListPartsResult parts(ListPartsRequest request,
            ResultCapture<ListPartsResult> extractor);

    /**
     * The convenient method form for the <code>Parts</code> action.
     *
     * @see #parts(ListPartsRequest)
     */
    ListPartsResult parts();

    /**
     * The convenient method form for the <code>Parts</code> action.
     *
     * @see #parts(ListPartsRequest, ResultCapture)
     */
    ListPartsResult parts(ResultCapture<ListPartsResult> extractor);

    /**
     * Performs the <code>Abort</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AbortMultipartUploadRequest
     */
    void abort(AbortMultipartUploadRequest request);

    /**
     * Performs the <code>Abort</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AbortMultipartUploadRequest
     */
    void abort(AbortMultipartUploadRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Abort</code> action.
     *
     * @see #abort(AbortMultipartUploadRequest)
     */
    void abort();

    /**
     * The convenient method form for the <code>Abort</code> action.
     *
     * @see #abort(AbortMultipartUploadRequest, ResultCapture)
     */
    void abort(ResultCapture<Void> extractor);

    /**
     * Performs the <code>UploadPart</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UploadMultipartPartRequest
     */
    UploadMultipartPartResult uploadPart(UploadMultipartPartRequest request);

    /**
     * Performs the <code>UploadPart</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UploadMultipartPartRequest
     */
    UploadMultipartPartResult uploadPart(UploadMultipartPartRequest request,
            ResultCapture<UploadMultipartPartResult> extractor);

    /**
     * The convenient method form for the <code>UploadPart</code> action.
     *
     * @see #uploadPart(UploadMultipartPartRequest)
     */
    UploadMultipartPartResult uploadPart(String checksum, InputStream body,
            String range);

    /**
     * The convenient method form for the <code>UploadPart</code> action.
     *
     * @see #uploadPart(UploadMultipartPartRequest, ResultCapture)
     */
    UploadMultipartPartResult uploadPart(String checksum, InputStream body,
            String range, ResultCapture<UploadMultipartPartResult> extractor);

    /**
     * Performs the <code>Complete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CompleteMultipartUploadRequest
     */
    CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request);

    /**
     * Performs the <code>Complete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MultipartUpload</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
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
     *     <b><code>UploadId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CompleteMultipartUploadRequest
     */
    CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request, ResultCapture<CompleteMultipartUploadResult> extractor);

    /**
     * The convenient method form for the <code>Complete</code> action.
     *
     * @see #complete(CompleteMultipartUploadRequest)
     */
    CompleteMultipartUploadResult complete(String checksum, String archiveSize);

    /**
     * The convenient method form for the <code>Complete</code> action.
     *
     * @see #complete(CompleteMultipartUploadRequest, ResultCapture)
     */
    CompleteMultipartUploadResult complete(String checksum, String archiveSize,
            ResultCapture<CompleteMultipartUploadResult> extractor);
}
