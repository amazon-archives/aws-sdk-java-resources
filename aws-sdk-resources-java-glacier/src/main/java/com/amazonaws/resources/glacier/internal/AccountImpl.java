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
package com.amazonaws.resources.glacier.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.glacier.Account;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.glacier.VaultCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.CreateVaultRequest;
import com.amazonaws.services.glacier.model.CreateVaultResult;
import com.amazonaws.services.glacier.model.ListVaultsRequest;

class AccountImpl implements Account {
    public static final ResourceCodec<Account> CODEC = new Codec();

    private final ResourceImpl resource;

    public AccountImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public Vault getVault(String name) {
        ResourceImpl result = resource.getSubResource("Vault", name);
        if (result == null) return null;
        return new VaultImpl(result);
    }

    @Override
    public VaultCollection getVaults() {
        return getVaults(null);
    }

    @Override
    public VaultCollection getVaults(ListVaultsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Vaults",
                request);

        if (result == null) return null;
        return new VaultCollectionImpl(result);
    }

    @Override
    public Vault createVault(CreateVaultRequest request) {
        return createVault(request, null);
    }

    @Override
    public Vault createVault(CreateVaultRequest request,
            ResultCapture<CreateVaultResult> extractor) {

        ActionResult result = resource.performAction("CreateVault", request,
                extractor);

        if (result == null) return null;
        return new VaultImpl(result.getResource());
    }

    @Override
    public Vault createVault() {
        return createVault((ResultCapture<CreateVaultResult>)null);
    }

    @Override
    public Vault createVault(ResultCapture<CreateVaultResult> extractor) {
        CreateVaultRequest request = new CreateVaultRequest();
        return createVault(request, extractor);
    }

    @Override
    public Vault createVault(String vaultName) {
        return createVault(vaultName, (ResultCapture<CreateVaultResult>)null);
    }

    @Override
    public Vault createVault(String vaultName, ResultCapture<CreateVaultResult>
            extractor) {

        CreateVaultRequest request = new CreateVaultRequest()
            .withVaultName(vaultName);
        return createVault(request, extractor);
    }

    @Override
    public Vault createVault(String accountId, String vaultName) {
        return createVault(accountId, vaultName,
                (ResultCapture<CreateVaultResult>)null);
    }

    @Override
    public Vault createVault(String accountId, String vaultName,
            ResultCapture<CreateVaultResult> extractor) {

        CreateVaultRequest request = new CreateVaultRequest()
            .withAccountId(accountId)
            .withVaultName(vaultName);
        return createVault(request, extractor);
    }

    private static class Codec implements ResourceCodec<Account> {
        @Override
        public Account transform(ResourceImpl resource) {
            return new AccountImpl(resource);
        }
    }
}
