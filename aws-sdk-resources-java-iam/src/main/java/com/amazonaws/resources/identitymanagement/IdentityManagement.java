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

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;
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

/**
 * The IdentityManagement service.
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.identitymanagement.internal.IdentityManagementImpl"
        )

public interface IdentityManagement extends Service<AmazonIdentityManagement> {
    /**
     * Gets a subresource.
     */
    User getUser(String name);

    /**
     * Gets a subresource.
     */
    SigningCertificate getSigningCertificate(String id);

    /**
     * Gets a subresource.
     */
    VirtualMfaDevice getVirtualMfaDevice(String serialNumber);

    /**
     * Gets a subresource.
     */
    Role getRole(String name);

    /**
     * Gets a subresource.
     */
    AccountAlias getAccountAlias(String name);

    /**
     * Gets a subresource.
     */
    SamlProvider getSamlProvider(String arn);

    /**
     * Gets a subresource.
     */
    InstanceProfile getInstanceProfile(String name);

    /**
     * Gets a subresource.
     */
    ServerCertificate getServerCertificate(String name);

    /**
     * Gets a subresource.
     */
    Group getGroup(String name);

    /**
     * Retrieves the Groups collection referenced by this resource.
     */
    GroupCollection getGroups();

    /**
     * Retrieves the Groups collection referenced by this resource.
     */
    GroupCollection getGroups(ListGroupsRequest request);

    /**
     * Retrieves the Users collection referenced by this resource.
     */
    UserCollection getUsers();

    /**
     * Retrieves the Users collection referenced by this resource.
     */
    UserCollection getUsers(ListUsersRequest request);

    /**
     * Retrieves the SamlProviders collection referenced by this resource.
     */
    SamlProviderCollection getSamlProviders();

    /**
     * Retrieves the SamlProviders collection referenced by this resource.
     */
    SamlProviderCollection getSamlProviders(ListSAMLProvidersRequest request);

    /**
     * Retrieves the SigningCertificates collection referenced by this resource.
     */
    SigningCertificateCollection getSigningCertificates();

    /**
     * Retrieves the SigningCertificates collection referenced by this resource.
     */
    SigningCertificateCollection getSigningCertificates(
            ListSigningCertificatesRequest request);

    /**
     * Retrieves the ServerCertificates collection referenced by this resource.
     */
    ServerCertificateCollection getServerCertificates();

    /**
     * Retrieves the ServerCertificates collection referenced by this resource.
     */
    ServerCertificateCollection getServerCertificates(
            ListServerCertificatesRequest request);

    /**
     * Retrieves the InstanceProfiles collection referenced by this resource.
     */
    InstanceProfileCollection getInstanceProfiles();

    /**
     * Retrieves the InstanceProfiles collection referenced by this resource.
     */
    InstanceProfileCollection getInstanceProfiles(ListInstanceProfilesRequest
            request);

    /**
     * Retrieves the Roles collection referenced by this resource.
     */
    RoleCollection getRoles();

    /**
     * Retrieves the Roles collection referenced by this resource.
     */
    RoleCollection getRoles(ListRolesRequest request);

    /**
     * Retrieves the AccountAliases collection referenced by this resource.
     */
    AccountAliasCollection getAccountAliases();

    /**
     * Retrieves the AccountAliases collection referenced by this resource.
     */
    AccountAliasCollection getAccountAliases(ListAccountAliasesRequest request);

    /**
     * Retrieves the VirtualMfaDevices collection referenced by this resource.
     */
    VirtualMfaDeviceCollection getVirtualMfaDevices();

    /**
     * Retrieves the VirtualMfaDevices collection referenced by this resource.
     */
    VirtualMfaDeviceCollection getVirtualMfaDevices(ListVirtualMFADevicesRequest
            request);

    /**
     * Performs an action.
     */
    Role createRole(CreateRoleRequest request);

    /**
     * Performs an action.
     */
    Role createRole(CreateRoleRequest request, ResultCapture<CreateRoleResult>
            extractor);

    /**
     * Performs an action.
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * Performs an action.
     */
    void changePassword(ChangePasswordRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    User createUser(CreateUserRequest request);

    /**
     * Performs an action.
     */
    User createUser(CreateUserRequest request, ResultCapture<CreateUserResult>
            extractor);

    /**
     * Performs an action.
     */
    VirtualMfaDevice createVirtualMfaDevice(CreateVirtualMFADeviceRequest
            request);

    /**
     * Performs an action.
     */
    VirtualMfaDevice createVirtualMfaDevice(CreateVirtualMFADeviceRequest
            request, ResultCapture<CreateVirtualMFADeviceResult> extractor);

    /**
     * Performs an action.
     */
    Group createGroup(CreateGroupRequest request);

    /**
     * Performs an action.
     */
    Group createGroup(CreateGroupRequest request,
            ResultCapture<CreateGroupResult> extractor);

    /**
     * Performs an action.
     */
    Map<String, Integer> accountSummary(GetAccountSummaryRequest request);

    /**
     * Performs an action.
     */
    Map<String, Integer> accountSummary(GetAccountSummaryRequest request,
            ResultCapture<GetAccountSummaryResult> extractor);

    /**
     * Performs an action.
     */
    InstanceProfile createInstanceProfile(CreateInstanceProfileRequest request);

    /**
     * Performs an action.
     */
    InstanceProfile createInstanceProfile(CreateInstanceProfileRequest request,
            ResultCapture<CreateInstanceProfileResult> extractor);

    /**
     * Performs an action.
     */
    SamlProvider createSamlProvider(CreateSAMLProviderRequest request);

    /**
     * Performs an action.
     */
    SamlProvider createSamlProvider(CreateSAMLProviderRequest request,
            ResultCapture<CreateSAMLProviderResult> extractor);

    /**
     * Performs an action.
     */
    void createAccountPasswordPolicy(UpdateAccountPasswordPolicyRequest request)
            ;

    /**
     * Performs an action.
     */
    void createAccountPasswordPolicy(UpdateAccountPasswordPolicyRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void createAccountAlias(CreateAccountAliasRequest request);

    /**
     * Performs an action.
     */
    void createAccountAlias(CreateAccountAliasRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    ServerCertificate createServerCertificate(UploadServerCertificateRequest
            request);

    /**
     * Performs an action.
     */
    ServerCertificate createServerCertificate(UploadServerCertificateRequest
            request, ResultCapture<UploadServerCertificateResult> extractor);

    /**
     * Performs an action.
     */
    SigningCertificate createSigningCertificate(UploadSigningCertificateRequest
            request);

    /**
     * Performs an action.
     */
    SigningCertificate createSigningCertificate(UploadSigningCertificateRequest
            request, ResultCapture<UploadSigningCertificateResult> extractor);
}
