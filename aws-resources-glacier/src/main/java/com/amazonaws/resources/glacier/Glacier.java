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
import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.model.CreateVaultRequest;
import com.amazonaws.services.glacier.model.CreateVaultResult;
import com.amazonaws.services.glacier.model.ListVaultsRequest;

/**
 * The <code>Glacier</code> service.
 * This is the entry point to interact with the following service resources:<ul>
 *   <li>Job</li>
 *   <li>Archive</li>
 *   <li>Vault</li>
 *   <li>Account</li>
 *   <li>MultipartUpload</li>
 *   <li>Notification</li>
 * </ul>
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.glacier.internal.GlacierImpl")

public interface Glacier extends Service<AmazonGlacier> {
    /**
     * Gets an instance of {@code Account} resource by its identifier(s).
     */
    Account getAccount(String id);

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
     *
     * @return The <code>Vault</code> resource object associated with the result
     *         of this action.
     * @see CreateVaultRequest
     */
    Vault createVault(CreateVaultRequest request);

    /**
     * Performs the <code>CreateVault</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Vault</code> resource object associated with the result
     *         of this action.
     * @see CreateVaultRequest
     */
    Vault createVault(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest)
     */
    Vault createVault(String vaultName);

    /**
     * The convenient method form for the <code>CreateVault</code> action.
     *
     * @see #createVault(CreateVaultRequest, ResultCapture)
     */
    Vault createVault(String vaultName, ResultCapture<CreateVaultResult>
            extractor);
}
