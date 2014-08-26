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
package com.amazonaws.resources.ec2.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.KeyPair;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;

class KeyPairImpl implements KeyPair {
    public static final ResourceCodec<KeyPair> CODEC = new Codec();

    private final ResourceImpl resource;

    public KeyPairImpl(ResourceImpl resource) {
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
    public boolean load(DescribeKeyPairsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeKeyPairsRequest request,
            ResultCapture<DescribeKeyPairsResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getName() {
        return (String) resource.getIdentifier("Name");
    }

    @Override
    public String getKeyFingerprint() {
        return (String) resource.getAttribute("KeyFingerprint");
    }

    @Override
    public void delete(DeleteKeyPairRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteKeyPairRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteKeyPairRequest request = new DeleteKeyPairRequest();
        delete(request, extractor);
    }

    private static class Codec implements ResourceCodec<KeyPair> {
        @Override
        public KeyPair transform(ResourceImpl resource) {
            return new KeyPairImpl(resource);
        }
    }
}
