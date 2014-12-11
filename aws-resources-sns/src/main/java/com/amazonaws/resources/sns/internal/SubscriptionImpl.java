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

import java.util.Map;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.resources.sns.Subscription;
import com.amazonaws.resources.sns.Topic;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.GetSubscriptionAttributesResult;
import com.amazonaws.services.sns.model.SetSubscriptionAttributesRequest;
import com.amazonaws.services.sns.model.UnsubscribeRequest;

class SubscriptionImpl implements Subscription {
    public static final ResourceCodec<Subscription> CODEC = new Codec();

    private final ResourceImpl resource;

    public SubscriptionImpl(ResourceImpl resource) {
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
    public boolean load(GetSubscriptionAttributesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(GetSubscriptionAttributesRequest request,
            ResultCapture<GetSubscriptionAttributesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getArn() {
        return (String) resource.getIdentifier("Arn");
    }

    @Override
    public String getTopicArn() {
        return (String) resource.getIdentifier("TopicArn");
    }

    @Override
    public Map<String, String> getAttributes() {
        return (Map<String, String>) resource.getAttribute("Attributes");
    }

    @Override
    public Topic getTopic() {
        ResourceImpl result = resource.getReference("Topic");
        if (result == null) return null;
        return new TopicImpl(result);
    }

    @Override
    public void setAttributes(SetSubscriptionAttributesRequest request) {
        setAttributes(request, null);
    }

    @Override
    public void setAttributes(SetSubscriptionAttributesRequest request,
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

        SetSubscriptionAttributesRequest request = new
                SetSubscriptionAttributesRequest()

            .withAttributeName(attributeName)
            .withAttributeValue(attributeValue);
        setAttributes(request, extractor);
    }

    @Override
    public void delete(UnsubscribeRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(UnsubscribeRequest request, ResultCapture<Void> extractor
            ) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        UnsubscribeRequest request = new UnsubscribeRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<Subscription> {
        @Override
        public Subscription transform(ResourceImpl resource) {
            return new SubscriptionImpl(resource);
        }
    }
}
