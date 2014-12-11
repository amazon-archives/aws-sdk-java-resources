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

import java.util.List;
import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.sns.model.AddPermissionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionRequest;
import com.amazonaws.services.sns.model.ConfirmSubscriptionResult;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesRequest;
import com.amazonaws.services.sns.model.GetTopicAttributesResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.RemovePermissionRequest;
import com.amazonaws.services.sns.model.SetTopicAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;

/**
 * The <code>Topic</code> resource.
 * Each <code>Topic</code> object is uniquely identified by these identifier(s):
 * <ul>
 *   <li>Arn</li>
 * </ul>
 */
public interface Topic {
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
     * @see #load(GetTopicAttributesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetTopicAttributesRequest
     */
    boolean load(GetTopicAttributesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetTopicAttributesRequest
     */
    boolean load(GetTopicAttributesRequest request,
            ResultCapture<GetTopicAttributesResult> extractor);

    /**
     * Gets the value of the Arn identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getArn();

    /**
     * Gets the value of the Attributes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Map<String, String> getAttributes();

    /**
     * Gets a subresource.
     */
    Subscription getSubscription(String arn);

    /**
     * Retrieves the Subscriptions collection referenced by this resource.
     */
    SubscriptionCollection getSubscriptions();

    /**
     * Retrieves the Subscriptions collection referenced by this resource.
     */
    SubscriptionCollection getSubscriptions(ListSubscriptionsByTopicRequest
            request);

    /**
     * Performs the <code>Publish</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see PublishRequest
     */
    PublishResult publish(PublishRequest request);

    /**
     * Performs the <code>Publish</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see PublishRequest
     */
    PublishResult publish(PublishRequest request, ResultCapture<PublishResult>
            extractor);

    /**
     * The convenient method form for the <code>Publish</code> action.
     *
     * @see #publish(PublishRequest)
     */
    PublishResult publish(String message);

    /**
     * The convenient method form for the <code>Publish</code> action.
     *
     * @see #publish(PublishRequest, ResultCapture)
     */
    PublishResult publish(String message, ResultCapture<PublishResult> extractor
            );

    /**
     * The convenient method form for the <code>Publish</code> action.
     *
     * @see #publish(PublishRequest)
     */
    PublishResult publish(String subject, String message);

    /**
     * The convenient method form for the <code>Publish</code> action.
     *
     * @see #publish(PublishRequest, ResultCapture)
     */
    PublishResult publish(String subject, String message,
            ResultCapture<PublishResult> extractor);

    /**
     * Performs the <code>SetAttributes</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetTopicAttributesRequest
     */
    void setAttributes(SetTopicAttributesRequest request);

    /**
     * Performs the <code>SetAttributes</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetTopicAttributesRequest
     */
    void setAttributes(SetTopicAttributesRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetTopicAttributesRequest)
     */
    void setAttributes(String attributeName, String attributeValue);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetTopicAttributesRequest, ResultCapture)
     */
    void setAttributes(String attributeName, String attributeValue,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>AddPermission</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddPermissionRequest
     */
    void addPermission(AddPermissionRequest request);

    /**
     * Performs the <code>AddPermission</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AddPermissionRequest
     */
    void addPermission(AddPermissionRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>AddPermission</code> action.
     *
     * @see #addPermission(AddPermissionRequest)
     */
    void addPermission(String label, List<String> aWSAccountIds, List<String>
            actionNames);

    /**
     * The convenient method form for the <code>AddPermission</code> action.
     *
     * @see #addPermission(AddPermissionRequest, ResultCapture)
     */
    void addPermission(String label, List<String> aWSAccountIds, List<String>
            actionNames, ResultCapture<Void> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteTopicRequest
     */
    void delete(DeleteTopicRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteTopicRequest
     */
    void delete(DeleteTopicRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteTopicRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteTopicRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>ConfirmSubscription</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subscription</code> resource object associated with the
     *         result of this action.
     * @see ConfirmSubscriptionRequest
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(
            ConfirmSubscriptionRequest request);

    /**
     * Performs the <code>ConfirmSubscription</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subscription</code> resource object associated with the
     *         result of this action.
     * @see ConfirmSubscriptionRequest
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(
            ConfirmSubscriptionRequest request,
            ResultCapture<ConfirmSubscriptionResult> extractor);

    /**
     * The convenient method form for the <code>ConfirmSubscription</code>
     * action.
     *
     * @see #confirmSubscription(ConfirmSubscriptionRequest)
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(String token);

    /**
     * The convenient method form for the <code>ConfirmSubscription</code>
     * action.
     *
     * @see #confirmSubscription(ConfirmSubscriptionRequest, ResultCapture)
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(String token,
            ResultCapture<ConfirmSubscriptionResult> extractor);

    /**
     * The convenient method form for the <code>ConfirmSubscription</code>
     * action.
     *
     * @see #confirmSubscription(ConfirmSubscriptionRequest)
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(String
            authenticateOnUnsubscribe, String token);

    /**
     * The convenient method form for the <code>ConfirmSubscription</code>
     * action.
     *
     * @see #confirmSubscription(ConfirmSubscriptionRequest, ResultCapture)
     */
    com.amazonaws.resources.sns.Subscription confirmSubscription(String
            authenticateOnUnsubscribe, String token,
            ResultCapture<ConfirmSubscriptionResult> extractor);

    /**
     * Performs the <code>Subscribe</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subscription</code> resource object associated with the
     *         result of this action.
     * @see SubscribeRequest
     */
    com.amazonaws.resources.sns.Subscription subscribe(SubscribeRequest request)
            ;

    /**
     * Performs the <code>Subscribe</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Subscription</code> resource object associated with the
     *         result of this action.
     * @see SubscribeRequest
     */
    com.amazonaws.resources.sns.Subscription subscribe(SubscribeRequest request,
            ResultCapture<SubscribeResult> extractor);

    /**
     * The convenient method form for the <code>Subscribe</code> action.
     *
     * @see #subscribe(SubscribeRequest)
     */
    com.amazonaws.resources.sns.Subscription subscribe(String endpoint, String
            protocol);

    /**
     * The convenient method form for the <code>Subscribe</code> action.
     *
     * @see #subscribe(SubscribeRequest, ResultCapture)
     */
    com.amazonaws.resources.sns.Subscription subscribe(String endpoint, String
            protocol, ResultCapture<SubscribeResult> extractor);

    /**
     * Performs the <code>RemovePermission</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemovePermissionRequest
     */
    void removePermission(RemovePermissionRequest request);

    /**
     * Performs the <code>RemovePermission</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Topic</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>TopicArn</code></b>
     *         - mapped from the <code>Arn</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RemovePermissionRequest
     */
    void removePermission(RemovePermissionRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>RemovePermission</code> action.
     *
     * @see #removePermission(RemovePermissionRequest)
     */
    void removePermission(String label);

    /**
     * The convenient method form for the <code>RemovePermission</code> action.
     *
     * @see #removePermission(RemovePermissionRequest, ResultCapture)
     */
    void removePermission(String label, ResultCapture<Void> extractor);
}
