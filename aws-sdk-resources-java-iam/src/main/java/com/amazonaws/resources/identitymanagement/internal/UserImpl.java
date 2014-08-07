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
import com.amazonaws.resources.identitymanagement.AccessKeyCollection;
import com.amazonaws.resources.identitymanagement.GroupCollection;
import com.amazonaws.resources.identitymanagement.LoginProfile;
import com.amazonaws.resources.identitymanagement.MfaDevice;
import com.amazonaws.resources.identitymanagement.MfaDeviceCollection;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.identitymanagement.UserPolicy;
import com.amazonaws.resources.identitymanagement.UserPolicyCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.AddUserToGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.DeleteUserRequest;
import com.amazonaws.services.identitymanagement.model.EnableMFADeviceRequest;
import com.amazonaws.services.identitymanagement.model.GetUserRequest;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserRequest;
import com.amazonaws.services.identitymanagement.model.ListMFADevicesRequest;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.PutUserPolicyRequest;
import
com.amazonaws.services.identitymanagement.model.RemoveUserFromGroupRequest;
import com.amazonaws.services.identitymanagement.model.UpdateUserRequest;

class UserImpl implements User {
    public static final ResourceCodec<User> CODEC = new Codec();

    private final ResourceImpl resource;

    public UserImpl(ResourceImpl resource) {
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
    public boolean load(GetUserRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetUserRequest request, ResultCapture<GetUserResult>
            extractor) {

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
    public String getUserId() {
        return (String) resource.getAttribute("UserId");
    }

    @Override
    public Date getCreateDate() {
        return (Date) resource.getAttribute("CreateDate");
    }

    @Override
    public String getPath() {
        return (String) resource.getAttribute("Path");
    }

    @Override
    public MfaDevice getMfaDevice(String serialNumber) {
        ResourceImpl result = resource.getSubResource("MfaDevice",
                serialNumber);

        if (result == null) return null;
        return new MfaDeviceImpl(result);
    }

    @Override
    public AccessKey getAccessKey(String id) {
        ResourceImpl result = resource.getSubResource("AccessKey", id);
        if (result == null) return null;
        return new AccessKeyImpl(result);
    }

    @Override
    public LoginProfile getLoginProfile() {
        ResourceImpl result = resource.getSubResource("LoginProfile", null);
        if (result == null) return null;
        return new LoginProfileImpl(result);
    }

    @Override
    public UserPolicy getUserPolicy(String name) {
        ResourceImpl result = resource.getSubResource("UserPolicy", name);
        if (result == null) return null;
        return new UserPolicyImpl(result);
    }

    @Override
    public GroupCollection getGroups() {
        return getGroups(null);
    }

    @Override
    public GroupCollection getGroups(ListGroupsForUserRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Groups",
                request);

        if (result == null) return null;
        return new GroupCollectionImpl(result);
    }

    @Override
    public UserPolicyCollection getPolicies() {
        return getPolicies(null);
    }

    @Override
    public UserPolicyCollection getPolicies(ListUserPoliciesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Policies",
                request);

        if (result == null) return null;
        return new UserPolicyCollectionImpl(result);
    }

    @Override
    public MfaDeviceCollection getMfaDevices() {
        return getMfaDevices(null);
    }

    @Override
    public MfaDeviceCollection getMfaDevices(ListMFADevicesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("MfaDevices",
                request);

        if (result == null) return null;
        return new MfaDeviceCollectionImpl(result);
    }

    @Override
    public AccessKeyCollection getAccessKeys() {
        return getAccessKeys(null);
    }

    @Override
    public AccessKeyCollection getAccessKeys(ListAccessKeysRequest request) {
        ResourceCollectionImpl result = resource.getCollection("AccessKeys",
                request);

        if (result == null) return null;
        return new AccessKeyCollectionImpl(result);
    }

    @Override
    public void addGroup(AddUserToGroupRequest request) {
        addGroup(request, null);
    }

    @Override
    public void addGroup(AddUserToGroupRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("AddGroup", request, extractor);
    }

    @Override
    public void update(UpdateUserRequest request) {
        update(request, null);
    }

    @Override
    public void update(UpdateUserRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Update", request, extractor);
    }

    @Override
    public void enableMfa(EnableMFADeviceRequest request) {
        enableMfa(request, null);
    }

    @Override
    public void enableMfa(EnableMFADeviceRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("EnableMfa", request, extractor);
    }

    @Override
    public void removeGroup(RemoveUserFromGroupRequest request) {
        removeGroup(request, null);
    }

    @Override
    public void removeGroup(RemoveUserFromGroupRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RemoveGroup", request, extractor);
    }

    @Override
    public AccessKey createAccessKey(CreateAccessKeyRequest request) {
        return createAccessKey(request, null);
    }

    @Override
    public AccessKey createAccessKey(CreateAccessKeyRequest request,
            ResultCapture<CreateAccessKeyResult> extractor) {

        ActionResult result = resource.performAction("CreateAccessKey", request,
                extractor);

        if (result == null) return null;
        return new AccessKeyImpl(result.getResource());
    }

    @Override
    public void delete(DeleteUserRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteUserRequest request, ResultCapture<Void> extractor)
            {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void createPolicy(PutUserPolicyRequest request) {
        createPolicy(request, null);
    }

    @Override
    public void createPolicy(PutUserPolicyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("CreatePolicy", request, extractor);
    }

    private static class Codec implements ResourceCodec<User> {
        @Override
        public User transform(ResourceImpl resource) {
            return new UserImpl(resource);
        }
    }
}
