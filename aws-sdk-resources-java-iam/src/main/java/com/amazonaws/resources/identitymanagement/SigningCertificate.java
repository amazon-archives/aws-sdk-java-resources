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
com.amazonaws.services.identitymanagement.model.DeleteSigningCertificateRequest;
import
com.amazonaws.services.identitymanagement.model.UpdateSigningCertificateRequest;

/**
 * The SigningCertificate resource.
 */
public interface SigningCertificate {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the Id identifier.
     */
    String getId();

    /**
     * Gets the value of the UploadDate attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getUploadDate();

    /**
     * Gets the value of the Status attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getStatus();

    /**
     * Gets the value of the CertificateBody attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCertificateBody();

    /**
     * Gets the value of the UserName attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getUserName();

    /**
     * Performs an action.
     */
    void deactivate(UpdateSigningCertificateRequest request);

    /**
     * Performs an action.
     */
    void deactivate(UpdateSigningCertificateRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void activate(UpdateSigningCertificateRequest request);

    /**
     * Performs an action.
     */
    void activate(UpdateSigningCertificateRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteSigningCertificateRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteSigningCertificateRequest request, ResultCapture<Void>
            extractor);
}
