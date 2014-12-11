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

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListPlatformApplicationsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListTopicsRequest;

/**
 * The <code>SNS</code> service.
 * This is the entry point to interact with the following service resources:<ul>
 *   <li>PlatformApplication</li>
 *   <li>PlatformEndpoint</li>
 *   <li>Subscription</li>
 *   <li>Topic</li>
 * </ul>
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.sns.internal.SNSImpl")

public interface SNS extends Service<AmazonSNS> {
    /**
     * Gets an instance of {@code PlatformEndpoint} resource by its
     * identifier(s).
     */
    PlatformEndpoint getPlatformEndpoint(String arn);

    /**
     * Gets an instance of {@code PlatformApplication} resource by its
     * identifier(s).
     */
    PlatformApplication getPlatformApplication(String arn);

    /**
     * Gets an instance of {@code Topic} resource by its identifier(s).
     */
    Topic getTopic(String arn);

    /**
     * Retrieves the PlatformApplications collection referenced by this
     * resource.
     */
    PlatformApplicationCollection getPlatformApplications();

    /**
     * Retrieves the PlatformApplications collection referenced by this
     * resource.
     */
    PlatformApplicationCollection getPlatformApplications(
            ListPlatformApplicationsRequest request);

    /**
     * Retrieves the Subscriptions collection referenced by this resource.
     */
    SubscriptionCollection getSubscriptions();

    /**
     * Retrieves the Subscriptions collection referenced by this resource.
     */
    SubscriptionCollection getSubscriptions(ListSubscriptionsRequest request);

    /**
     * Retrieves the Topics collection referenced by this resource.
     */
    TopicCollection getTopics();

    /**
     * Retrieves the Topics collection referenced by this resource.
     */
    TopicCollection getTopics(ListTopicsRequest request);

    /**
     * Performs the <code>CreateTopic</code> action.
     *
     * <p>
     *
     * @return The <code>Topic</code> resource object associated with the result
     *         of this action.
     * @see CreateTopicRequest
     */
    com.amazonaws.resources.sns.Topic createTopic(CreateTopicRequest request);

    /**
     * Performs the <code>CreateTopic</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Topic</code> resource object associated with the result
     *         of this action.
     * @see CreateTopicRequest
     */
    com.amazonaws.resources.sns.Topic createTopic(CreateTopicRequest request,
            ResultCapture<CreateTopicResult> extractor);

    /**
     * The convenient method form for the <code>CreateTopic</code> action.
     *
     * @see #createTopic(CreateTopicRequest)
     */
    com.amazonaws.resources.sns.Topic createTopic(String name);

    /**
     * The convenient method form for the <code>CreateTopic</code> action.
     *
     * @see #createTopic(CreateTopicRequest, ResultCapture)
     */
    com.amazonaws.resources.sns.Topic createTopic(String name,
            ResultCapture<CreateTopicResult> extractor);

    /**
     * Performs the <code>CreatePlatformApplication</code> action.
     *
     * <p>
     *
     * @return The <code>PlatformApplication</code> resource object associated
     *         with the result of this action.
     * @see CreatePlatformApplicationRequest
     */
    com.amazonaws.resources.sns.PlatformApplication createPlatformApplication(
            CreatePlatformApplicationRequest request);

    /**
     * Performs the <code>CreatePlatformApplication</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>PlatformApplication</code> resource object associated
     *         with the result of this action.
     * @see CreatePlatformApplicationRequest
     */
    com.amazonaws.resources.sns.PlatformApplication createPlatformApplication(
            CreatePlatformApplicationRequest request,
            ResultCapture<CreatePlatformApplicationResult> extractor);
}
