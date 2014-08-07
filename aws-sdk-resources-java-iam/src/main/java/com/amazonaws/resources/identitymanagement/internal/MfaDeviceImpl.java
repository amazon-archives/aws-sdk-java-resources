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
package com.amazonaws.resources.identitymanagement.internal;

import java.util.Date;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.MfaDevice;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import
com.amazonaws.services.identitymanagement.model.DeactivateMFADeviceRequest;
import com.amazonaws.services.identitymanagement.model.EnableMFADeviceRequest;
import com.amazonaws.services.identitymanagement.model.ResyncMFADeviceRequest;

class MfaDeviceImpl implements MfaDevice {
    public static final ResourceCodec<MfaDevice> CODEC = new Codec();

    private final ResourceImpl resource;

    public MfaDeviceImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getSerialNumber() {
        return (String) resource.getIdentifier("SerialNumber");
    }

    @Override
    public String getUserName() {
        return (String) resource.getIdentifier("UserName");
    }

    @Override
    public Date getEnableDate() {
        return (Date) resource.getAttribute("EnableDate");
    }

    @Override
    public User getUser() {
        ResourceImpl result = resource.getReference("User");
        if (result == null) return null;
        return new UserImpl(result);
    }

    @Override
    public void enable(EnableMFADeviceRequest request) {
        enable(request, null);
    }

    @Override
    public void enable(EnableMFADeviceRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Enable", request, extractor);
    }

    @Override
    public void deactivate(DeactivateMFADeviceRequest request) {
        deactivate(request, null);
    }

    @Override
    public void deactivate(DeactivateMFADeviceRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Deactivate", request, extractor);
    }

    @Override
    public void resync(ResyncMFADeviceRequest request) {
        resync(request, null);
    }

    @Override
    public void resync(ResyncMFADeviceRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Resync", request, extractor);
    }

    private static class Codec implements ResourceCodec<MfaDevice> {
        @Override
        public MfaDevice transform(ResourceImpl resource) {
            return new MfaDeviceImpl(resource);
        }
    }
}
