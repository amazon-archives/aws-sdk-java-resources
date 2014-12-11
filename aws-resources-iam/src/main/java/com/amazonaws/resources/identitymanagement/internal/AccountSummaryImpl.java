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

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.AccountSummary;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryRequest;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryResult;

class AccountSummaryImpl implements AccountSummary {
    public static final ResourceCodec<AccountSummary> CODEC = new Codec();

    private final ResourceImpl resource;

    public AccountSummaryImpl(ResourceImpl resource) {
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
    public boolean load(GetAccountSummaryRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetAccountSummaryRequest request,
            ResultCapture<GetAccountSummaryResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public Map<String, Integer> getSummaryMap() {
        return (Map<String, Integer>) resource.getAttribute("SummaryMap");
    }

    private static class Codec implements ResourceCodec<AccountSummary> {
        @Override
        public AccountSummary transform(ResourceImpl resource) {
            return new AccountSummaryImpl(resource);
        }
    }
}
