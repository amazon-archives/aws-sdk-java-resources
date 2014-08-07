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
 * The SamlProvider resource.
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
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetSAMLProviderRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(GetSAMLProviderRequest request,
            ResultCapture<GetSAMLProviderResult> extractor);

    /**
     * Gets the value of the Arn identifier.
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
     * Performs an action.
     */
    UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request);

    /**
     * Performs an action.
     */
    UpdateSAMLProviderResult update(UpdateSAMLProviderRequest request,
            ResultCapture<UpdateSAMLProviderResult> extractor);

    /**
     * Performs an action.
     */
    void delete(DeleteSAMLProviderRequest request);

    /**
     * Performs an action.
     */
    void delete(DeleteSAMLProviderRequest request, ResultCapture<Void> extractor
            );
}
