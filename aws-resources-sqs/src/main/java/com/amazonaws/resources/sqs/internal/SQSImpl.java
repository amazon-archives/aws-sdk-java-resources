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
package com.amazonaws.resources.sqs.internal;

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
import com.amazonaws.resources.sqs.Queue;
import com.amazonaws.resources.sqs.QueueCollection;
import com.amazonaws.resources.sqs.SQS;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;

public class SQSImpl implements SQS {

    private final ServiceImpl<AmazonSQS> service;

    /**
     * Construct a service implementation that talks to the specified AWS
     * region. The low-level client will be created via the default no-arg
     * constructor, which means it will have all the default client
     * configurations and it will use the default provider chain to retrieve AWS
     * credentials. If you need more flexible control over the low-level client,
     * use {@link #SQSImpl(AmazonSQS)} instead.
     *
     * @param region The AWS region where the service API calls will be sent to.
     */
    public SQSImpl(Regions region) {
        this(new AmazonSQSClient());
        this.client().setRegion(Region.getRegion(region));
    }

    /**
     * Construct a service implementation using the specified client object.
     *
     * @param client The low-level client which the service implementation will
     *         use to make API calls.
     */
    public SQSImpl(AmazonSQS client) {
        ServiceModel model = V1ModelLoader.load(SQS.class,
                SQS.class.getAnnotation(V1ServiceInterface.class).model());

        this.service = new ServiceImpl<AmazonSQS>(model, client);
    }

    public SQSImpl(ServiceImpl<AmazonSQS> service) {
        this.service = service;
    }

    @Override
    public AmazonSQS client() {
        return service.getClient();
    }

    @Override
    public Queue getQueue(String url) {
        ResourceImpl result = service.getSubResource("Queue", url);
        if (result == null) return null;
        return new QueueImpl(result);
    }

    @Override
    public QueueCollection getQueues(String queueNamePrefix) {
        return getQueues( new ListQueuesRequest(queueNamePrefix));
    }

    @Override
    public QueueCollection getQueues() {
        return getQueues((ListQueuesRequest)null);
    }

    @Override
    public QueueCollection getQueues(ListQueuesRequest request) {
        ResourceCollectionImpl result = service.getCollection("Queues",
                request);

        if (result == null) return null;
        return new QueueCollectionImpl(result);
    }

    @Override
    public Queue getQueueByName(GetQueueUrlRequest request) {
        return getQueueByName(request, null);
    }

    @Override
    public Queue getQueueByName(GetQueueUrlRequest request,
            ResultCapture<GetQueueUrlResult> extractor) {

        ActionResult result = service.performAction("GetQueueByName", request,
                extractor);

        if (result == null) return null;
        return new QueueImpl(result.getResource());
    }

    @Override
    public Queue getQueueByName(String queueName) {
        return getQueueByName(queueName,
                (ResultCapture<GetQueueUrlResult>)null);
    }

    @Override
    public Queue getQueueByName(String queueName,
            ResultCapture<GetQueueUrlResult> extractor) {

        GetQueueUrlRequest request = new GetQueueUrlRequest()
            .withQueueName(queueName);
        return getQueueByName(request, extractor);
    }

    @Override
    public Queue createQueue(CreateQueueRequest request) {
        return createQueue(request, null);
    }

    @Override
    public Queue createQueue(CreateQueueRequest request,
            ResultCapture<CreateQueueResult> extractor) {

        ActionResult result = service.performAction("CreateQueue", request,
                extractor);

        if (result == null) return null;
        return new QueueImpl(result.getResource());
    }

    @Override
    public Queue createQueue(String queueName) {
        return createQueue(queueName, (ResultCapture<CreateQueueResult>)null);
    }

    @Override
    public Queue createQueue(String queueName, ResultCapture<CreateQueueResult>
            extractor) {

        CreateQueueRequest request = new CreateQueueRequest()
            .withQueueName(queueName);
        return createQueue(request, extractor);
    }
}
