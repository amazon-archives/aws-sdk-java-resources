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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.InstanceProfile;
import com.amazonaws.resources.identitymanagement.Role;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import
com.amazonaws.services.identitymanagement.model.AddRoleToInstanceProfileRequest;
import
com.amazonaws.services.identitymanagement.model.DeleteInstanceProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetInstanceProfileRequest
;
import com.amazonaws.services.identitymanagement.model.GetInstanceProfileResult;
import
com.amazonaws.services.identitymanagement.model.RemoveRoleFromInstanceProfileRequest
;

class InstanceProfileImpl implements InstanceProfile {
    public static final ResourceCodec<InstanceProfile> CODEC = new Codec();

    private final ResourceImpl resource;

    public InstanceProfileImpl(ResourceImpl resource) {
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
    public boolean load(GetInstanceProfileRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetInstanceProfileRequest request,
            ResultCapture<GetInstanceProfileResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getArn() {
        return (String) resource.getAttribute("Arn");
    }

    @Override
    public Date getCreateDate() {
        return (Date) resource.getAttribute("CreateDate");
    }

    @Override
    public String getInstanceProfileId() {
        return (String) resource.getAttribute("InstanceProfileId");
    }

    @Override
    public String getPath() {
        return (String) resource.getAttribute("Path");
    }

    @Override
    public List<Role> getRoles() {
        List<ResourceImpl> result = resource.getMultiReference("Roles");
        if (result == null) return null;

        List<Role> transformed = new ArrayList<>(result.size());
        for (ResourceImpl item : result) {
            transformed.add(new RoleImpl(item));
        }
        return transformed;
    }

    @Override
    public void removeRole(RemoveRoleFromInstanceProfileRequest request) {
        removeRole(request, null);
    }

    @Override
    public void removeRole(RemoveRoleFromInstanceProfileRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RemoveRole", request, extractor);
    }

    @Override
    public void addRole(AddRoleToInstanceProfileRequest request) {
        addRole(request, null);
    }

    @Override
    public void addRole(AddRoleToInstanceProfileRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("AddRole", request, extractor);
    }

    @Override
    public void delete(DeleteInstanceProfileRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteInstanceProfileRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<InstanceProfile> {
        @Override
        public InstanceProfile transform(ResourceImpl resource) {
            return new InstanceProfileImpl(resource);
        }
    }
}
