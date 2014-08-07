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
 * The Job resource.
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
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeJobRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeJobRequest request, ResultCapture<DescribeJobResult>
            extractor);

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
     * Retrieves the Vault referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs an action.
     */
    GetJobOutputResult getOutput(GetJobOutputRequest request);

    /**
     * Performs an action.
     */
    GetJobOutputResult getOutput(GetJobOutputRequest request,
            ResultCapture<GetJobOutputResult> extractor);
}
