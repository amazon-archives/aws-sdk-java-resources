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
import com.amazonaws.resources.identitymanagement.Group;
import com.amazonaws.resources.identitymanagement.GroupPolicy;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.DeleteGroupPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyResult;
import com.amazonaws.services.identitymanagement.model.PutGroupPolicyRequest;

class GroupPolicyImpl implements GroupPolicy {
    public static final ResourceCodec<GroupPolicy> CODEC = new Codec();

    private final ResourceImpl resource;

    public GroupPolicyImpl(ResourceImpl resource) {
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
    public boolean load(GetGroupPolicyRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetGroupPolicyRequest request,
            ResultCapture<GetGroupPolicyResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getGroupName() {
        return (String) resource.getIdentifier("GroupName");
    }

    @Override
    public String getPolicyDocument() {
        return (String) resource.getAttribute("PolicyDocument");
    }

    @Override
    public Group getGroup() {
        ResourceImpl result = resource.getReference("Group");
        if (result == null) return null;
        return new GroupImpl(result);
    }

    @Override
    public void put(PutGroupPolicyRequest request) {
        put(request, null);
    }

    @Override
    public void put(PutGroupPolicyRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Put", request, extractor);
    }

    @Override
    public void put(String policyDocument) {
        put(policyDocument, (ResultCapture<Void>)null);
    }

    @Override
    public void put(String policyDocument, ResultCapture<Void> extractor) {
        PutGroupPolicyRequest request = new PutGroupPolicyRequest()
            .withPolicyDocument(policyDocument);
        put(request, extractor);
    }

    @Override
    public void delete(DeleteGroupPolicyRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteGroupPolicyRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteGroupPolicyRequest request = new DeleteGroupPolicyRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<GroupPolicy> {
        @Override
        public GroupPolicy transform(ResourceImpl resource) {
            return new GroupPolicyImpl(resource);
        }
    }
}
