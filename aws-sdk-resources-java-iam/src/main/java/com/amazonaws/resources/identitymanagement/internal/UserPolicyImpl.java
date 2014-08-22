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

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.identitymanagement.UserPolicy;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyResult;
import com.amazonaws.services.identitymanagement.model.PutUserPolicyRequest;

class UserPolicyImpl implements UserPolicy {
    public static final ResourceCodec<UserPolicy> CODEC = new Codec();

    private final ResourceImpl resource;

    public UserPolicyImpl(ResourceImpl resource) {
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
    public boolean load(GetUserPolicyRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetUserPolicyRequest request,
            ResultCapture<GetUserPolicyResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getUserName() {
        return (String) resource.getIdentifier("UserName");
    }

    @Override
    public String getPolicyDocument() {
        return (String) resource.getAttribute("PolicyDocument");
    }

    @Override
    public User getUser() {
        ResourceImpl result = resource.getReference("User");
        if (result == null) return null;
        return new UserImpl(result);
    }

    @Override
    public void put(PutUserPolicyRequest request) {
        put(request, null);
    }

    @Override
    public void put(PutUserPolicyRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Put", request, extractor);
    }

    @Override
    public void put(String policyDocument) {
        put(policyDocument, (ResultCapture<Void>)null);
    }

    @Override
    public void put(String policyDocument, ResultCapture<Void> extractor) {
        PutUserPolicyRequest request = new PutUserPolicyRequest()
            .withPolicyDocument(policyDocument);
        put(request, extractor);
    }

    @Override
    public void delete(DeleteUserPolicyRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteUserPolicyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteUserPolicyRequest request = new DeleteUserPolicyRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<UserPolicy> {
        @Override
        public UserPolicy transform(ResourceImpl resource) {
            return new UserPolicyImpl(resource);
        }
    }
}
