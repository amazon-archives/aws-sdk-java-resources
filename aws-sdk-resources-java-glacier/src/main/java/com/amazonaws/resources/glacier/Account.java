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
import com.amazonaws.services.glacier.model.ListVaultsRequest;

/**
 * The Account resource.
 */
public interface Account {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the Id identifier.
     */
    String getId();

    /**
     * Gets a subresource.
     */
    Vault getVault(String name);

    /**
     * Retrieves the Vaults collection referenced by this resource.
     */
    VaultCollection getVaults();

    /**
     * Retrieves the Vaults collection referenced by this resource.
     */
    VaultCollection getVaults(ListVaultsRequest request);

    /**
     * Performs an action.
     */
    Vault createVault(CreateVaultRequest request);

    /**
     * Performs an action.
     */
    Vault createVault(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor);
}
