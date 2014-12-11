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

import java.util.Iterator;

import com.amazonaws.resources.ResourcePage;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.StandardPageIterable;
import com.amazonaws.resources.internal.StandardResourceIterator;
import com.amazonaws.resources.internal.StandardResourcePage;
import com.amazonaws.resources.sns.PlatformApplication;
import com.amazonaws.resources.sns.PlatformApplicationCollection;

class PlatformApplicationCollectionImpl implements PlatformApplicationCollection
        {

    private final ResourceCollectionImpl impl;

    public PlatformApplicationCollectionImpl(ResourceCollectionImpl impl) {
        this.impl = impl;
    }

    @Override
    public Iterator<PlatformApplication> iterator() {
        return new
                StandardResourceIterator<PlatformApplication>(impl.iterator(),
                PlatformApplicationImpl.CODEC);
    }

    @Override
    public Iterable<ResourcePage<PlatformApplication>> pages() {
        return new StandardPageIterable<PlatformApplication>(impl.pages(),
                PlatformApplicationImpl.CODEC);
    }

    @Override
    public ResourcePage<PlatformApplication> firstPage() {
        return firstPage(null);
    }

    @Override
    public ResourcePage<PlatformApplication> firstPage(ResultCapture<Object>
            extractor) {

        return new
                StandardResourcePage<PlatformApplication>(impl.firstPage(extractor),
                PlatformApplicationImpl.CODEC);
    }
}
