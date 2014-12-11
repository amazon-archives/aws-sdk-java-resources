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
package com.amazonaws.resources.sqs;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlRequest;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ListQueuesRequest;

/**
 * The <code>SQS</code> service.
 * This is the entry point to interact with the following service resources:<ul>
 *   <li>Message</li>
 *   <li>Queue</li>
 * </ul>
 */
@V1ServiceInterface(model="model.json", impl=
        "com.amazonaws.resources.sqs.internal.SQSImpl")

public interface SQS extends Service<AmazonSQS> {
    /**
     * Gets an instance of {@code Queue} resource by its identifier(s).
     */
    Queue getQueue(String url);

    /**
     * Retrieves the Queues collection referenced by this resource.
     */
    QueueCollection getQueues(String queueNamePrefix);

    /**
     * Retrieves the Queues collection referenced by this resource.
     */
    QueueCollection getQueues();

    /**
     * Retrieves the Queues collection referenced by this resource.
     */
    QueueCollection getQueues(ListQueuesRequest request);

    /**
     * Performs the <code>GetQueueByName</code> action.
     *
     * <p>
     *
     * @return The <code>Queue</code> resource object associated with the result
     *         of this action.
     * @see GetQueueUrlRequest
     */
    com.amazonaws.resources.sqs.Queue getQueueByName(GetQueueUrlRequest request)
            ;

    /**
     * Performs the <code>GetQueueByName</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Queue</code> resource object associated with the result
     *         of this action.
     * @see GetQueueUrlRequest
     */
    com.amazonaws.resources.sqs.Queue getQueueByName(GetQueueUrlRequest request,
            ResultCapture<GetQueueUrlResult> extractor);

    /**
     * The convenient method form for the <code>GetQueueByName</code> action.
     *
     * @see #getQueueByName(GetQueueUrlRequest)
     */
    com.amazonaws.resources.sqs.Queue getQueueByName(String queueName);

    /**
     * The convenient method form for the <code>GetQueueByName</code> action.
     *
     * @see #getQueueByName(GetQueueUrlRequest, ResultCapture)
     */
    com.amazonaws.resources.sqs.Queue getQueueByName(String queueName,
            ResultCapture<GetQueueUrlResult> extractor);

    /**
     * Performs the <code>CreateQueue</code> action.
     *
     * <p>
     *
     * @return The <code>Queue</code> resource object associated with the result
     *         of this action.
     * @see CreateQueueRequest
     */
    com.amazonaws.resources.sqs.Queue createQueue(CreateQueueRequest request);

    /**
     * Performs the <code>CreateQueue</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @return The <code>Queue</code> resource object associated with the result
     *         of this action.
     * @see CreateQueueRequest
     */
    com.amazonaws.resources.sqs.Queue createQueue(CreateQueueRequest request,
            ResultCapture<CreateQueueResult> extractor);

    /**
     * The convenient method form for the <code>CreateQueue</code> action.
     *
     * @see #createQueue(CreateQueueRequest)
     */
    com.amazonaws.resources.sqs.Queue createQueue(String queueName);

    /**
     * The convenient method form for the <code>CreateQueue</code> action.
     *
     * @see #createQueue(CreateQueueRequest, ResultCapture)
     */
    com.amazonaws.resources.sqs.Queue createQueue(String queueName,
            ResultCapture<CreateQueueResult> extractor);
}
