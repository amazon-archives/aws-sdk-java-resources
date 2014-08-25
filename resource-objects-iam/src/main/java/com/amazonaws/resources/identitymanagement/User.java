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
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(GetUserRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetUserRequest
     */
    boolean load(GetUserRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetUserRequest
     */
    boolean load(GetUserRequest request, ResultCapture<GetUserResult> extractor)
            ;

    /**
     * Gets the value of the Name identifier. This method always directly
     * returns the identifier and never involves a service call.
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
     * Performs the <code>AddGroup</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddUserToGroupRequest
     */
    void addGroup(AddUserToGroupRequest request);

    /**
     * Performs the <code>AddGroup</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddUserToGroupRequest
     */
    void addGroup(AddUserToGroupRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>AddGroup</code> action.
     *
     * @see #addGroup(AddUserToGroupRequest)
     */
    void addGroup(String groupName);

    /**
     * The convenient method form for the <code>AddGroup</code> action.
     *
     * @see #addGroup(AddUserToGroupRequest, ResultCapture)
     */
    void addGroup(String groupName, ResultCapture<Void> extractor);

    /**
     * Performs the <code>Update</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>User</code> resource object associated with the result
     *         of this action.
     * @see UpdateUserRequest
     */
    User update(UpdateUserRequest request);

    /**
     * Performs the <code>Update</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>User</code> resource object associated with the result
     *         of this action.
     * @see UpdateUserRequest
     */
    User update(UpdateUserRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateUserRequest)
     */
    User update();

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateUserRequest, ResultCapture)
     */
    User update(ResultCapture<Void> extractor);

    /**
     * Performs the <code>EnableMfa</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>MfaDevice</code> resource object associated with the
     *         result of this action.
     * @see EnableMFADeviceRequest
     */
    com.amazonaws.resources.identitymanagement.MfaDevice enableMfa(
            EnableMFADeviceRequest request);

    /**
     * Performs the <code>EnableMfa</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>MfaDevice</code> resource object associated with the
     *         result of this action.
     * @see EnableMFADeviceRequest
     */
    com.amazonaws.resources.identitymanagement.MfaDevice enableMfa(
            EnableMFADeviceRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>EnableMfa</code> action.
     *
     * @see #enableMfa(EnableMFADeviceRequest)
     */
    com.amazonaws.resources.identitymanagement.MfaDevice enableMfa(String
            serialNumber, String authenticationCode1, String authenticationCode2
            );

    /**
     * The convenient method form for the <code>EnableMfa</code> action.
     *
     * @see #enableMfa(EnableMFADeviceRequest, ResultCapture)
     */
    com.amazonaws.resources.identitymanagement.MfaDevice enableMfa(String
            serialNumber, String authenticationCode1, String authenticationCode2
            , ResultCapture<Void> extractor);

    /**
     * Performs the <code>RemoveGroup</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemoveUserFromGroupRequest
     */
    void removeGroup(RemoveUserFromGroupRequest request);

    /**
     * Performs the <code>RemoveGroup</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemoveUserFromGroupRequest
     */
    void removeGroup(RemoveUserFromGroupRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>RemoveGroup</code> action.
     *
     * @see #removeGroup(RemoveUserFromGroupRequest)
     */
    void removeGroup(String groupName);

    /**
     * The convenient method form for the <code>RemoveGroup</code> action.
     *
     * @see #removeGroup(RemoveUserFromGroupRequest, ResultCapture)
     */
    void removeGroup(String groupName, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateAccessKey</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>AccessKey</code> resource object associated with the
     *         result of this action.
     * @see CreateAccessKeyRequest
     */
    com.amazonaws.resources.identitymanagement.AccessKey createAccessKey(
            CreateAccessKeyRequest request);

    /**
     * Performs the <code>CreateAccessKey</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>AccessKey</code> resource object associated with the
     *         result of this action.
     * @see CreateAccessKeyRequest
     */
    com.amazonaws.resources.identitymanagement.AccessKey createAccessKey(
            CreateAccessKeyRequest request, ResultCapture<CreateAccessKeyResult>
            extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteUserRequest
     */
    void delete(DeleteUserRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteUserRequest
     */
    void delete(DeleteUserRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteUserRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteUserRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreatePolicy</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>UserPolicy</code> resource object associated with the
     *         result of this action.
     * @see PutUserPolicyRequest
     */
    com.amazonaws.resources.identitymanagement.UserPolicy createPolicy(
            PutUserPolicyRequest request);

    /**
     * Performs the <code>CreatePolicy</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>User</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>UserPolicy</code> resource object associated with the
     *         result of this action.
     * @see PutUserPolicyRequest
     */
    com.amazonaws.resources.identitymanagement.UserPolicy createPolicy(
            PutUserPolicyRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>CreatePolicy</code> action.
     *
     * @see #createPolicy(PutUserPolicyRequest)
     */
    com.amazonaws.resources.identitymanagement.UserPolicy createPolicy(String
            policyName, String policyDocument);

    /**
     * The convenient method form for the <code>CreatePolicy</code> action.
     *
     * @see #createPolicy(PutUserPolicyRequest, ResultCapture)
     */
    com.amazonaws.resources.identitymanagement.UserPolicy createPolicy(String
            policyName, String policyDocument, ResultCapture<Void> extractor);
}
