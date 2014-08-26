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

import java.nio.ByteBuffer;
import java.util.Date;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.identitymanagement.VirtualMfaDevice;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import
com.amazonaws.services.identitymanagement.model.DeleteVirtualMFADeviceRequest;

class VirtualMfaDeviceImpl implements VirtualMfaDevice {
    public static final ResourceCodec<VirtualMfaDevice> CODEC = new Codec();

    private final ResourceImpl resource;

    public VirtualMfaDeviceImpl(ResourceImpl resource) {
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
    public Date getEnableDate() {
        return (Date) resource.getAttribute("EnableDate");
    }

    @Override
    public ByteBuffer getQRCodePNG() {
        return (ByteBuffer) resource.getAttribute("QRCodePNG");
    }

    @Override
    public ByteBuffer getBase32StringSeed() {
        return (ByteBuffer) resource.getAttribute("Base32StringSeed");
    }

    @Override
    public User getUser() {
        ResourceImpl result = resource.getReference("User");
        if (result == null) return null;
        return new UserImpl(result);
    }

    @Override
    public void delete(DeleteVirtualMFADeviceRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteVirtualMFADeviceRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<VirtualMfaDevice> {
        @Override
        public VirtualMfaDevice transform(ResourceImpl resource) {
            return new VirtualMfaDeviceImpl(resource);
        }
    }
}
