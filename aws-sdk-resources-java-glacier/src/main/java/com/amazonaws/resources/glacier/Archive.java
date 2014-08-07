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
     * Retrieves the Vault referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs an action.
     */
    void delete(DeleteArchiveRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteArchiveRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    Job initiateArchiveRetreival(InitiateJobRequest request);

    /**
     * Performs an action.
     */
    Job initiateArchiveRetreival(InitiateJobRequest request,
            ResultCapture<InitiateJobResult> extractor);
}
