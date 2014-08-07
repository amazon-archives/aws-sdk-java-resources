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

/**
 * The InstanceProfile resource.
 */
public interface InstanceProfile {
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
    boolean load(GetInstanceProfileRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetInstanceProfileRequest request,
            ResultCapture<GetInstanceProfileResult> extractor);

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
     * Gets the value of the InstanceProfileId attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getInstanceProfileId();

    /**
     * Gets the value of the Path attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getPath();

    /**
     * Retrieves the Roles referenced by this resource.
     */
    Role getRoles();

    /**
     * Performs an action.
     */
    void removeRole(RemoveRoleFromInstanceProfileRequest request);

    /**
     * Performs an action.
     */
    void removeRole(RemoveRoleFromInstanceProfileRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void addRole(AddRoleToInstanceProfileRequest request);

    /**
     * Performs an action.
     */
    void addRole(AddRoleToInstanceProfileRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteInstanceProfileRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteInstanceProfileRequest request, ResultCapture<Void>
            extractor);
}
