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
import com.amazonaws.resources.identitymanagement.Role;
import com.amazonaws.resources.identitymanagement.RolePolicy;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyResult;
import com.amazonaws.services.identitymanagement.model.PutRolePolicyRequest;

class RolePolicyImpl implements RolePolicy {
    public static final ResourceCodec<RolePolicy> CODEC = new Codec();

    private final ResourceImpl resource;

    public RolePolicyImpl(ResourceImpl resource) {
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
    public boolean load(GetRolePolicyRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetRolePolicyRequest request,
            ResultCapture<GetRolePolicyResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getRoleName() {
        return (String) resource.getIdentifier("RoleName");
    }

    @Override
    public String getPolicyDocument() {
        return (String) resource.getAttribute("PolicyDocument");
    }

    @Override
    public Role getRole() {
        ResourceImpl result = resource.getReference("Role");
        if (result == null) return null;
        return new RoleImpl(result);
    }

    @Override
    public void put(PutRolePolicyRequest request) {
        put(request, null);
    }

    @Override
    public void put(PutRolePolicyRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Put", request, extractor);
    }

    @Override
    public void delete(DeleteRolePolicyRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteRolePolicyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<RolePolicy> {
        @Override
        public RolePolicy transform(ResourceImpl resource) {
            return new RolePolicyImpl(resource);
        }
    }
}
