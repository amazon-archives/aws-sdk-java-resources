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
package com.amazonaws.resources.identitymanagement;

import java.util.Date;

import com.amazonaws.resources.ResultCapture;
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

/**
 * The User resource.
 */
public interface User {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetUserRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetUserRequest request, ResultCapture<GetUserResult> extractor)
            ;

    /**
     * Gets the value of the Name identifier.
     */
    String getName();

    /**
     * Gets the value of the Arn attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getArn();

    /**
     * Gets the value of the UserId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getUserId();

    /**
     * Gets the value of the CreateDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateDate();

    /**
     * Gets the value of the Path attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getPath();

    /**
     * Gets a subresource.
     */
    MfaDevice getMfaDevice(String serialNumber);

    /**
     * Gets a subresource.
     */
    AccessKey getAccessKey(String id);

    /**
     * Gets a subresource.
     */
    LoginProfile getLoginProfile();

    /**
     * Gets a subresource.
     */
    UserPolicy getUserPolicy(String name);

    /**
     * Retrieves the Groups collection referenced by this resource.
     */
    GroupCollection getGroups();

    /**
     * Retrieves the Groups collection referenced by this resource.
     */
    GroupCollection getGroups(ListGroupsForUserRequest request);

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    UserPolicyCollection getPolicies();

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    UserPolicyCollection getPolicies(ListUserPoliciesRequest request);

    /**
     * Retrieves the MfaDevices collection referenced by this resource.
     */
    MfaDeviceCollection getMfaDevices();

    /**
     * Retrieves the MfaDevices collection referenced by this resource.
     */
    MfaDeviceCollection getMfaDevices(ListMFADevicesRequest request);

    /**
     * Retrieves the AccessKeys collection referenced by this resource.
     */
    AccessKeyCollection getAccessKeys();

    /**
     * Retrieves the AccessKeys collection referenced by this resource.
     */
    AccessKeyCollection getAccessKeys(ListAccessKeysRequest request);

    /**
     * Performs an action.
     */
    void addGroup(AddUserToGroupRequest request);

    /**
     * Performs an action.
     */
    void addGroup(AddUserToGroupRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void update(UpdateUserRequest request);

    /**
     * Performs an action.
     */
    void update(UpdateUserRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void enableMfa(EnableMFADeviceRequest request);

    /**
     * Performs an action.
     */
    void enableMfa(EnableMFADeviceRequest request, ResultCapture<Void> extractor
            );

    /**
     * Performs an action.
     */
    void removeGroup(RemoveUserFromGroupRequest request);

    /**
     * Performs an action.
     */
    void removeGroup(RemoveUserFromGroupRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    AccessKey createAccessKey(CreateAccessKeyRequest request);

    /**
     * Performs an action.
     */
    AccessKey createAccessKey(CreateAccessKeyRequest request,
            ResultCapture<CreateAccessKeyResult> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteUserRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteUserRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void createPolicy(PutUserPolicyRequest request);

    /**
     * Performs an action.
     */
    void createPolicy(PutUserPolicyRequest request, ResultCapture<Void>
            extractor);
}
