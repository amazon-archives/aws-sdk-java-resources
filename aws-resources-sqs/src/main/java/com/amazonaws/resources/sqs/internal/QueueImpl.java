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

import java.util.List;
import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sqs.Message;
import com.amazonaws.resources.sqs.Queue;
import com.amazonaws.resources.sqs.QueueCollection;
import com.amazonaws.services.sqs.model.AddPermissionRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchRequestEntry
;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityBatchResult;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequest;
import com.amazonaws.services.sqs.model.DeleteMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.DeleteMessageBatchResult;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.ListDeadLetterSourceQueuesRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.RemovePermissionRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

class QueueImpl implements Queue {
    public static final ResourceCodec<Queue> CODEC = new Codec();

    private final ResourceImpl resource;

    public QueueImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public boolean load() {
        return load(null, null);
    }

    @Override
    public boolean load(GetQueueAttributesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetQueueAttributesRequest request,
            ResultCapture<GetQueueAttributesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getUrl() {
        return (String) resource.getIdentifier("Url");
    }

    @Override
    public Map<String, String> getAttributes() {
        return (Map<String, String>) resource.getAttribute("Attributes");
    }

    @Override
    public Message getMessage(String receiptHandle) {
        ResourceImpl result = resource.getSubResource("Message", receiptHandle);
        if (result == null) return null;
        return new MessageImpl(result);
    }

    @Override
    public QueueCollection getDeadLetterSourceQueues() {
        return getDeadLetterSourceQueues(null);
    }

    @Override
    public QueueCollection getDeadLetterSourceQueues(
            ListDeadLetterSourceQueuesRequest request) {

        ResourceCollectionImpl result =
                resource.getCollection("DeadLetterSourceQueues", request);

        if (result == null) return null;
        return new QueueCollectionImpl(result);
    }

    @Override
    public void setAttributes(SetQueueAttributesRequest request) {
        setAttributes(request, null);
    }

    @Override
    public void setAttributes(SetQueueAttributesRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("SetAttributes", request, extractor);
    }

    @Override
    public void setAttributes(Map<String, String> attributes) {
        setAttributes(attributes, (ResultCapture<Void>)null);
    }

    @Override
    public void setAttributes(Map<String, String> attributes,
            ResultCapture<Void> extractor) {

        SetQueueAttributesRequest request = new SetQueueAttributesRequest()
            .withAttributes(attributes);
        setAttributes(request, extractor);
    }

    @Override
    public void addPermission(AddPermissionRequest request) {
        addPermission(request, null);
    }

    @Override
    public void addPermission(AddPermissionRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("AddPermission", request, extractor);
    }

    @Override
    public void addPermission(List<String> actions, String label, List<String>
            aWSAccountIds) {

        addPermission(actions, label, aWSAccountIds, (ResultCapture<Void>)null);
    }

    @Override
    public void addPermission(List<String> actions, String label, List<String>
            aWSAccountIds, ResultCapture<Void> extractor) {

        AddPermissionRequest request = new AddPermissionRequest()
            .withActions(actions)
            .withLabel(label)
            .withAWSAccountIds(aWSAccountIds);
        addPermission(request, extractor);
    }

    @Override
    public SendMessageResult sendMessage(SendMessageRequest request) {
        return sendMessage(request, null);
    }

    @Override
    public SendMessageResult sendMessage(SendMessageRequest request,
            ResultCapture<SendMessageResult> extractor) {

        ActionResult result = resource.performAction("SendMessage", request,
                extractor);

        if (result == null) return null;
        return (SendMessageResult) result.getData();
    }

    @Override
    public SendMessageResult sendMessage(String messageBody) {
        return sendMessage(messageBody, (ResultCapture<SendMessageResult>)null);
    }

    @Override
    public SendMessageResult sendMessage(String messageBody,
            ResultCapture<SendMessageResult> extractor) {

        SendMessageRequest request = new SendMessageRequest()
            .withMessageBody(messageBody);
        return sendMessage(request, extractor);
    }

    @Override
    public DeleteMessageBatchResult deleteMessages(DeleteMessageBatchRequest
            request) {

        return deleteMessages(request, null);
    }

    @Override
    public DeleteMessageBatchResult deleteMessages(DeleteMessageBatchRequest
            request, ResultCapture<DeleteMessageBatchResult> extractor) {

        ActionResult result = resource.performAction("DeleteMessages", request,
                extractor);

        if (result == null) return null;
        return (DeleteMessageBatchResult) result.getData();
    }

    @Override
    public DeleteMessageBatchResult deleteMessages() {
        return deleteMessages((ResultCapture<DeleteMessageBatchResult>)null);
    }

    @Override
    public DeleteMessageBatchResult deleteMessages(
            ResultCapture<DeleteMessageBatchResult> extractor) {

        DeleteMessageBatchRequest request = new DeleteMessageBatchRequest();
        return deleteMessages(request, extractor);
    }

    @Override
    public DeleteMessageBatchResult deleteMessages(
            List<DeleteMessageBatchRequestEntry> entries) {

        return deleteMessages(entries,
                (ResultCapture<DeleteMessageBatchResult>)null);
    }

    @Override
    public DeleteMessageBatchResult deleteMessages(
            List<DeleteMessageBatchRequestEntry> entries,
            ResultCapture<DeleteMessageBatchResult> extractor) {

        DeleteMessageBatchRequest request = new DeleteMessageBatchRequest()
            .withEntries(entries);
        return deleteMessages(request, extractor);
    }

    @Override
    public List<Message> receiveMessages(ReceiveMessageRequest request) {
        return receiveMessages(request, null);
    }

    @Override
    public List<Message> receiveMessages(ReceiveMessageRequest request,
            ResultCapture<ReceiveMessageResult> extractor) {

        ActionResult result = resource.performAction("ReceiveMessages", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), MessageImpl.CODEC);
    }

    @Override
    public List<Message> receiveMessages() {
        return receiveMessages((ResultCapture<ReceiveMessageResult>)null);
    }

    @Override
    public List<Message> receiveMessages(ResultCapture<ReceiveMessageResult>
            extractor) {

        ReceiveMessageRequest request = new ReceiveMessageRequest();
        return receiveMessages(request, extractor);
    }

    @Override
    public void delete(DeleteQueueRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteQueueRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteQueueRequest request = new DeleteQueueRequest();
        delete(request, extractor);
    }

    @Override
    public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            ChangeMessageVisibilityBatchRequest request) {

        return changeMessageVisibilityBatch(request, null);
    }

    @Override
    public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            ChangeMessageVisibilityBatchRequest request,
            ResultCapture<ChangeMessageVisibilityBatchResult> extractor) {

        ActionResult result =
                resource.performAction("ChangeMessageVisibilityBatch", request,
                extractor);

        if (result == null) return null;
        return (ChangeMessageVisibilityBatchResult) result.getData();
    }

    @Override
    public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            List<ChangeMessageVisibilityBatchRequestEntry> entries) {

        return changeMessageVisibilityBatch(entries,
                (ResultCapture<ChangeMessageVisibilityBatchResult>)null);
    }

    @Override
    public ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            List<ChangeMessageVisibilityBatchRequestEntry> entries,
            ResultCapture<ChangeMessageVisibilityBatchResult> extractor) {

        ChangeMessageVisibilityBatchRequest request = new
                ChangeMessageVisibilityBatchRequest()

            .withEntries(entries);
        return changeMessageVisibilityBatch(request, extractor);
    }

    @Override
    public SendMessageBatchResult sendMessages(SendMessageBatchRequest request)
            {

        return sendMessages(request, null);
    }

    @Override
    public SendMessageBatchResult sendMessages(SendMessageBatchRequest request,
            ResultCapture<SendMessageBatchResult> extractor) {

        ActionResult result = resource.performAction("SendMessages", request,
                extractor);

        if (result == null) return null;
        return (SendMessageBatchResult) result.getData();
    }

    @Override
    public SendMessageBatchResult sendMessages() {
        return sendMessages((ResultCapture<SendMessageBatchResult>)null);
    }

    @Override
    public SendMessageBatchResult sendMessages(
            ResultCapture<SendMessageBatchResult> extractor) {

        SendMessageBatchRequest request = new SendMessageBatchRequest();
        return sendMessages(request, extractor);
    }

    @Override
    public SendMessageBatchResult sendMessages(
            List<SendMessageBatchRequestEntry> entries) {

        return sendMessages(entries,
                (ResultCapture<SendMessageBatchResult>)null);
    }

    @Override
    public SendMessageBatchResult sendMessages(
            List<SendMessageBatchRequestEntry> entries,
            ResultCapture<SendMessageBatchResult> extractor) {

        SendMessageBatchRequest request = new SendMessageBatchRequest()
            .withEntries(entries);
        return sendMessages(request, extractor);
    }

    @Override
    public void removePermission(RemovePermissionRequest request) {
        removePermission(request, null);
    }

    @Override
    public void removePermission(RemovePermissionRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("RemovePermission", request, extractor);
    }

    @Override
    public void removePermission(String label) {
        removePermission(label, (ResultCapture<Void>)null);
    }

    @Override
    public void removePermission(String label, ResultCapture<Void> extractor) {
        RemovePermissionRequest request = new RemovePermissionRequest()
            .withLabel(label);
        removePermission(request, extractor);
    }

    private static class Codec implements ResourceCodec<Queue> {
        @Override
        public Queue transform(ResourceImpl resource) {
            return new QueueImpl(resource);
        }
    }
}
