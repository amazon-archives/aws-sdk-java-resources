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
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
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
     * Performs the <code>CreateVault</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Account</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Vault</code> resource object associated with the result
     *         of this action.
     * @see CreateVaultRequest
     */
    com.amazonaws.resources.glacier.Vault createVault(CreateVaultRequest request
            );

    /**
     * Performs the <code>CreateVault</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Account</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Vault</code> resource object associated with the result
     *         of this action.
     * @see CreateVaultRequest
     */
    com.amazonaws.resources.glacier.Vault createVault(CreateVaultRequest request
            , ResultCapture<CreateVaultResult> extractor);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest)
     */
    com.amazonaws.resources.glacier.Vault createVault();

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest, ResultCapture)
     */
    com.amazonaws.resources.glacier.Vault createVault(
            ResultCapture<CreateVaultResult> extractor);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest)
     */
    com.amazonaws.resources.glacier.Vault createVault(String vaultName);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest, ResultCapture)
     */
    com.amazonaws.resources.glacier.Vault createVault(String vaultName,
            ResultCapture<CreateVaultResult> extractor);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest)
     */
    com.amazonaws.resources.glacier.Vault createVault(String accountId, String
            vaultName);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest, ResultCapture)
     */
    com.amazonaws.resources.glacier.Vault createVault(String accountId, String
            vaultName, ResultCapture<CreateVaultResult> extractor);
}
