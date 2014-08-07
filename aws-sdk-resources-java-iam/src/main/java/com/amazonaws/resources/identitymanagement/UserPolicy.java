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

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.identitymanagement.model.DeleteUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyResult;
import com.amazonaws.services.identitymanagement.model.PutUserPolicyRequest;

/**
 * The UserPolicy resource.
 */
public interface UserPolicy {
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
    boolean load(GetUserPolicyRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetUserPolicyRequest request,
            ResultCapture<GetUserPolicyResult> extractor);

    /**
     * Gets the value of the Name identifier.
     */
    String getName();

    /**
     * Gets the value of the UserName identifier.
     */
    String getUserName();

    /**
     * Gets the value of the PolicyDocument attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPolicyDocument();

    /**
     * Retrieves the User referenced by this resource.
     */
    User getUser();

    /**
     * Performs an action.
     */
    void put(PutUserPolicyRequest request);

    /**
     * Performs an action.
     */
    void put(PutUserPolicyRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteUserPolicyRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteUserPolicyRequest request, ResultCapture<Void> extractor);
}
