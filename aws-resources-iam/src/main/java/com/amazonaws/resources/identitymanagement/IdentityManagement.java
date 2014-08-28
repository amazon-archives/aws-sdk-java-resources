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
 * The <code>IdentityManagement</code> service.
 * This is the entry point to interact with the following service resources:<ul>
 *   <li>User</li>
 *   <li>AccountPasswordPolicy</li>
 *   <li>AccessKey</li>
 *   <li>SamlProvider</li>
 *   <li>LoginProfile</li>
 *   <li>InstanceProfile</li>
 *   <li>Group</li>
 *   <li>UserPolicy</li>
 *   <li>MfaDevice</li>
 *   <li>SigningCertificate</li>
 *   <li>VirtualMfaDevice</li>
 *   <li>RolePolicy</li>
 *   <li>Role</li>
 *   <li>AccountAlias</li>
 *   <li>GroupPolicy</li>
 *   <li>ServerCertificate</li>
 * </ul>
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.identitymanagement.internal.IdentityManagementImpl"
        )

public interface IdentityManagement extends Service<AmazonIdentityManagement> {
    /**
     * Gets an instance of {@code User} resource by its identifier(s).
     */
    User getUser(String name);

    /**
     * Gets an instance of {@code SigningCertificate} resource by its
     * identifier(s).
     */
    SigningCertificate getSigningCertificate(String id);

    /**
     * Gets an instance of {@code VirtualMfaDevice} resource by its
     * identifier(s).
     */
    VirtualMfaDevice getVirtualMfaDevice(String serialNumber);

    /**
     * Gets an instance of {@code Role} resource by its identifier(s).
     */
    Role getRole(String name);

    /**
     * Gets an instance of {@code AccountAlias} resource by its identifier(s).
     */
    AccountAlias getAccountAlias(String name);

    /**
     * Gets an instance of {@code SamlProvider} resource by its identifier(s).
     */
    SamlProvider getSamlProvider(String arn);

    /**
     * Gets an instance of {@code InstanceProfile} resource by its
     * identifier(s).
     */
    InstanceProfile getInstanceProfile(String name);

    /**
     * Gets an instance of {@code ServerCertificate} resource by its
     * identifier(s).
     */
    ServerCertificate getServerCertificate(String name);

    /**
     * Gets an instance of {@code Group} resource by its identifier(s).
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
     * Performs the <code>CreateRole</code> action.
     *
     * <p>
     *
     * @return The <code>Role</code> resource object associated with the result
     *         of this action.
     * @see CreateRoleRequest
     */
    com.amazonaws.resources.identitymanagement.Role createRole(CreateRoleRequest
            request);

    /**
     * Performs the <code>CreateRole</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Role</code> resource object associated with the result
     *         of this action.
     * @see CreateRoleRequest
     */
    com.amazonaws.resources.identitymanagement.Role createRole(CreateRoleRequest
            request, ResultCapture<CreateRoleResult> extractor);

    /**
     * Performs the <code>ChangePassword</code> action.
     *
     * <p>
     *
     * @see ChangePasswordRequest
     */
    void changePassword(ChangePasswordRequest request);

    /**
     * Performs the <code>ChangePassword</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @see ChangePasswordRequest
     */
    void changePassword(ChangePasswordRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>CreateUser</code> action.
     *
     * <p>
     *
     * @return The <code>User</code> resource object associated with the result
     *         of this action.
     * @see CreateUserRequest
     */
    com.amazonaws.resources.identitymanagement.User createUser(CreateUserRequest
            request);

    /**
     * Performs the <code>CreateUser</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>User</code> resource object associated with the result
     *         of this action.
     * @see CreateUserRequest
     */
    com.amazonaws.resources.identitymanagement.User createUser(CreateUserRequest
            request, ResultCapture<CreateUserResult> extractor);

    /**
     * Performs the <code>CreateVirtualMfaDevice</code> action.
     *
     * <p>
     *
     * @return The <code>VirtualMfaDevice</code> resource object associated with
     *         the result of this action.
     * @see CreateVirtualMFADeviceRequest
     */
    com.amazonaws.resources.identitymanagement.VirtualMfaDevice
            createVirtualMfaDevice(CreateVirtualMFADeviceRequest request);

    /**
     * Performs the <code>CreateVirtualMfaDevice</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>VirtualMfaDevice</code> resource object associated with
     *         the result of this action.
     * @see CreateVirtualMFADeviceRequest
     */
    com.amazonaws.resources.identitymanagement.VirtualMfaDevice
            createVirtualMfaDevice(CreateVirtualMFADeviceRequest request,
            ResultCapture<CreateVirtualMFADeviceResult> extractor);

    /**
     * Performs the <code>CreateGroup</code> action.
     *
     * <p>
     *
     * @return The <code>Group</code> resource object associated with the result
     *         of this action.
     * @see CreateGroupRequest
     */
    com.amazonaws.resources.identitymanagement.Group createGroup(
            CreateGroupRequest request);

    /**
     * Performs the <code>CreateGroup</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Group</code> resource object associated with the result
     *         of this action.
     * @see CreateGroupRequest
     */
    com.amazonaws.resources.identitymanagement.Group createGroup(
            CreateGroupRequest request, ResultCapture<CreateGroupResult>
            extractor);

    /**
     * Performs the <code>AccountSummary</code> action.
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetAccountSummaryRequest
     */
    Map<String, Integer> accountSummary(GetAccountSummaryRequest request);

    /**
     * Performs the <code>AccountSummary</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetAccountSummaryRequest
     */
    Map<String, Integer> accountSummary(GetAccountSummaryRequest request,
            ResultCapture<GetAccountSummaryResult> extractor);

    /**
     * Performs the <code>CreateInstanceProfile</code> action.
     *
     * <p>
     *
     * @return The <code>InstanceProfile</code> resource object associated with
     *         the result of this action.
     * @see CreateInstanceProfileRequest
     */
    com.amazonaws.resources.identitymanagement.InstanceProfile
            createInstanceProfile(CreateInstanceProfileRequest request);

    /**
     * Performs the <code>CreateInstanceProfile</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>InstanceProfile</code> resource object associated with
     *         the result of this action.
     * @see CreateInstanceProfileRequest
     */
    com.amazonaws.resources.identitymanagement.InstanceProfile
            createInstanceProfile(CreateInstanceProfileRequest request,
            ResultCapture<CreateInstanceProfileResult> extractor);

    /**
     * Performs the <code>CreateSamlProvider</code> action.
     *
     * <p>
     *
     * @return The <code>SamlProvider</code> resource object associated with the
     *         result of this action.
     * @see CreateSAMLProviderRequest
     */
    com.amazonaws.resources.identitymanagement.SamlProvider createSamlProvider(
            CreateSAMLProviderRequest request);

    /**
     * Performs the <code>CreateSamlProvider</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>SamlProvider</code> resource object associated with the
     *         result of this action.
     * @see CreateSAMLProviderRequest
     */
    com.amazonaws.resources.identitymanagement.SamlProvider createSamlProvider(
            CreateSAMLProviderRequest request,
            ResultCapture<CreateSAMLProviderResult> extractor);

    /**
     * Performs the <code>CreateAccountPasswordPolicy</code> action.
     *
     * <p>
     *
     * @return The <code>AccountPasswordPolicy</code> resource object associated
     *         with the result of this action.
     * @see UpdateAccountPasswordPolicyRequest
     */
    AccountPasswordPolicy createAccountPasswordPolicy(
            UpdateAccountPasswordPolicyRequest request);

    /**
     * Performs the <code>CreateAccountPasswordPolicy</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>AccountPasswordPolicy</code> resource object associated
     *         with the result of this action.
     * @see UpdateAccountPasswordPolicyRequest
     */
    AccountPasswordPolicy createAccountPasswordPolicy(
            UpdateAccountPasswordPolicyRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>CreateAccountAlias</code> action.
     *
     * <p>
     *
     * @return The <code>AccountAlias</code> resource object associated with the
     *         result of this action.
     * @see CreateAccountAliasRequest
     */
    com.amazonaws.resources.identitymanagement.AccountAlias createAccountAlias(
            CreateAccountAliasRequest request);

    /**
     * Performs the <code>CreateAccountAlias</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>AccountAlias</code> resource object associated with the
     *         result of this action.
     * @see CreateAccountAliasRequest
     */
    com.amazonaws.resources.identitymanagement.AccountAlias createAccountAlias(
            CreateAccountAliasRequest request, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateServerCertificate</code> action.
     *
     * <p>
     *
     * @return The <code>ServerCertificate</code> resource object associated
     *         with the result of this action.
     * @see UploadServerCertificateRequest
     */
    com.amazonaws.resources.identitymanagement.ServerCertificate
            createServerCertificate(UploadServerCertificateRequest request);

    /**
     * Performs the <code>CreateServerCertificate</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>ServerCertificate</code> resource object associated
     *         with the result of this action.
     * @see UploadServerCertificateRequest
     */
    com.amazonaws.resources.identitymanagement.ServerCertificate
            createServerCertificate(UploadServerCertificateRequest request,
            ResultCapture<UploadServerCertificateResult> extractor);

    /**
     * Performs the <code>CreateSigningCertificate</code> action.
     *
     * <p>
     *
     * @return The <code>SigningCertificate</code> resource object associated
     *         with the result of this action.
     * @see UploadSigningCertificateRequest
     */
    com.amazonaws.resources.identitymanagement.SigningCertificate
            createSigningCertificate(UploadSigningCertificateRequest request);

    /**
     * Performs the <code>CreateSigningCertificate</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>SigningCertificate</code> resource object associated
     *         with the result of this action.
     * @see UploadSigningCertificateRequest
     */
    com.amazonaws.resources.identitymanagement.SigningCertificate
            createSigningCertificate(UploadSigningCertificateRequest request,
            ResultCapture<UploadSigningCertificateResult> extractor);
}
