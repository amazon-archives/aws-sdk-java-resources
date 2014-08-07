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
     * Gets the value of the AccountId identifier.
     */
    String getAccountId();

    /**
     * Gets the value of the Id identifier.
     */
    String getId();

    /**
     * Gets the value of the VaultName identifier.
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
     * Retrieves the Vault referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs an action.
     */
    ListPartsResult parts(ListPartsRequest request);

    /**
     * Performs an action.
     */
    ListPartsResult parts(ListPartsRequest request,
            ResultCapture<ListPartsResult> extractor);

    /**
     * Performs an action.
     */
    void abort(AbortMultipartUploadRequest request);

    /**
     * Performs an action.
     */
    void abort(AbortMultipartUploadRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    UploadMultipartPartResult uploadPart(UploadMultipartPartRequest request);

    /**
     * Performs an action.
     */
    UploadMultipartPartResult uploadPart(UploadMultipartPartRequest request,
            ResultCapture<UploadMultipartPartResult> extractor);

    /**
     * Performs an action.
     */
    CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request);

    /**
     * Performs an action.
     */
    CompleteMultipartUploadResult complete(CompleteMultipartUploadRequest
            request, ResultCapture<CompleteMultipartUploadResult> extractor);
}
