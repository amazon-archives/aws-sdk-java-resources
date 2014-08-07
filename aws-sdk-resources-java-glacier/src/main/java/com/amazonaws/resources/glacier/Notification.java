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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.glacier.model.DeleteVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsResult;
import com.amazonaws.services.glacier.model.SetVaultNotificationsRequest;

/**
 * The Notification resource.
 */
public interface Notification {
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
    boolean load(GetVaultNotificationsRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetVaultNotificationsRequest request,
            ResultCapture<GetVaultNotificationsResult> extractor);

    /**
     * Gets the value of the AccountId identifier.
     */
    String getAccountId();

    /**
     * Gets the value of the VaultName identifier.
     */
    String getVaultName();

    /**
     * Gets the value of the SNSTopic attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSNSTopic();

    /**
     * Gets the value of the Events attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<String> getEvents();

    /**
     * Retrieves the Vault referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs an action.
     */
    void delete(DeleteVaultNotificationsRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteVaultNotificationsRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void set(SetVaultNotificationsRequest request);

    /**
     * Performs an action.
     */
    void set(SetVaultNotificationsRequest request, ResultCapture<Void> extractor
            );
}
