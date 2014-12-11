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

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sqs.Message;
import com.amazonaws.resources.sqs.Queue;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.MessageAttributeValue;

class MessageImpl implements Message {
    public static final ResourceCodec<Message> CODEC = new Codec();

    private final ResourceImpl resource;

    public MessageImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getReceiptHandle() {
        return (String) resource.getIdentifier("ReceiptHandle");
    }

    @Override
    public String getQueueUrl() {
        return (String) resource.getIdentifier("QueueUrl");
    }

    @Override
    public Map<String, String> getAttributes() {
        return (Map<String, String>) resource.getAttribute("Attributes");
    }

    @Override
    public String getBody() {
        return (String) resource.getAttribute("Body");
    }

    @Override
    public String getMD5OfMessageAttributes() {
        return (String) resource.getAttribute("MD5OfMessageAttributes");
    }

    @Override
    public Map<String, MessageAttributeValue> getMessageAttributes() {
        return (Map<String, MessageAttributeValue>)
                resource.getAttribute("MessageAttributes");
    }

    @Override
    public String getMessageId() {
        return (String) resource.getAttribute("MessageId");
    }

    @Override
    public String getMD5OfBody() {
        return (String) resource.getAttribute("MD5OfBody");
    }

    @Override
    public Queue getQueue() {
        ResourceImpl result = resource.getReference("Queue");
        if (result == null) return null;
        return new QueueImpl(result);
    }

    @Override
    public void changeVisibility(ChangeMessageVisibilityRequest request) {
        changeVisibility(request, null);
    }

    @Override
    public void changeVisibility(ChangeMessageVisibilityRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ChangeVisibility", request, extractor);
    }

    @Override
    public void changeVisibility(Integer visibilityTimeout) {
        changeVisibility(visibilityTimeout, (ResultCapture<Void>)null);
    }

    @Override
    public void changeVisibility(Integer visibilityTimeout, ResultCapture<Void>
            extractor) {

        ChangeMessageVisibilityRequest request = new
                ChangeMessageVisibilityRequest()

            .withVisibilityTimeout(visibilityTimeout);
        changeVisibility(request, extractor);
    }

    @Override
    public void delete(DeleteMessageRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteMessageRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteMessageRequest request = new DeleteMessageRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<Message> {
        @Override
        public Message transform(ResourceImpl resource) {
            return new MessageImpl(resource);
        }
    }
}
