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
package com.amazonaws.resources.sns;

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.UnsubscribeRequest;

/**
 * The <code>Subscription</code> resource.
 * Each <code>Subscription</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Arn</li>
 *   <li>TopicArn</li>
 * </ul>
 */
public interface Subscription {
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
     * @see #load(GetSubscriptionAttributesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetSubscriptionAttributesRequest
     */
    boolean load(GetSubscriptionAttributesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetSubscriptionAttributesRequest
     */
    boolean load(GetSubscriptionAttributesRequest request,
            ResultCapture<GetSubscriptionAttributesResult> extractor);

    /**
     * Gets the value of the Arn identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getArn();

    /**
     * Gets the value of the TopicArn identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getTopicArn();

    /**
     * Gets the value of the Attributes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Map<String, String> getAttributes();

    /**
     * Retrieves the <code>Topic</code> resource referenced by this resource.
     */
    Topic getTopic();

    /**
     * Performs the <code>SetAttributes</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetSubscriptionAttributesRequest
     */
    void setAttributes(SetSubscriptionAttributesRequest request);

    /**
     * Performs the <code>SetAttributes</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetSubscriptionAttributesRequest
     */
    void setAttributes(SetSubscriptionAttributesRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetSubscriptionAttributesRequest)
     */
    void setAttributes(String attributeName, String attributeValue);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetSubscriptionAttributesRequest, ResultCapture)
     */
    void setAttributes(String attributeName, String attributeValue,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UnsubscribeRequest
     */
    void delete(UnsubscribeRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subscription</code> resource, and any conflicting parameter value
     * set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubscriptionArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UnsubscribeRequest
     */
    void delete(UnsubscribeRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(UnsubscribeRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(UnsubscribeRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
