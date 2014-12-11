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
package com.amazonaws.resources.glacier;

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.glacier.model.DeleteVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.GetVaultNotificationsResult;
import com.amazonaws.services.glacier.model.SetVaultNotificationsRequest;
import com.amazonaws.services.glacier.model.VaultNotificationConfig;

/**
 * The <code>Notification</code> resource.
 * Each <code>Notification</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>AccountId</li>
 *   <li>VaultName</li>
 * </ul>
 */
public interface Notification {
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
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(GetVaultNotificationsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetVaultNotificationsRequest
     */
    boolean load(GetVaultNotificationsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetVaultNotificationsRequest
     */
    boolean load(GetVaultNotificationsRequest request,
            ResultCapture<GetVaultNotificationsResult> extractor);

    /**
     * Gets the value of the AccountId identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getAccountId();

    /**
     * Gets the value of the VaultName identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getVaultName();

    /**
     * Gets the value of the SNSTopic attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSNSTopic();

    /**
     * Gets the value of the Events attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<String> getEvents();

    /**
     * Retrieves the <code>Vault</code> resource referenced by this resource.
     */
    Vault getVault();

    /**
     * Performs the <code>Set</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetVaultNotificationsRequest
     */
    void set(SetVaultNotificationsRequest request);

    /**
     * Performs the <code>Set</code> action and use a ResultCapture to retrieve
     * the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetVaultNotificationsRequest
     */
    void set(SetVaultNotificationsRequest request, ResultCapture<Void> extractor
            );

    /**
     * The convenient method form for the <code>Set</code> action.
     *
     * @see #set(SetVaultNotificationsRequest)
     */
    void set(VaultNotificationConfig vaultNotificationConfig);

    /**
     * The convenient method form for the <code>Set</code> action.
     *
     * @see #set(SetVaultNotificationsRequest, ResultCapture)
     */
    void set(VaultNotificationConfig vaultNotificationConfig,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVaultNotificationsRequest
     */
    void delete(DeleteVaultNotificationsRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Notification</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AccountId</code></b>
     *         - mapped from the <code>AccountId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>VaultName</code></b>
     *         - mapped from the <code>VaultName</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteVaultNotificationsRequest
     */
    void delete(DeleteVaultNotificationsRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVaultNotificationsRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteVaultNotificationsRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
