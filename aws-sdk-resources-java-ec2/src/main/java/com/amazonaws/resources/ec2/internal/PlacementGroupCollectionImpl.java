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
import com.amazonaws.resources.ec2.PlacementGroup;
import com.amazonaws.resources.ec2.PlacementGroupCollection;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.StandardPageIterable;
import com.amazonaws.resources.internal.StandardResourceIterator;
import com.amazonaws.resources.internal.StandardResourcePage;

class PlacementGroupCollectionImpl implements PlacementGroupCollection {

    private final ResourceCollectionImpl impl;

    public PlacementGroupCollectionImpl(ResourceCollectionImpl impl) {
        this.impl = impl;
    }

    @Override
    public Iterator<PlacementGroup> iterator() {
        return new StandardResourceIterator<PlacementGroup>(impl.iterator(),
                PlacementGroupImpl.CODEC);
    }

    @Override
    public Iterable<ResourcePage<PlacementGroup>> pages() {
        return new StandardPageIterable<PlacementGroup>(impl.pages(),
                PlacementGroupImpl.CODEC);
    }

    @Override
    public ResourcePage<PlacementGroup> firstPage() {
        return firstPage(null);
    }

    @Override
    public ResourcePage<PlacementGroup> firstPage(ResultCapture<Object>
            extractor) {

        return new
                StandardResourcePage<PlacementGroup>(impl.firstPage(extractor),
                PlacementGroupImpl.CODEC);
    }
}
