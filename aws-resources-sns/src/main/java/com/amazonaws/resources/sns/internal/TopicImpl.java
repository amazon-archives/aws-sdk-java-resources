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

import java.util.List;
import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sns.Subscription;
import com.amazonaws.resources.sns.SubscriptionCollection;
import com.amazonaws.resources.sns.Topic;
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

class TopicImpl implements Topic {
    public static final ResourceCodec<Topic> CODEC = new Codec();

    private final ResourceImpl resource;

    public TopicImpl(ResourceImpl resource) {
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
    public boolean load(GetTopicAttributesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetTopicAttributesRequest request,
            ResultCapture<GetTopicAttributesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getArn() {
        return (String) resource.getIdentifier("Arn");
    }

    @Override
    public Map<String, String> getAttributes() {
        return (Map<String, String>) resource.getAttribute("Attributes");
    }

    @Override
    public Subscription getSubscription(String arn) {
        ResourceImpl result = resource.getSubResource("Subscription", arn);
        if (result == null) return null;
        return new SubscriptionImpl(result);
    }

    @Override
    public SubscriptionCollection getSubscriptions() {
        return getSubscriptions(null);
    }

    @Override
    public SubscriptionCollection getSubscriptions(
            ListSubscriptionsByTopicRequest request) {

        ResourceCollectionImpl result = resource.getCollection("Subscriptions",
                request);

        if (result == null) return null;
        return new SubscriptionCollectionImpl(result);
    }

    @Override
    public PublishResult publish(PublishRequest request) {
        return publish(request, null);
    }

    @Override
    public PublishResult publish(PublishRequest request,
            ResultCapture<PublishResult> extractor) {

        ActionResult result = resource.performAction("Publish", request,
                extractor);

        if (result == null) return null;
        return (PublishResult) result.getData();
    }

    @Override
    public PublishResult publish(String message) {
        return publish(message, (ResultCapture<PublishResult>)null);
    }

    @Override
    public PublishResult publish(String message, ResultCapture<PublishResult>
            extractor) {

        PublishRequest request = new PublishRequest()
            .withMessage(message);
        return publish(request, extractor);
    }

    @Override
    public PublishResult publish(String subject, String message) {
        return publish(subject, message, (ResultCapture<PublishResult>)null);
    }

    @Override
    public PublishResult publish(String subject, String message,
            ResultCapture<PublishResult> extractor) {

        PublishRequest request = new PublishRequest()
            .withSubject(subject)
            .withMessage(message);
        return publish(request, extractor);
    }

    @Override
    public void setAttributes(SetTopicAttributesRequest request) {
        setAttributes(request, null);
    }

    @Override
    public void setAttributes(SetTopicAttributesRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("SetAttributes", request, extractor);
    }

    @Override
    public void setAttributes(String attributeName, String attributeValue) {
        setAttributes(attributeName, attributeValue, (ResultCapture<Void>)null);
    }

    @Override
    public void setAttributes(String attributeName, String attributeValue,
            ResultCapture<Void> extractor) {

        SetTopicAttributesRequest request = new SetTopicAttributesRequest()
            .withAttributeName(attributeName)
            .withAttributeValue(attributeValue);
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
    public void addPermission(String label, List<String> aWSAccountIds,
            List<String> actionNames) {

        addPermission(label, aWSAccountIds, actionNames,
                (ResultCapture<Void>)null);
    }

    @Override
    public void addPermission(String label, List<String> aWSAccountIds,
            List<String> actionNames, ResultCapture<Void> extractor) {

        AddPermissionRequest request = new AddPermissionRequest()
            .withLabel(label)
            .withAWSAccountIds(aWSAccountIds)
            .withActionNames(actionNames);
        addPermission(request, extractor);
    }

    @Override
    public void delete(DeleteTopicRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteTopicRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteTopicRequest request = new DeleteTopicRequest();
        delete(request, extractor);
    }

    @Override
    public Subscription confirmSubscription(ConfirmSubscriptionRequest request)
            {

        return confirmSubscription(request, null);
    }

    @Override
    public Subscription confirmSubscription(ConfirmSubscriptionRequest request,
            ResultCapture<ConfirmSubscriptionResult> extractor) {

        ActionResult result = resource.performAction("ConfirmSubscription",
                request, extractor);

        if (result == null) return null;
        return new SubscriptionImpl(result.getResource());
    }

    @Override
    public Subscription confirmSubscription(String token) {
        return confirmSubscription(token,
                (ResultCapture<ConfirmSubscriptionResult>)null);
    }

    @Override
    public Subscription confirmSubscription(String token,
            ResultCapture<ConfirmSubscriptionResult> extractor) {

        ConfirmSubscriptionRequest request = new ConfirmSubscriptionRequest()
            .withToken(token);
        return confirmSubscription(request, extractor);
    }

    @Override
    public Subscription confirmSubscription(String authenticateOnUnsubscribe,
            String token) {

        return confirmSubscription(authenticateOnUnsubscribe, token,
                (ResultCapture<ConfirmSubscriptionResult>)null);
    }

    @Override
    public Subscription confirmSubscription(String authenticateOnUnsubscribe,
            String token, ResultCapture<ConfirmSubscriptionResult> extractor) {

        ConfirmSubscriptionRequest request = new ConfirmSubscriptionRequest()
            .withAuthenticateOnUnsubscribe(authenticateOnUnsubscribe)
            .withToken(token);
        return confirmSubscription(request, extractor);
    }

    @Override
    public Subscription subscribe(SubscribeRequest request) {
        return subscribe(request, null);
    }

    @Override
    public Subscription subscribe(SubscribeRequest request,
            ResultCapture<SubscribeResult> extractor) {

        ActionResult result = resource.performAction("Subscribe", request,
                extractor);

        if (result == null) return null;
        return new SubscriptionImpl(result.getResource());
    }

    @Override
    public Subscription subscribe(String endpoint, String protocol) {
        return subscribe(endpoint, protocol,
                (ResultCapture<SubscribeResult>)null);
    }

    @Override
    public Subscription subscribe(String endpoint, String protocol,
            ResultCapture<SubscribeResult> extractor) {

        SubscribeRequest request = new SubscribeRequest()
            .withEndpoint(endpoint)
            .withProtocol(protocol);
        return subscribe(request, extractor);
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

    private static class Codec implements ResourceCodec<Topic> {
        @Override
        public Topic transform(ResourceImpl resource) {
            return new TopicImpl(resource);
        }
    }
}
