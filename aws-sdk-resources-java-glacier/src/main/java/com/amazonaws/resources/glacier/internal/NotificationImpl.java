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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.glacier.Notification;
import com.amazonaws.resources.glacier.Vault;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.glacier.model.DeleteVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsResult;
import com.amazonaws.services.glacier.model.SetVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.VaultNotificationConfig;

class NotificationImpl implements Notification {
    public static final ResourceCodec<Notification> CODEC = new Codec();

    private final ResourceImpl resource;

    public NotificationImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public boolean load() {
        return load(null, null);
    }

    @Override
    public boolean load(GetVaultNotificationsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetVaultNotificationsRequest request,
            ResultCapture<GetVaultNotificationsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getAccountId() {
        return (String) resource.getIdentifier("AccountId");
    }

    @Override
    public String getVaultName() {
        return (String) resource.getIdentifier("VaultName");
    }

    @Override
    public String getSNSTopic() {
        return (String) resource.getAttribute("SNSTopic");
    }

    @Override
    public List<String> getEvents() {
        return (List<String>) resource.getAttribute("Events");
    }

    @Override
    public Vault getVault() {
        ResourceImpl result = resource.getReference("Vault");
        if (result == null) return null;
        return new VaultImpl(result);
    }

    @Override
    public void delete(DeleteVaultNotificationsRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteVaultNotificationsRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteVaultNotificationsRequest request = new
                DeleteVaultNotificationsRequest();

        delete(request, extractor);
    }

    @Override
    public void set(SetVaultNotificationsRequest request) {
        set(request, null);
    }

    @Override
    public void set(SetVaultNotificationsRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Set", request, extractor);
    }

    @Override
    public void set(VaultNotificationConfig vaultNotificationConfig) {
        set(vaultNotificationConfig, (ResultCapture<Void>)null);
    }

    @Override
    public void set(VaultNotificationConfig vaultNotificationConfig,
            ResultCapture<Void> extractor) {

        SetVaultNotificationsRequest request = new
                SetVaultNotificationsRequest()

            .withVaultNotificationConfig(vaultNotificationConfig);
        set(request, extractor);
    }

    private static class Codec implements ResourceCodec<Notification> {
        @Override
        public Notification transform(ResourceImpl resource) {
            return new NotificationImpl(resource);
        }
    }
}
