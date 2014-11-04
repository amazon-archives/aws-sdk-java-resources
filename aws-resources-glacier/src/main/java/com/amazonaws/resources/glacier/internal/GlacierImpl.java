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

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.glacier.Account;
import com.amazonaws.resources.glacier.Glacier;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.glacier.VaultCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.V1ModelLoader;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.model.CreateVaultRequest;
import com.amazonaws.services.glacier.model.CreateVaultResult;
import com.amazonaws.services.glacier.model.ListVaultsRequest;

public class GlacierImpl implements Glacier {

    private final ServiceImpl<AmazonGlacier> service;

    /**
     * Construct a service implementation that talks to the specified AWS
     * region. The low-level client will be created via the default no-arg
     * constructor, which means it will have all the default client
     * configurations and it will use the default provider chain to retrieve AWS
     * credentials. If you need more flexible control over the low-level client,
     * use {@link #GlacierImpl(AmazonGlacier)} instead.
     *
     * @param region The AWS region where the service API calls will be sent to.
     */
    public GlacierImpl(Regions region) {
        this(new AmazonGlacierClient());
        this.client().setRegion(Region.getRegion(region));
    }

    /**
     * Construct a service implementation using the specified client object.
     *
     * @param client The low-level client which the service implementation will
     *         use to make API calls.
     */
    public GlacierImpl(AmazonGlacier client) {
        ServiceModel model = V1ModelLoader.load(Glacier.class,
                Glacier.class.getAnnotation(V1ServiceInterface.class).model());

        this.service = new ServiceImpl<AmazonGlacier>(model, client);
    }

    public GlacierImpl(ServiceImpl<AmazonGlacier> service) {
        this.service = service;
    }

    @Override
    public AmazonGlacier client() {
        return service.getClient();
    }

    @Override
    public Account getAccount(String id) {
        ResourceImpl result = service.getSubResource("Account", id);
        if (result == null) return null;
        return new AccountImpl(result);
    }

    @Override
    public VaultCollection getVaults() {
        return getVaults((ListVaultsRequest)null);
    }

    @Override
    public VaultCollection getVaults(ListVaultsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Vaults",
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

        ActionResult result = service.performAction("CreateVault", request,
                extractor);

        if (result == null) return null;
        return new VaultImpl(result.getResource());
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
}
