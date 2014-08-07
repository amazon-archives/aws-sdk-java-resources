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
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileRequest
;
import com.amazonaws.services.identitymanagement.model.CreateLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.DeleteLoginProfileRequest
;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.UpdateLoginProfileRequest
;

/**
 * The LoginProfile resource.
 */
public interface LoginProfile {
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
    boolean load(GetLoginProfileRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetLoginProfileRequest request,
            ResultCapture<GetLoginProfileResult> extractor);

    /**
     * Gets the value of the UserName identifier.
     */
    String getUserName();

    /**
     * Gets the value of the PasswordResetRequired attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    Boolean getPasswordResetRequired();

    /**
     * Gets the value of the CreateDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateDate();

    /**
     * Retrieves the User referenced by this resource.
     */
    User getUser();

    /**
     * Performs an action.
     */
    void update(UpdateLoginProfileRequest request);

    /**
     * Performs an action.
     */
    void update(UpdateLoginProfileRequest request, ResultCapture<Void> extractor
            );

    /**
     * Performs an action.
     */
    CreateLoginProfileResult create(CreateLoginProfileRequest request);

    /**
     * Performs an action.
     */
    CreateLoginProfileResult create(CreateLoginProfileRequest request,
            ResultCapture<CreateLoginProfileResult> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteLoginProfileRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteLoginProfileRequest request, ResultCapture<Void> extractor
            );
}
