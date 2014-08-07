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
import com.amazonaws.resources.identitymanagement.AccountAlias;
import com.amazonaws.resources.identitymanagement.AccountAliasCollection;
import com.amazonaws.resources.identitymanagement.Group;
import com.amazonaws.resources.identitymanagement.GroupCollection;
import com.amazonaws.resources.identitymanagement.IdentityManagement;
import com.amazonaws.resources.identitymanagement.InstanceProfile;
import com.amazonaws.resources.identitymanagement.InstanceProfileCollection;
import com.amazonaws.resources.identitymanagement.Role;
import com.amazonaws.resources.identitymanagement.RoleCollection;
import com.amazonaws.resources.identitymanagement.SamlProvider;
import com.amazonaws.resources.identitymanagement.SamlProviderCollection;
import com.amazonaws.resources.identitymanagement.ServerCertificate;
import com.amazonaws.resources.identitymanagement.ServerCertificateCollection;
import com.amazonaws.resources.identitymanagement.SigningCertificate;
import com.amazonaws.resources.identitymanagement.SigningCertificateCollection;
import com.amazonaws.resources.identitymanagement.User;
import com.amazonaws.resources.identitymanagement.UserCollection;
import com.amazonaws.resources.identitymanagement.VirtualMfaDevice;
import com.amazonaws.resources.identitymanagement.VirtualMfaDeviceCollection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.model.ChangePasswordRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccountAliasRequest
;
import com.amazonaws.services.identitymanagement.model.CreateGroupRequest;
import com.amazonaws.services.identitymanagement.model.CreateGroupResult;
import
com.amazonaws.services.identitymanagement.model.CreateInstanceProfileRequest;
import
com.amazonaws.services.identitymanagement.model.CreateInstanceProfileResult;
import com.amazonaws.services.identitymanagement.model.CreateRoleRequest;
import com.amazonaws.services.identitymanagement.model.CreateRoleResult;
import com.amazonaws.services.identitymanagement.model.CreateSAMLProviderRequest
;
import com.amazonaws.services.identitymanagement.model.CreateSAMLProviderResult;
import com.amazonaws.services.identitymanagement.model.CreateUserRequest;
import com.amazonaws.services.identitymanagement.model.CreateUserResult;
import
com.amazonaws.services.identitymanagement.model.CreateVirtualMFADeviceRequest;
import
com.amazonaws.services.identitymanagement.model.CreateVirtualMFADeviceResult;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryRequest;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryResult;
import com.amazonaws.services.identitymanagement.model.ListAccountAliasesRequest
;
import com.amazonaws.services.identitymanagement.model.ListGroupsRequest;
import
com.amazonaws.services.identitymanagement.model.ListInstanceProfilesRequest;
import com.amazonaws.services.identitymanagement.model.ListRolesRequest;
import com.amazonaws.services.identitymanagement.model.ListSAMLProvidersRequest;
import
com.amazonaws.services.identitymanagement.model.ListServerCertificatesRequest;
import
com.amazonaws.services.identitymanagement.model.ListSigningCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import
com.amazonaws.services.identitymanagement.model.ListVirtualMFADevicesRequest;
import
com.amazonaws.services.identitymanagement.model.UpdateAccountPasswordPolicyRequest
;
import
com.amazonaws.services.identitymanagement.model.UploadServerCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.UploadServerCertificateResult;
import
com.amazonaws.services.identitymanagement.model.UploadSigningCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.UploadSigningCertificateResult;

public class IdentityManagementImpl implements IdentityManagement {

    private final ServiceImpl<AmazonIdentityManagement> service;

    public IdentityManagementImpl(ServiceImpl<AmazonIdentityManagement> service)
            {

        this.service = service;
    }

    @Override
    public AmazonIdentityManagement client() {
        return service.getClient();
    }

    @Override
    public User getUser(String name) {
        ResourceImpl result = service.getSubResource("User", name);
        if (result == null) return null;
        return new UserImpl(result);
    }

    @Override
    public SigningCertificate getSigningCertificate(String id) {
        ResourceImpl result = service.getSubResource("SigningCertificate", id);
        if (result == null) return null;
        return new SigningCertificateImpl(result);
    }

    @Override
    public VirtualMfaDevice getVirtualMfaDevice(String serialNumber) {
        ResourceImpl result = service.getSubResource("VirtualMfaDevice",
                serialNumber);

        if (result == null) return null;
        return new VirtualMfaDeviceImpl(result);
    }

    @Override
    public Role getRole(String name) {
        ResourceImpl result = service.getSubResource("Role", name);
        if (result == null) return null;
        return new RoleImpl(result);
    }

    @Override
    public AccountAlias getAccountAlias(String name) {
        ResourceImpl result = service.getSubResource("AccountAlias", name);
        if (result == null) return null;
        return new AccountAliasImpl(result);
    }

    @Override
    public SamlProvider getSamlProvider(String arn) {
        ResourceImpl result = service.getSubResource("SamlProvider", arn);
        if (result == null) return null;
        return new SamlProviderImpl(result);
    }

    @Override
    public InstanceProfile getInstanceProfile(String name) {
        ResourceImpl result = service.getSubResource("InstanceProfile", name);
        if (result == null) return null;
        return new InstanceProfileImpl(result);
    }

    @Override
    public ServerCertificate getServerCertificate(String name) {
        ResourceImpl result = service.getSubResource("ServerCertificate", name);
        if (result == null) return null;
        return new ServerCertificateImpl(result);
    }

    @Override
    public Group getGroup(String name) {
        ResourceImpl result = service.getSubResource("Group", name);
        if (result == null) return null;
        return new GroupImpl(result);
    }

    @Override
    public GroupCollection getGroups() {
        return getGroups(null);
    }

    @Override
    public GroupCollection getGroups(ListGroupsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Groups",
                request);

        if (result == null) return null;
        return new GroupCollectionImpl(result);
    }

    @Override
    public UserCollection getUsers() {
        return getUsers(null);
    }

    @Override
    public UserCollection getUsers(ListUsersRequest request) {
        ResourceCollectionImpl result = service.getCollection("Users", request);
        if (result == null) return null;
        return new UserCollectionImpl(result);
    }

    @Override
    public SamlProviderCollection getSamlProviders() {
        return getSamlProviders(null);
    }

    @Override
    public SamlProviderCollection getSamlProviders(ListSAMLProvidersRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("SamlProviders",
                request);

        if (result == null) return null;
        return new SamlProviderCollectionImpl(result);
    }

    @Override
    public SigningCertificateCollection getSigningCertificates() {
        return getSigningCertificates(null);
    }

    @Override
    public SigningCertificateCollection getSigningCertificates(
            ListSigningCertificatesRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("SigningCertificates", request);

        if (result == null) return null;
        return new SigningCertificateCollectionImpl(result);
    }

    @Override
    public ServerCertificateCollection getServerCertificates() {
        return getServerCertificates(null);
    }

    @Override
    public ServerCertificateCollection getServerCertificates(
            ListServerCertificatesRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("ServerCertificates", request);

        if (result == null) return null;
        return new ServerCertificateCollectionImpl(result);
    }

    @Override
    public InstanceProfileCollection getInstanceProfiles() {
        return getInstanceProfiles(null);
    }

    @Override
    public InstanceProfileCollection getInstanceProfiles(
            ListInstanceProfilesRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("InstanceProfiles", request);

        if (result == null) return null;
        return new InstanceProfileCollectionImpl(result);
    }

    @Override
    public RoleCollection getRoles() {
        return getRoles(null);
    }

    @Override
    public RoleCollection getRoles(ListRolesRequest request) {
        ResourceCollectionImpl result = service.getCollection("Roles", request);
        if (result == null) return null;
        return new RoleCollectionImpl(result);
    }

    @Override
    public AccountAliasCollection getAccountAliases() {
        return getAccountAliases(null);
    }

    @Override
    public AccountAliasCollection getAccountAliases(ListAccountAliasesRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("AccountAliases",
                request);

        if (result == null) return null;
        return new AccountAliasCollectionImpl(result);
    }

    @Override
    public VirtualMfaDeviceCollection getVirtualMfaDevices() {
        return getVirtualMfaDevices(null);
    }

    @Override
    public VirtualMfaDeviceCollection getVirtualMfaDevices(
            ListVirtualMFADevicesRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("VirtualMfaDevices", request);

        if (result == null) return null;
        return new VirtualMfaDeviceCollectionImpl(result);
    }

    @Override
    public Role createRole(CreateRoleRequest request) {
        return createRole(request, null);
    }

    @Override
    public Role createRole(CreateRoleRequest request,
            ResultCapture<CreateRoleResult> extractor) {

        ActionResult result = service.performAction("CreateRole", request,
                extractor);

        if (result == null) return null;
        return new RoleImpl(result.getResource());
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        changePassword(request, null);
    }

    @Override
    public void changePassword(ChangePasswordRequest request,
            ResultCapture<Void> extractor) {

        service.performAction("ChangePassword", request, extractor);
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return createUser(request, null);
    }

    @Override
    public User createUser(CreateUserRequest request,
            ResultCapture<CreateUserResult> extractor) {

        ActionResult result = service.performAction("CreateUser", request,
                extractor);

        if (result == null) return null;
        return new UserImpl(result.getResource());
    }

    @Override
    public VirtualMfaDevice createVirtualMfaDevice(CreateVirtualMFADeviceRequest
            request) {

        return createVirtualMfaDevice(request, null);
    }

    @Override
    public VirtualMfaDevice createVirtualMfaDevice(CreateVirtualMFADeviceRequest
            request, ResultCapture<CreateVirtualMFADeviceResult> extractor) {

        ActionResult result = service.performAction("CreateVirtualMfaDevice",
                request, extractor);

        if (result == null) return null;
        return new VirtualMfaDeviceImpl(result.getResource());
    }

    @Override
    public Group createGroup(CreateGroupRequest request) {
        return createGroup(request, null);
    }

    @Override
    public Group createGroup(CreateGroupRequest request,
            ResultCapture<CreateGroupResult> extractor) {

        ActionResult result = service.performAction("CreateGroup", request,
                extractor);

        if (result == null) return null;
        return new GroupImpl(result.getResource());
    }

    @Override
    public Map<String, Integer> accountSummary(GetAccountSummaryRequest request)
            {

        return accountSummary(request, null);
    }

    @Override
    public Map<String, Integer> accountSummary(GetAccountSummaryRequest request,
            ResultCapture<GetAccountSummaryResult> extractor) {

        ActionResult result = service.performAction("AccountSummary", request,
                extractor);

        if (result == null) return null;
        return (Map<String, Integer>) result.getData();
    }

    @Override
    public InstanceProfile createInstanceProfile(CreateInstanceProfileRequest
            request) {

        return createInstanceProfile(request, null);
    }

    @Override
    public InstanceProfile createInstanceProfile(CreateInstanceProfileRequest
            request, ResultCapture<CreateInstanceProfileResult> extractor) {

        ActionResult result = service.performAction("CreateInstanceProfile",
                request, extractor);

        if (result == null) return null;
        return new InstanceProfileImpl(result.getResource());
    }

    @Override
    public SamlProvider createSamlProvider(CreateSAMLProviderRequest request) {
        return createSamlProvider(request, null);
    }

    @Override
    public SamlProvider createSamlProvider(CreateSAMLProviderRequest request,
            ResultCapture<CreateSAMLProviderResult> extractor) {

        ActionResult result = service.performAction("CreateSamlProvider",
                request, extractor);

        if (result == null) return null;
        return new SamlProviderImpl(result.getResource());
    }

    @Override
    public void createAccountPasswordPolicy(UpdateAccountPasswordPolicyRequest
            request) {

        createAccountPasswordPolicy(request, null);
    }

    @Override
    public void createAccountPasswordPolicy(UpdateAccountPasswordPolicyRequest
            request, ResultCapture<Void> extractor) {

        service.performAction("CreateAccountPasswordPolicy", request,
                extractor);
    }

    @Override
    public void createAccountAlias(CreateAccountAliasRequest request) {
        createAccountAlias(request, null);
    }

    @Override
    public void createAccountAlias(CreateAccountAliasRequest request,
            ResultCapture<Void> extractor) {

        service.performAction("CreateAccountAlias", request, extractor);
    }

    @Override
    public ServerCertificate createServerCertificate(
            UploadServerCertificateRequest request) {

        return createServerCertificate(request, null);
    }

    @Override
    public ServerCertificate createServerCertificate(
            UploadServerCertificateRequest request,
            ResultCapture<UploadServerCertificateResult> extractor) {

        ActionResult result = service.performAction("CreateServerCertificate",
                request, extractor);

        if (result == null) return null;
        return new ServerCertificateImpl(result.getResource());
    }

    @Override
    public SigningCertificate createSigningCertificate(
            UploadSigningCertificateRequest request) {

        return createSigningCertificate(request, null);
    }

    @Override
    public SigningCertificate createSigningCertificate(
            UploadSigningCertificateRequest request,
            ResultCapture<UploadSigningCertificateResult> extractor) {

        ActionResult result = service.performAction("CreateSigningCertificate",
                request, extractor);

        if (result == null) return null;
        return new SigningCertificateImpl(result.getResource());
    }
}
