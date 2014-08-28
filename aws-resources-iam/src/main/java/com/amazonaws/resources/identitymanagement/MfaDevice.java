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
import
com.amazonaws.services.identitymanagement.model.DeactivateMFADeviceRequest;
import com.amazonaws.services.identitymanagement.model.EnableMFADeviceRequest;
import com.amazonaws.services.identitymanagement.model.ResyncMFADeviceRequest;

/**
 * The <code>MfaDevice</code> resource.
 * Each <code>MfaDevice</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>SerialNumber</li>
 *   <li>UserName</li>
 * </ul>
 */
public interface MfaDevice {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the SerialNumber identifier. This method always
     * directly returns the identifier and never involves a service call.
     */
    String getSerialNumber();

    /**
     * Gets the value of the UserName identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getUserName();

    /**
     * Gets the value of the EnableDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getEnableDate();

    /**
     * Retrieves the <code>User</code> resource referenced by this resource.
     */
    User getUser();

    /**
     * Performs the <code>Enable</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see EnableMFADeviceRequest
     */
    void enable(EnableMFADeviceRequest request);

    /**
     * Performs the <code>Enable</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see EnableMFADeviceRequest
     */
    void enable(EnableMFADeviceRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Enable</code> action.
     *
     * @see #enable(EnableMFADeviceRequest)
     */
    void enable(String authenticationCode1, String authenticationCode2);

    /**
     * The convenient method form for the <code>Enable</code> action.
     *
     * @see #enable(EnableMFADeviceRequest, ResultCapture)
     */
    void enable(String authenticationCode1, String authenticationCode2,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>Deactivate</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeactivateMFADeviceRequest
     */
    void deactivate(DeactivateMFADeviceRequest request);

    /**
     * Performs the <code>Deactivate</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeactivateMFADeviceRequest
     */
    void deactivate(DeactivateMFADeviceRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Deactivate</code> action.
     *
     * @see #deactivate(DeactivateMFADeviceRequest)
     */
    void deactivate();

    /**
     * The convenient method form for the <code>Deactivate</code> action.
     *
     * @see #deactivate(DeactivateMFADeviceRequest, ResultCapture)
     */
    void deactivate(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Resync</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResyncMFADeviceRequest
     */
    void resync(ResyncMFADeviceRequest request);

    /**
     * Performs the <code>Resync</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>MfaDevice</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>UserName</code></b>
     *         - mapped from the <code>UserName</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>SerialNumber</code></b>
     *         - mapped from the <code>SerialNumber</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResyncMFADeviceRequest
     */
    void resync(ResyncMFADeviceRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Resync</code> action.
     *
     * @see #resync(ResyncMFADeviceRequest)
     */
    void resync(String authenticationCode1, String authenticationCode2);

    /**
     * The convenient method form for the <code>Resync</code> action.
     *
     * @see #resync(ResyncMFADeviceRequest, ResultCapture)
     */
    void resync(String authenticationCode1, String authenticationCode2,
            ResultCapture<Void> extractor);
}
