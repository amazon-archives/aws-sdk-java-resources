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
import com.amazonaws.services.identitymanagement.model.DeleteSAMLProviderRequest
;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderRequest;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderResult;
import com.amazonaws.services.identitymanagement.model.UpdateSAMLProviderRequest
;
import com.amazonaws.services.identitymanagement.model.UpdateSAMLProviderResult;

/**
 * The <code>SamlProvider</code> resource.
 * Each <code>SamlProvider</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Arn</li>
 * </ul>
 */
public interface SamlProvider {
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
     * @see #load(GetSAMLProviderRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetSAMLProviderRequest
     */
    boolean load(GetSAMLProviderRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetSAMLProviderRequest
     */
    boolean load(GetSAMLProviderRequest request,
            ResultCapture<GetSAMLProviderResult> extractor);

    /**
     * Gets the value of the Arn identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getArn();

    /**
     * Gets the value of the ValidUntil attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getValidUntil();

    /**
     * Gets the value of the SAMLMetadataDocument attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getSAMLMetadataDocument();

    /**
     * Gets the value of the CreateDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateDate();

    /**
     * Performs the <code>Update</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UpdateSAMLProviderRequest
     */
    UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request);

    /**
     * Performs the <code>Update</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UpdateSAMLProviderRequest
     */
    UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request,
            ResultCapture<UpdateSAMLProviderResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSAMLProviderRequest
     */
    void delete(DeleteSAMLProviderRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>SamlProvider</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SAMLProviderArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSAMLProviderRequest
     */
    void delete(DeleteSAMLProviderRequest request, ResultCapture<Void> extractor
            );
}
