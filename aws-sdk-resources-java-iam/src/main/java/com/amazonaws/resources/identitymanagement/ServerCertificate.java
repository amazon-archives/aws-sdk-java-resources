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

import com.amazonaws.resources.ResultCapture;
import
com.amazonaws.services.identitymanagement.model.DeleteServerCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.GetServerCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.GetServerCertificateResult;
import com.amazonaws.services.identitymanagement.model.ServerCertificateMetadata
;
import
com.amazonaws.services.identitymanagement.model.UpdateServerCertificateRequest;

/**
 * The ServerCertificate resource.
 */
public interface ServerCertificate {
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
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(GetServerCertificateRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetServerCertificateRequest
     */
    boolean load(GetServerCertificateRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetServerCertificateRequest
     */
    boolean load(GetServerCertificateRequest request,
            ResultCapture<GetServerCertificateResult> extractor);

    /**
     * Gets the value of the Name identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getName();

    /**
     * Gets the value of the ServerCertificateMetadata attribute. If this
     * resource is not yet loaded, a call to {@code load()} is made to retrieve
     * the value of the attribute.
     */
    ServerCertificateMetadata getServerCertificateMetadata();

    /**
     * Gets the value of the CertificateBody attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCertificateBody();

    /**
     * Gets the value of the CertificateChain attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCertificateChain();

    /**
     * Performs the <code>Update</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>ServerCertificate</code> resource object associated
     *         with the result of this action.
     * @see UpdateServerCertificateRequest
     */
    ServerCertificate update(UpdateServerCertificateRequest request);

    /**
     * Performs the <code>Update</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>ServerCertificate</code> resource object associated
     *         with the result of this action.
     * @see UpdateServerCertificateRequest
     */
    ServerCertificate update(UpdateServerCertificateRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateServerCertificateRequest)
     */
    ServerCertificate update();

    /**
     * The convenient method form for the <code>Update</code> action.
     *
     * @see #update(UpdateServerCertificateRequest, ResultCapture)
     */
    ServerCertificate update(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteServerCertificateRequest
     */
    void delete(DeleteServerCertificateRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>ServerCertificate</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ServerCertificateName</code></b>
     *         - mapped from the <code>Name</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteServerCertificateRequest
     */
    void delete(DeleteServerCertificateRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteServerCertificateRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteServerCertificateRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
