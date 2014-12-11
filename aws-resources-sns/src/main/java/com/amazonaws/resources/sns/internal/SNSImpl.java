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
package com.amazonaws.resources.sns.internal;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.V1ModelLoader;
import com.amazonaws.resources.sns.PlatformApplication;
import com.amazonaws.resources.sns.PlatformApplicationCollection;
import com.amazonaws.resources.sns.PlatformEndpoint;
import com.amazonaws.resources.sns.SNS;
import com.amazonaws.resources.sns.SubscriptionCollection;
import com.amazonaws.resources.sns.Topic;
import com.amazonaws.resources.sns.TopicCollection;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreatePlatformApplicationRequest;
import com.amazonaws.services.sns.model.CreatePlatformApplicationResult;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListPlatformApplicationsRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsRequest;
import com.amazonaws.services.sns.model.ListTopicsRequest;

public class SNSImpl implements SNS {

    private final ServiceImpl<AmazonSNS> service;

    /**
     * Construct a service implementation that talks to the specified AWS
     * region. The low-level client will be created via the default no-arg
     * constructor, which means it will have all the default client
     * configurations and it will use the default provider chain to retrieve AWS
     * credentials. If you need more flexible control over the low-level client,
     * use {@link #SNSImpl(AmazonSNS)} instead.
     *
     * @param region The AWS region where the service API calls will be sent to.
     */
    public SNSImpl(Regions region) {
        this(new AmazonSNSClient());
        this.client().setRegion(Region.getRegion(region));
    }

    /**
     * Construct a service implementation using the specified client object.
     *
     * @param client The low-level client which the service implementation will
     *         use to make API calls.
     */
    public SNSImpl(AmazonSNS client) {
        ServiceModel model = V1ModelLoader.load(SNS.class,
                SNS.class.getAnnotation(V1ServiceInterface.class).model());

        this.service = new ServiceImpl<AmazonSNS>(model, client);
    }

    public SNSImpl(ServiceImpl<AmazonSNS> service) {
        this.service = service;
    }

    @Override
    public AmazonSNS client() {
        return service.getClient();
    }

    @Override
    public PlatformEndpoint getPlatformEndpoint(String arn) {
        ResourceImpl result = service.getSubResource("PlatformEndpoint", arn);
        if (result == null) return null;
        return new PlatformEndpointImpl(result);
    }

    @Override
    public PlatformApplication getPlatformApplication(String arn) {
        ResourceImpl result = service.getSubResource("PlatformApplication",
                arn);

        if (result == null) return null;
        return new PlatformApplicationImpl(result);
    }

    @Override
    public Topic getTopic(String arn) {
        ResourceImpl result = service.getSubResource("Topic", arn);
        if (result == null) return null;
        return new TopicImpl(result);
    }

    @Override
    public PlatformApplicationCollection getPlatformApplications() {
        return getPlatformApplications((ListPlatformApplicationsRequest)null);
    }

    @Override
    public PlatformApplicationCollection getPlatformApplications(
            ListPlatformApplicationsRequest request) {

        ResourceCollectionImpl result =
                service.getCollection("PlatformApplications", request);

        if (result == null) return null;
        return new PlatformApplicationCollectionImpl(result);
    }

    @Override
    public SubscriptionCollection getSubscriptions() {
        return getSubscriptions((ListSubscriptionsRequest)null);
    }

    @Override
    public SubscriptionCollection getSubscriptions(ListSubscriptionsRequest
            request) {

        ResourceCollectionImpl result = service.getCollection("Subscriptions",
                request);

        if (result == null) return null;
        return new SubscriptionCollectionImpl(result);
    }

    @Override
    public TopicCollection getTopics() {
        return getTopics((ListTopicsRequest)null);
    }

    @Override
    public TopicCollection getTopics(ListTopicsRequest request) {
        ResourceCollectionImpl result = service.getCollection("Topics",
                request);

        if (result == null) return null;
        return new TopicCollectionImpl(result);
    }

    @Override
    public Topic createTopic(CreateTopicRequest request) {
        return createTopic(request, null);
    }

    @Override
    public Topic createTopic(CreateTopicRequest request,
            ResultCapture<CreateTopicResult> extractor) {

        ActionResult result = service.performAction("CreateTopic", request,
                extractor);

        if (result == null) return null;
        return new TopicImpl(result.getResource());
    }

    @Override
    public Topic createTopic(String name) {
        return createTopic(name, (ResultCapture<CreateTopicResult>)null);
    }

    @Override
    public Topic createTopic(String name, ResultCapture<CreateTopicResult>
            extractor) {

        CreateTopicRequest request = new CreateTopicRequest()
            .withName(name);
        return createTopic(request, extractor);
    }

    @Override
    public PlatformApplication createPlatformApplication(
            CreatePlatformApplicationRequest request) {

        return createPlatformApplication(request, null);
    }

    @Override
    public PlatformApplication createPlatformApplication(
            CreatePlatformApplicationRequest request,
            ResultCapture<CreatePlatformApplicationResult> extractor) {

        ActionResult result = service.performAction("CreatePlatformApplication",
                request, extractor);

        if (result == null) return null;
        return new PlatformApplicationImpl(result.getResource());
    }
}
