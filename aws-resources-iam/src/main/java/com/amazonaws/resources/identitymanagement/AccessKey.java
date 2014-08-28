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

import java.util.Date;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.identitymanagement.model.DeleteAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.UpdateAccessKeyRequest;

/**
 * The <code>AccessKey</code> resource.
 * Each <code>AccessKey</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 *   <li>UserName</li>
 * </ul>
 */
public interface AccessKey {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the UserName identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getUserName();

    /**
     * Gets the value of the SecretAccessKey attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSecretAccessKey();

    /**
     * Gets the value of the Status attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getStatus();

    /**
     * Gets the value of the CreateDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateDate();

    /**
     * Retrieves the <code>User</code> resource referenced by this resource.
     */
    User getUser();

    /**
     * Performs the <code>Deactivate</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Status</code></b>
     *         - constant value <code>Inactive</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccessKeyRequest
     */
    void deactivate(UpdateAccessKeyRequest request);

    /**
     * Performs the <code>Deactivate</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Status</code></b>
     *         - constant value <code>Inactive</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccessKeyRequest
     */
    void deactivate(UpdateAccessKeyRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Deactivate</code> action.
     *
     * @see #deactivate(UpdateAccessKeyRequest)
     */
    void deactivate();

    /**
     * The convenient method form for the <code>Deactivate</code> action.
     *
     * @see #deactivate(UpdateAccessKeyRequest, ResultCapture)
     */
    void deactivate(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Activate</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Status</code></b>
     *         - constant value <code>Active</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccessKeyRequest
     */
    void activate(UpdateAccessKeyRequest request);

    /**
     * Performs the <code>Activate</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Status</code></b>
     *         - constant value <code>Active</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UpdateAccessKeyRequest
     */
    void activate(UpdateAccessKeyRequest request, ResultCapture<Void> extractor)
            ;

    /**
     * The convenient method form for the <code>Activate</code> action.
     *
     * @see #activate(UpdateAccessKeyRequest)
     */
    void activate();

    /**
     * The convenient method form for the <code>Activate</code> action.
     *
     * @see #activate(UpdateAccessKeyRequest, ResultCapture)
     */
    void activate(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteAccessKeyRequest
     */
    void delete(DeleteAccessKeyRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>AccessKey</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AccessKeyId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteAccessKeyRequest
     */
    void delete(DeleteAccessKeyRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteAccessKeyRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteAccessKeyRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
