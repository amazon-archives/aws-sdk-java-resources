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
import com.amazonaws.resources.identitymanagement.LoginProfile;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileRequest
;
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.DeleteLoginProfileRequest
;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.UpdateLoginProfileRequest
;

class LoginProfileImpl implements LoginProfile {
    public static final ResourceCodec<LoginProfile> CODEC = new Codec();

    private final ResourceImpl resource;

    public LoginProfileImpl(ResourceImpl resource) {
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
    public boolean load(GetLoginProfileRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetLoginProfileRequest request,
            ResultCapture<GetLoginProfileResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getUserName() {
        return (String) resource.getIdentifier("UserName");
    }

    @Override
    public Boolean getPasswordResetRequired() {
        return (Boolean) resource.getAttribute("PasswordResetRequired");
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
    public void update(UpdateLoginProfileRequest request) {
        update(request, null);
    }

    @Override
    public void update(UpdateLoginProfileRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Update", request, extractor);
    }

    @Override
    public CreateLoginProfileResult create(CreateLoginProfileRequest request) {
        return create(request, null);
    }

    @Override
    public CreateLoginProfileResult create(CreateLoginProfileRequest request,
            ResultCapture<CreateLoginProfileResult> extractor) {

        ActionResult result = resource.performAction("Create", request,
                extractor);

        if (result == null) return null;
        return (CreateLoginProfileResult) result.getData();
    }

    @Override
    public void delete(DeleteLoginProfileRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteLoginProfileRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<LoginProfile> {
        @Override
        public LoginProfile transform(ResourceImpl resource) {
            return new LoginProfileImpl(resource);
        }
    }
}
