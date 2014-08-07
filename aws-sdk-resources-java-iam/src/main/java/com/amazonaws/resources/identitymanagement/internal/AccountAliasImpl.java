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
import com.amazonaws.resources.identitymanagement.AccountAlias;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteAccountAliasRequest
;

class AccountAliasImpl implements AccountAlias {
    public static final ResourceCodec<AccountAlias> CODEC = new Codec();

    private final ResourceImpl resource;

    public AccountAliasImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public void delete(DeleteAccountAliasRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteAccountAliasRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<AccountAlias> {
        @Override
        public AccountAlias transform(ResourceImpl resource) {
            return new AccountAliasImpl(resource);
        }
    }
}
