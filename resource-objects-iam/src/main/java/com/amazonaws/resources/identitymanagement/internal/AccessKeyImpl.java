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
import com.amazonaws.resources.identitymanagement.AccessKey;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyRequest;

class AccessKeyImpl implements AccessKey {
    public static final ResourceCodec<AccessKey> CODEC = new Codec();

    private final ResourceImpl resource;

    public AccessKeyImpl(ResourceImpl resource) {
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
    public String getUserName() {
        return (String) resource.getIdentifier("UserName");
    }

    @Override
    public String getSecretAccessKey() {
        return (String) resource.getAttribute("SecretAccessKey");
    }

    @Override
    public String getStatus() {
        return (String) resource.getAttribute("Status");
    }

    @Override
    public Date getCreateDate() {
        return (Date) resource.getAttribute("CreateDate");
    }

    @Override
    public User getUser() {
        ResourceImpl result = resource.getReference("User");
        if (result == null) return null;
        return new UserImpl(result);
    }

    @Override
    public void deactivate(UpdateAccessKeyRequest request) {
        deactivate(request, null);
    }

    @Override
    public void deactivate(UpdateAccessKeyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Deactivate", request, extractor);
    }

    @Override
    public void activate(UpdateAccessKeyRequest request) {
        activate(request, null);
    }

    @Override
    public void activate(UpdateAccessKeyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Activate", request, extractor);
    }

    @Override
    public void delete(DeleteAccessKeyRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteAccessKeyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<AccessKey> {
        @Override
        public AccessKey transform(ResourceImpl resource) {
            return new AccessKeyImpl(resource);
        }
    }
}
