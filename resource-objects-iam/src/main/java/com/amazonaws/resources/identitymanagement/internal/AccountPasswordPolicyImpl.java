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
package com.amazonaws.resources.identitymanagement.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.AccountPasswordPolicy;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
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

class AccountPasswordPolicyImpl implements AccountPasswordPolicy {
    public static final ResourceCodec<AccountPasswordPolicy> CODEC = new Codec()
            ;

    private final ResourceImpl resource;

    public AccountPasswordPolicyImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public boolean load() {
        return load(null, null);
    }

    @Override
    public boolean load(GetAccountPasswordPolicyRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetAccountPasswordPolicyRequest request,
            ResultCapture<GetAccountPasswordPolicyResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public Integer getMaxPasswordAge() {
        return (Integer) resource.getAttribute("MaxPasswordAge");
    }

    @Override
    public Boolean getRequireUppercaseCharacters() {
        return (Boolean) resource.getAttribute("RequireUppercaseCharacters");
    }

    @Override
    public Boolean getHardExpiry() {
        return (Boolean) resource.getAttribute("HardExpiry");
    }

    @Override
    public Boolean getRequireNumbers() {
        return (Boolean) resource.getAttribute("RequireNumbers");
    }

    @Override
    public Boolean getExpirePasswords() {
        return (Boolean) resource.getAttribute("ExpirePasswords");
    }

    @Override
    public Boolean getRequireSymbols() {
        return (Boolean) resource.getAttribute("RequireSymbols");
    }

    @Override
    public Boolean getAllowUsersToChangePassword() {
        return (Boolean) resource.getAttribute("AllowUsersToChangePassword");
    }

    @Override
    public Integer getPasswordReusePrevention() {
        return (Integer) resource.getAttribute("PasswordReusePrevention");
    }

    @Override
    public Boolean getRequireLowercaseCharacters() {
        return (Boolean) resource.getAttribute("RequireLowercaseCharacters");
    }

    @Override
    public Integer getMinimumPasswordLength() {
        return (Integer) resource.getAttribute("MinimumPasswordLength");
    }

    @Override
    public void update(UpdateAccountPasswordPolicyRequest request) {
        update(request, null);
    }

    @Override
    public void update(UpdateAccountPasswordPolicyRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Update", request, extractor);
    }

    @Override
    public void delete(DeleteAccountPasswordPolicyRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteAccountPasswordPolicyRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<AccountPasswordPolicy> {
        @Override
        public AccountPasswordPolicy transform(ResourceImpl resource) {
            return new AccountPasswordPolicyImpl(resource);
        }
    }
}
