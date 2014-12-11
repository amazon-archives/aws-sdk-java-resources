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

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.MessageAttributeValue;

/**
 * The <code>Message</code> resource.
 * Each <code>Message</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>ReceiptHandle</li>
 *   <li>QueueUrl</li>
 * </ul>
 */
public interface Message {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the ReceiptHandle identifier. This method always
     * directly returns the identifier and never involves a service call.
     */
    String getReceiptHandle();

    /**
     * Gets the value of the QueueUrl identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getQueueUrl();

    /**
     * Gets the value of the Attributes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Map<String, String> getAttributes();

    /**
     * Gets the value of the Body attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getBody();

    /**
     * Gets the value of the MD5OfMessageAttributes attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    String getMD5OfMessageAttributes();

    /**
     * Gets the value of the MessageAttributes attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    Map<String, MessageAttributeValue> getMessageAttributes();

    /**
     * Gets the value of the MessageId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getMessageId();

    /**
     * Gets the value of the MD5OfBody attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getMD5OfBody();

    /**
     * Retrieves the <code>Queue</code> resource referenced by this resource.
     */
    Queue getQueue();

    /**
     * Performs the <code>ChangeVisibility</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Message</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>QueueUrl</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>ReceiptHandle</code></b>
     *         - mapped from the <code>ReceiptHandle</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ChangeMessageVisibilityRequest
     */
    void changeVisibility(ChangeMessageVisibilityRequest request);

    /**
     * Performs the <code>ChangeVisibility</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Message</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>QueueUrl</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>ReceiptHandle</code></b>
     *         - mapped from the <code>ReceiptHandle</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ChangeMessageVisibilityRequest
     */
    void changeVisibility(ChangeMessageVisibilityRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ChangeVisibility</code> action.
     *
     * @see #changeVisibility(ChangeMessageVisibilityRequest)
     */
    void changeVisibility(Integer visibilityTimeout);

    /**
     * The convenient method form for the <code>ChangeVisibility</code> action.
     *
     * @see #changeVisibility(ChangeMessageVisibilityRequest, ResultCapture)
     */
    void changeVisibility(Integer visibilityTimeout, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Message</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>QueueUrl</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>ReceiptHandle</code></b>
     *         - mapped from the <code>ReceiptHandle</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteMessageRequest
     */
    void delete(DeleteMessageRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Message</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>QueueUrl</code></b>
     *         - mapped from the <code>QueueUrl</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>ReceiptHandle</code></b>
     *         - mapped from the <code>ReceiptHandle</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteMessageRequest
     */
    void delete(DeleteMessageRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteMessageRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteMessageRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);
}
