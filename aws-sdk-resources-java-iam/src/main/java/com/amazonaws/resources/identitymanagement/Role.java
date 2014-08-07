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
import com.amazonaws.services.identitymanagement.model.DeleteRoleRequest;
import com.amazonaws.services.identitymanagement.model.GetRoleRequest;
import com.amazonaws.services.identitymanagement.model.GetRoleResult;
import
com.amazonaws.services.identitymanagement.model.ListInstanceProfilesForRoleRequest
;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesRequest;
import
com.amazonaws.services.identitymanagement.model.UpdateAssumeRolePolicyRequest;

/**
 * The Role resource.
 */
public interface Role {
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
    boolean load(GetRoleRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetRoleRequest request, ResultCapture<GetRoleResult> extractor)
            ;

    /**
     * Gets the value of the Name identifier.
     */
    String getName();

    /**
     * Gets the value of the AssumeRolePolicyDocument attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    String getAssumeRolePolicyDocument();

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
     * Gets the value of the RoleId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRoleId();

    /**
     * Gets the value of the Path attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getPath();

    /**
     * Gets a subresource.
     */
    RolePolicy getRolePolicy(String name);

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    RolePolicyCollection getPolicies();

    /**
     * Retrieves the Policies collection referenced by this resource.
     */
    RolePolicyCollection getPolicies(ListRolePoliciesRequest request);

    /**
     * Retrieves the InstanceProfiles collection referenced by this resource.
     */
    InstanceProfileCollection getInstanceProfiles();

    /**
     * Retrieves the InstanceProfiles collection referenced by this resource.
     */
    InstanceProfileCollection getInstanceProfiles(
            ListInstanceProfilesForRoleRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteRoleRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteRoleRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void updateAssumeRolePolicy(UpdateAssumeRolePolicyRequest request);

    /**
     * Performs an action.
     */
    void updateAssumeRolePolicy(UpdateAssumeRolePolicyRequest request,
            ResultCapture<Void> extractor);
}
