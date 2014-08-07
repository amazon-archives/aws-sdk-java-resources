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
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetGroupRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetGroupRequest request, ResultCapture<GetGroupResult>
            extractor);

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
     * Performs an action.
     */
    void addUser(AddUserToGroupRequest request);

    /**
     * Performs an action.
     */
    void addUser(AddUserToGroupRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void update(UpdateGroupRequest request);

    /**
     * Performs an action.
     */
    void update(UpdateGroupRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    CreateGroupResult create(CreateGroupRequest request);

    /**
     * Performs an action.
     */
    CreateGroupResult create(CreateGroupRequest request,
            ResultCapture<CreateGroupResult> extractor);

    /**
     * Performs an action.
     */
    void removeUser(RemoveUserFromGroupRequest request);

    /**
     * Performs an action.
     */
    void removeUser(RemoveUserFromGroupRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteGroupRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteGroupRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void createPolicy(PutGroupPolicyRequest request);

    /**
     * Performs an action.
     */
    void createPolicy(PutGroupPolicyRequest request, ResultCapture<Void>
            extractor);
}
