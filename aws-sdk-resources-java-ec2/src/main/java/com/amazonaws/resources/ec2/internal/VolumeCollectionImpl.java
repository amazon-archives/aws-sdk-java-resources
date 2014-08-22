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

import java.util.Iterator;

import com.amazonaws.resources.ResourcePage;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.Volume;
import com.amazonaws.resources.ec2.VolumeCollection;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.StandardPageIterable;
import com.amazonaws.resources.internal.StandardResourceIterator;
import com.amazonaws.resources.internal.StandardResourcePage;

class VolumeCollectionImpl implements VolumeCollection {

    private final ResourceCollectionImpl impl;

    public VolumeCollectionImpl(ResourceCollectionImpl impl) {
        this.impl = impl;
    }

    @Override
    public Iterator<Volume> iterator() {
        return new StandardResourceIterator<Volume>(impl.iterator(),
                VolumeImpl.CODEC);
    }

    @Override
    public Iterable<ResourcePage<Volume>> pages() {
        return new StandardPageIterable<Volume>(impl.pages(), VolumeImpl.CODEC);
    }

    @Override
    public ResourcePage<Volume> firstPage() {
        return firstPage(null);
    }

    @Override
    public ResourcePage<Volume> firstPage(ResultCapture<Object> extractor) {
        return new StandardResourcePage<Volume>(impl.firstPage(extractor),
                VolumeImpl.CODEC);
    }
}
