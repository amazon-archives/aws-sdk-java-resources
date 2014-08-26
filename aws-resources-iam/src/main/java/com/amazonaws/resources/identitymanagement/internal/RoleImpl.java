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
import com.amazonaws.resources.identitymanagement.InstanceProfileCollection;
import com.amazonaws.resources.identitymanagement.Role;
import com.amazonaws.resources.identitymanagement.RolePolicy;
import com.amazonaws.resources.identitymanagement.RolePolicyCollection;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteRoleRequest;
import com.amazonaws.services.identitymanagement.model.GetRoleRequest;
import com.amazonaws.services.identitymanagement.model.GetRoleResult;
import
com.amazonaws.services.identitymanagement.model.ListInstanceProfilesForRoleRequest
;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesRequest;
import
com.amazonaws.services.identitymanagement.model.UpdateAssumeRolePolicyRequest;

class RoleImpl implements Role {
    public static final ResourceCodec<Role> CODEC = new Codec();

    private final ResourceImpl resource;

    public RoleImpl(ResourceImpl resource) {
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
    public boolean load(GetRoleRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetRoleRequest request, ResultCapture<GetRoleResult>
            extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getAssumeRolePolicyDocument() {
        return (String) resource.getAttribute("AssumeRolePolicyDocument");
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
    public String getRoleId() {
        return (String) resource.getAttribute("RoleId");
    }

    @Override
    public String getPath() {
        return (String) resource.getAttribute("Path");
    }

    @Override
    public RolePolicy getRolePolicy(String name) {
        ResourceImpl result = resource.getSubResource("RolePolicy", name);
        if (result == null) return null;
        return new RolePolicyImpl(result);
    }

    @Override
    public RolePolicyCollection getPolicies() {
        return getPolicies(null);
    }

    @Override
    public RolePolicyCollection getPolicies(ListRolePoliciesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Policies",
                request);

        if (result == null) return null;
        return new RolePolicyCollectionImpl(result);
    }

    @Override
    public InstanceProfileCollection getInstanceProfiles() {
        return getInstanceProfiles(null);
    }

    @Override
    public InstanceProfileCollection getInstanceProfiles(
            ListInstanceProfilesForRoleRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("InstanceProfiles", request);

        if (result == null) return null;
        return new InstanceProfileCollectionImpl(result);
    }

    @Override
    public void delete(DeleteRoleRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteRoleRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void updateAssumeRolePolicy(UpdateAssumeRolePolicyRequest request) {
        updateAssumeRolePolicy(request, null);
    }

    @Override
    public void updateAssumeRolePolicy(UpdateAssumeRolePolicyRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("UpdateAssumeRolePolicy", request, extractor);
    }

    private static class Codec implements ResourceCodec<Role> {
        @Override
        public Role transform(ResourceImpl resource) {
            return new RoleImpl(resource);
        }
    }
}
