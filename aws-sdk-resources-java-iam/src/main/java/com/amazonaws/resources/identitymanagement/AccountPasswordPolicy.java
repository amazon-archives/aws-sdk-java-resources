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
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(GetAccountPasswordPolicyRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetAccountPasswordPolicyRequest
     */
    boolean load(GetAccountPasswordPolicyRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetAccountPasswordPolicyRequest
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
     * Performs the <code>Update</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccountPasswordPolicyRequest
     */
    void update(UpdateAccountPasswordPolicyRequest request);

    /**
     * Performs the <code>Update</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccountPasswordPolicyRequest
     */
    void update(UpdateAccountPasswordPolicyRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @see DeleteAccountPasswordPolicyRequest
     */
    void delete(DeleteAccountPasswordPolicyRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccountPasswordPolicy</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     * </ul>
     *
     * <p>
     *
     * @see DeleteAccountPasswordPolicyRequest
     */
    void delete(DeleteAccountPasswordPolicyRequest request, ResultCapture<Void>
            extractor);
}
