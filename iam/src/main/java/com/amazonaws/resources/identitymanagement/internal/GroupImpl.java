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

import java.util.Date;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.identitymanagement.Group;
import com.amazonaws.resources.identitymanagement.GroupPolicy;
import com.amazonaws.resources.identitymanagement.GroupPolicyCollection;
import com.amazonaws.resources.identitymanagement.UserCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.identitymanagement.model.AddUserToGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateGroupResult;
import com.amazonaws.services.identitymanagement.model.DeleteGroupRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupResult;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.PutGroupPolicyRequest;
import
com.amazonaws.services.identitymanagement.model.RemoveUserFromGroupRequest;
import com.amazonaws.services.identitymanagement.model.UpdateGroupRequest;

class GroupImpl implements Group {
    public static final ResourceCodec<Group> CODEC = new Codec();

    private final ResourceImpl resource;

    public GroupImpl(ResourceImpl resource) {
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
    public boolean load(GetGroupRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetGroupRequest request, ResultCapture<GetGroupResult>
            extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getArn() {
        return (String) resource.getAttribute("Arn");
    }

    @Override
    public Date getCreateDate() {
        return (Date) resource.getAttribute("CreateDate");
    }

    @Override
    public String getGroupId() {
        return (String) resource.getAttribute("GroupId");
    }

    @Override
    public String getPath() {
        return (String) resource.getAttribute("Path");
    }

    @Override
    public GroupPolicy getGroupPolicy(String name) {
        ResourceImpl result = resource.getSubResource("GroupPolicy", name);
        if (result == null) return null;
        return new GroupPolicyImpl(result);
    }

    @Override
    public UserCollection getUsers() {
        return getUsers(null);
    }

    @Override
    public UserCollection getUsers(GetGroupRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Users",
                request);

        if (result == null) return null;
        return new UserCollectionImpl(result);
    }

    @Override
    public GroupPolicyCollection getPolicies() {
        return getPolicies(null);
    }

    @Override
    public GroupPolicyCollection getPolicies(ListGroupPoliciesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Policies",
                request);

        if (result == null) return null;
        return new GroupPolicyCollectionImpl(result);
    }

    @Override
    public void addUser(AddUserToGroupRequest request) {
        addUser(request, null);
    }

    @Override
    public void addUser(AddUserToGroupRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("AddUser", request, extractor);
    }

    @Override
    public void addUser(String userName) {
        addUser(userName, (ResultCapture<Void>)null);
    }

    @Override
    public void addUser(String userName, ResultCapture<Void> extractor) {
        AddUserToGroupRequest request = new AddUserToGroupRequest()
            .withUserName(userName);
        addUser(request, extractor);
    }

    @Override
    public Group update(UpdateGroupRequest request) {
        return update(request, null);
    }

    @Override
    public Group update(UpdateGroupRequest request, ResultCapture<Void>
            extractor) {

        ActionResult result = resource.performAction("Update", request,
                extractor);

        if (result == null) return null;
        return new GroupImpl(result.getResource());
    }

    @Override
    public Group update() {
        return update((ResultCapture<Void>)null);
    }

    @Override
    public Group update(ResultCapture<Void> extractor) {
        UpdateGroupRequest request = new UpdateGroupRequest();
        return update(request, extractor);
    }

    @Override
    public CreateGroupResult create(CreateGroupRequest request) {
        return create(request, null);
    }

    @Override
    public CreateGroupResult create(CreateGroupRequest request,
            ResultCapture<CreateGroupResult> extractor) {

        ActionResult result = resource.performAction("Create", request,
                extractor);

        if (result == null) return null;
        return (CreateGroupResult) result.getData();
    }

    @Override
    public CreateGroupResult create() {
        return create((ResultCapture<CreateGroupResult>)null);
    }

    @Override
    public CreateGroupResult create(ResultCapture<CreateGroupResult> extractor)
            {

        CreateGroupRequest request = new CreateGroupRequest();
        return create(request, extractor);
    }

    @Override
    public void removeUser(RemoveUserFromGroupRequest request) {
        removeUser(request, null);
    }

    @Override
    public void removeUser(RemoveUserFromGroupRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RemoveUser", request, extractor);
    }

    @Override
    public void removeUser(String userName) {
        removeUser(userName, (ResultCapture<Void>)null);
    }

    @Override
    public void removeUser(String userName, ResultCapture<Void> extractor) {
        RemoveUserFromGroupRequest request = new RemoveUserFromGroupRequest()
            .withUserName(userName);
        removeUser(request, extractor);
    }

    @Override
    public void delete(DeleteGroupRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteGroupRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteGroupRequest request = new DeleteGroupRequest();
        delete(request, extractor);
    }

    @Override
    public GroupPolicy createPolicy(PutGroupPolicyRequest request) {
        return createPolicy(request, null);
    }

    @Override
    public GroupPolicy createPolicy(PutGroupPolicyRequest request,
            ResultCapture<Void> extractor) {

        ActionResult result = resource.performAction("CreatePolicy", request,
                extractor);

        if (result == null) return null;
        return new GroupPolicyImpl(result.getResource());
    }

    @Override
    public GroupPolicy createPolicy(String policyName, String policyDocument) {
        return createPolicy(policyName, policyDocument,
                (ResultCapture<Void>)null);
    }

    @Override
    public GroupPolicy createPolicy(String policyName, String policyDocument,
            ResultCapture<Void> extractor) {

        PutGroupPolicyRequest request = new PutGroupPolicyRequest()
            .withPolicyName(policyName)
            .withPolicyDocument(policyDocument);
        return createPolicy(request, extractor);
    }

    private static class Codec implements ResourceCodec<Group> {
        @Override
        public Group transform(ResourceImpl resource) {
            return new GroupImpl(resource);
        }
    }
}
