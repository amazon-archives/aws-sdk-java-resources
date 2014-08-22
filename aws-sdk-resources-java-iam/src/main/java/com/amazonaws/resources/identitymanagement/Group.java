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
import com.amazonaws.services.identitymanagement.model.CreateGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateGroupResult;
import com.amazonaws.services.identitymanagement.model.DeleteGroupRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupResult;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.PutGroupPolicyRequest;
import
com.amazonaws.services.identitymanagement.model.RemoveUserFromGroupRequest;
import com.amazonaws.services.identitymanagement.model.UpdateGroupRequest;

/**
 * The Group resource.
 */
public interface Group {
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
     * @see #load(GetGroupRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetGroupRequest
     */
    boolean load(GetGroupRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetGroupRequest
     */
    boolean load(GetGroupRequest request, ResultCapture<GetGroupResult>
            extractor);

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
     * Gets the value of the CreateDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateDate();

    /**
     * Gets the value of the GroupId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getGroupId();

    /**
     * Gets the value of the Path attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getPath();

    /**
     * Gets a subresource.
     */
    GroupPolicy getGroupPolicy(String name);

    /**
     * Retrieves the Users collection referenced by this resource.
     */
    UserCollection getUsers();

    /**
     * Retrieves the Users collection referenced by this resource.
     */
    UserCollection getUsers(GetGroupRequest request);

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    GroupPolicyCollection getPolicies();

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    GroupPolicyCollection getPolicies(ListGroupPoliciesRequest request);

    /**
     * Performs the <code>AddUser</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddUserToGroupRequest
     */
    void addUser(AddUserToGroupRequest request);

    /**
     * Performs the <code>AddUser</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddUserToGroupRequest
     */
    void addUser(AddUserToGroupRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>AddUser</code> action.
     *
     * @see #addUser(AddUserToGroupRequest)
     */
    void addUser(String userName);

    /**
     * The convenient method form for the <code>AddUser</code> action.
     *
     * @see #addUser(AddUserToGroupRequest, ResultCapture)
     */
    void addUser(String userName, ResultCapture<Void> extractor);

    /**
     * Performs the <code>Update</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Group</code> resource object associated with the result
     *         of this action.
     * @see UpdateGroupRequest
     */
    Group update(UpdateGroupRequest request);

    /**
     * Performs the <code>Update</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Group</code> resource object associated with the result
     *         of this action.
     * @see UpdateGroupRequest
     */
    Group update(UpdateGroupRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateGroupRequest)
     */
    Group update();

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateGroupRequest, ResultCapture)
     */
    Group update(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Create</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CreateGroupRequest
     */
    CreateGroupResult create(CreateGroupRequest request);

    /**
     * Performs the <code>Create</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CreateGroupRequest
     */
    CreateGroupResult create(CreateGroupRequest request,
            ResultCapture<CreateGroupResult> extractor);

    /**
     * The convenient method form for the <code>Create</code> action.
     *
     * @see #create(CreateGroupRequest)
     */
    CreateGroupResult create();

    /**
     * The convenient method form for the <code>Create</code> action.
     *
     * @see #create(CreateGroupRequest, ResultCapture)
     */
    CreateGroupResult create(ResultCapture<CreateGroupResult> extractor);

    /**
     * Performs the <code>RemoveUser</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemoveUserFromGroupRequest
     */
    void removeUser(RemoveUserFromGroupRequest request);

    /**
     * Performs the <code>RemoveUser</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemoveUserFromGroupRequest
     */
    void removeUser(RemoveUserFromGroupRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>RemoveUser</code> action.
     *
     * @see #removeUser(RemoveUserFromGroupRequest)
     */
    void removeUser(String userName);

    /**
     * The convenient method form for the <code>RemoveUser</code> action.
     *
     * @see #removeUser(RemoveUserFromGroupRequest, ResultCapture)
     */
    void removeUser(String userName, ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteGroupRequest
     */
    void delete(DeleteGroupRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteGroupRequest
     */
    void delete(DeleteGroupRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteGroupRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteGroupRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreatePolicy</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>GroupPolicy</code> resource object associated with the
     *         result of this action.
     * @see PutGroupPolicyRequest
     */
    com.amazonaws.resources.identitymanagement.GroupPolicy createPolicy(
            PutGroupPolicyRequest request);

    /**
     * Performs the <code>CreatePolicy</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Group</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>GroupName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>GroupPolicy</code> resource object associated with the
     *         result of this action.
     * @see PutGroupPolicyRequest
     */
    com.amazonaws.resources.identitymanagement.GroupPolicy createPolicy(
            PutGroupPolicyRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>CreatePolicy</code> action.
     *
     * @see #createPolicy(PutGroupPolicyRequest)
     */
    com.amazonaws.resources.identitymanagement.GroupPolicy createPolicy(String
            policyName, String policyDocument);

    /**
     * The convenient method form for the <code>CreatePolicy</code> action.
     *
     * @see #createPolicy(PutGroupPolicyRequest, ResultCapture)
     */
    com.amazonaws.resources.identitymanagement.GroupPolicy createPolicy(String
            policyName, String policyDocument, ResultCapture<Void> extractor);
}
