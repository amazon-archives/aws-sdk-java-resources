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

import java.util.List;
import java.util.Map;

import com.amazonaws.resources.ResultCapture;
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

/**
 * The <code>Queue</code> resource.
 * Each <code>Queue</code> object is uniquely identified by these identifier(s):
 * <ul>
 *   <li>Url</li>
 * </ul>
 */
public interface Queue {
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
     * @see #load(GetQueueAttributesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AttributeNames.0</code></b>
     *         - constant value <code>All</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetQueueAttributesRequest
     */
    boolean load(GetQueueAttributesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>AttributeNames.0</code></b>
     *         - constant value <code>All</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see GetQueueAttributesRequest
     */
    boolean load(GetQueueAttributesRequest request,
            ResultCapture<GetQueueAttributesResult> extractor);

    /**
     * Gets the value of the Url identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getUrl();

    /**
     * Gets the value of the Attributes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Map<String, String> getAttributes();

    /**
     * Gets a subresource.
     */
    Message getMessage(String receiptHandle);

    /**
     * Retrieves the DeadLetterSourceQueues collection referenced by this
     * resource.
     */
    QueueCollection getDeadLetterSourceQueues();

    /**
     * Retrieves the DeadLetterSourceQueues collection referenced by this
     * resource.
     */
    QueueCollection getDeadLetterSourceQueues(ListDeadLetterSourceQueuesRequest
            request);

    /**
     * Performs the <code>SetAttributes</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetQueueAttributesRequest
     */
    void setAttributes(SetQueueAttributesRequest request);

    /**
     * Performs the <code>SetAttributes</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see SetQueueAttributesRequest
     */
    void setAttributes(SetQueueAttributesRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetQueueAttributesRequest)
     */
    void setAttributes(Map<String, String> attributes);

    /**
     * The convenient method form for the <code>SetAttributes</code> action.
     *
     * @see #setAttributes(SetQueueAttributesRequest, ResultCapture)
     */
    void setAttributes(Map<String, String> attributes, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>AddPermission</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
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
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
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
    void addPermission(List<String> actions, String label, List<String>
            aWSAccountIds);

    /**
     * The convenient method form for the <code>AddPermission</code> action.
     *
     * @see #addPermission(AddPermissionRequest, ResultCapture)
     */
    void addPermission(List<String> actions, String label, List<String>
            aWSAccountIds, ResultCapture<Void> extractor);

    /**
     * Performs the <code>SendMessage</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see SendMessageRequest
     */
    SendMessageResult sendMessage(SendMessageRequest request);

    /**
     * Performs the <code>SendMessage</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see SendMessageRequest
     */
    SendMessageResult sendMessage(SendMessageRequest request,
            ResultCapture<SendMessageResult> extractor);

    /**
     * The convenient method form for the <code>SendMessage</code> action.
     *
     * @see #sendMessage(SendMessageRequest)
     */
    SendMessageResult sendMessage(String messageBody);

    /**
     * The convenient method form for the <code>SendMessage</code> action.
     *
     * @see #sendMessage(SendMessageRequest, ResultCapture)
     */
    SendMessageResult sendMessage(String messageBody,
            ResultCapture<SendMessageResult> extractor);

    /**
     * Performs the <code>DeleteMessages</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DeleteMessageBatchRequest
     */
    DeleteMessageBatchResult deleteMessages(DeleteMessageBatchRequest request);

    /**
     * Performs the <code>DeleteMessages</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DeleteMessageBatchRequest
     */
    DeleteMessageBatchResult deleteMessages(DeleteMessageBatchRequest request,
            ResultCapture<DeleteMessageBatchResult> extractor);

    /**
     * The convenient method form for the <code>DeleteMessages</code> action.
     *
     * @see #deleteMessages(DeleteMessageBatchRequest)
     */
    DeleteMessageBatchResult deleteMessages();

    /**
     * The convenient method form for the <code>DeleteMessages</code> action.
     *
     * @see #deleteMessages(DeleteMessageBatchRequest, ResultCapture)
     */
    DeleteMessageBatchResult deleteMessages(
            ResultCapture<DeleteMessageBatchResult> extractor);

    /**
     * The convenient method form for the <code>DeleteMessages</code> action.
     *
     * @see #deleteMessages(DeleteMessageBatchRequest)
     */
    DeleteMessageBatchResult deleteMessages(List<DeleteMessageBatchRequestEntry>
            entries);

    /**
     * The convenient method form for the <code>DeleteMessages</code> action.
     *
     * @see #deleteMessages(DeleteMessageBatchRequest, ResultCapture)
     */
    DeleteMessageBatchResult deleteMessages(List<DeleteMessageBatchRequestEntry>
            entries, ResultCapture<DeleteMessageBatchResult> extractor);

    /**
     * Performs the <code>ReceiveMessages</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Message</code> resource objects associated with
     *         the result of this action.
     * @see ReceiveMessageRequest
     */
    List<com.amazonaws.resources.sqs.Message> receiveMessages(
            ReceiveMessageRequest request);

    /**
     * Performs the <code>ReceiveMessages</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Message</code> resource objects associated with
     *         the result of this action.
     * @see ReceiveMessageRequest
     */
    List<com.amazonaws.resources.sqs.Message> receiveMessages(
            ReceiveMessageRequest request, ResultCapture<ReceiveMessageResult>
            extractor);

    /**
     * The convenient method form for the <code>ReceiveMessages</code> action.
     *
     * @see #receiveMessages(ReceiveMessageRequest)
     */
    List<com.amazonaws.resources.sqs.Message> receiveMessages();

    /**
     * The convenient method form for the <code>ReceiveMessages</code> action.
     *
     * @see #receiveMessages(ReceiveMessageRequest, ResultCapture)
     */
    List<com.amazonaws.resources.sqs.Message> receiveMessages(
            ResultCapture<ReceiveMessageResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteQueueRequest
     */
    void delete(DeleteQueueRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteQueueRequest
     */
    void delete(DeleteQueueRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteQueueRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteQueueRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>ChangeMessageVisibilityBatch</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ChangeMessageVisibilityBatchRequest
     */
    ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            ChangeMessageVisibilityBatchRequest request);

    /**
     * Performs the <code>ChangeMessageVisibilityBatch</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ChangeMessageVisibilityBatchRequest
     */
    ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            ChangeMessageVisibilityBatchRequest request,
            ResultCapture<ChangeMessageVisibilityBatchResult> extractor);

    /**
     * The convenient method form for the
     * <code>ChangeMessageVisibilityBatch</code> action.
     *
     * @see #changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest)
     */
    ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            List<ChangeMessageVisibilityBatchRequestEntry> entries);

    /**
     * The convenient method form for the
     * <code>ChangeMessageVisibilityBatch</code> action.
     *
     * @see #changeMessageVisibilityBatch(ChangeMessageVisibilityBatchRequest,
     *         ResultCapture)
     */
    ChangeMessageVisibilityBatchResult changeMessageVisibilityBatch(
            List<ChangeMessageVisibilityBatchRequestEntry> entries,
            ResultCapture<ChangeMessageVisibilityBatchResult> extractor);

    /**
     * Performs the <code>SendMessages</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see SendMessageBatchRequest
     */
    SendMessageBatchResult sendMessages(SendMessageBatchRequest request);

    /**
     * Performs the <code>SendMessages</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see SendMessageBatchRequest
     */
    SendMessageBatchResult sendMessages(SendMessageBatchRequest request,
            ResultCapture<SendMessageBatchResult> extractor);

    /**
     * The convenient method form for the <code>SendMessages</code> action.
     *
     * @see #sendMessages(SendMessageBatchRequest)
     */
    SendMessageBatchResult sendMessages();

    /**
     * The convenient method form for the <code>SendMessages</code> action.
     *
     * @see #sendMessages(SendMessageBatchRequest, ResultCapture)
     */
    SendMessageBatchResult sendMessages(ResultCapture<SendMessageBatchResult>
            extractor);

    /**
     * The convenient method form for the <code>SendMessages</code> action.
     *
     * @see #sendMessages(SendMessageBatchRequest)
     */
    SendMessageBatchResult sendMessages(List<SendMessageBatchRequestEntry>
            entries);

    /**
     * The convenient method form for the <code>SendMessages</code> action.
     *
     * @see #sendMessages(SendMessageBatchRequest, ResultCapture)
     */
    SendMessageBatchResult sendMessages(List<SendMessageBatchRequestEntry>
            entries, ResultCapture<SendMessageBatchResult> extractor);

    /**
     * Performs the <code>RemovePermission</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
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
     * <code>Queue</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>Url</code> identifier.
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
