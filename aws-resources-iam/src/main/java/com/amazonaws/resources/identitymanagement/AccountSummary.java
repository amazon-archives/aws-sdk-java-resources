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
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryRequest;
import com.amazonaws.services.identitymanagement.model.GetAccountSummaryResult;

/**
 * The <code>AccountSummary</code> resource.
 * Each <code>AccountSummary</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 * </ul>
 */
public interface AccountSummary {
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
     * @see #load(GetAccountSummaryRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetAccountSummaryRequest
     */
    boolean load(GetAccountSummaryRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetAccountSummaryRequest
     */
    boolean load(GetAccountSummaryRequest request,
            ResultCapture<GetAccountSummaryResult> extractor);

    /**
     * Gets the value of the SummaryMap attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Map<String, Integer> getSummaryMap();
}
