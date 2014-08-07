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
import
com.amazonaws.services.identitymanagement.model.DeleteAccountPasswordPolicyRequest
;
import
com.amazonaws.services.identitymanagement.model.GetAccountPasswordPolicyRequest;
import
com.amazonaws.services.identitymanagement.model.GetAccountPasswordPolicyResult;
import
com.amazonaws.services.identitymanagement.model.UpdateAccountPasswordPolicyRequest
;

/**
 * The AccountPasswordPolicy resource.
 */
public interface AccountPasswordPolicy {
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
    boolean load(GetAccountPasswordPolicyRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetAccountPasswordPolicyRequest request,
            ResultCapture<GetAccountPasswordPolicyResult> extractor);

    /**
     * Gets the value of the MaxPasswordAge attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Integer getMaxPasswordAge();

    /**
     * Gets the value of the RequireUppercaseCharacters attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    Boolean getRequireUppercaseCharacters();

    /**
     * Gets the value of the HardExpiry attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getHardExpiry();

    /**
     * Gets the value of the RequireNumbers attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getRequireNumbers();

    /**
     * Gets the value of the ExpirePasswords attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getExpirePasswords();

    /**
     * Gets the value of the RequireSymbols attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getRequireSymbols();

    /**
     * Gets the value of the AllowUsersToChangePassword attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    Boolean getAllowUsersToChangePassword();

    /**
     * Gets the value of the PasswordReusePrevention attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    Integer getPasswordReusePrevention();

    /**
     * Gets the value of the RequireLowercaseCharacters attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    Boolean getRequireLowercaseCharacters();

    /**
     * Gets the value of the MinimumPasswordLength attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    Integer getMinimumPasswordLength();

    /**
     * Performs an action.
     */
    void update(UpdateAccountPasswordPolicyRequest request);

    /**
     * Performs an action.
     */
    void update(UpdateAccountPasswordPolicyRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteAccountPasswordPolicyRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteAccountPasswordPolicyRequest request, ResultCapture<Void>
            extractor);
}
