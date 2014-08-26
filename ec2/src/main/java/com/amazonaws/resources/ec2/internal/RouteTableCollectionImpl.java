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
import com.amazonaws.resources.ec2.RouteTable;
import com.amazonaws.resources.ec2.RouteTableCollection;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.StandardPageIterable;
import com.amazonaws.resources.internal.StandardResourceIterator;
import com.amazonaws.resources.internal.StandardResourcePage;

class RouteTableCollectionImpl implements RouteTableCollection {

    private final ResourceCollectionImpl impl;

    public RouteTableCollectionImpl(ResourceCollectionImpl impl) {
        this.impl = impl;
    }

    @Override
    public Iterator<RouteTable> iterator() {
        return new StandardResourceIterator<RouteTable>(impl.iterator(),
                RouteTableImpl.CODEC);
    }

    @Override
    public Iterable<ResourcePage<RouteTable>> pages() {
        return new StandardPageIterable<RouteTable>(impl.pages(),
                RouteTableImpl.CODEC);
    }

    @Override
    public ResourcePage<RouteTable> firstPage() {
        return firstPage(null);
    }

    @Override
    public ResourcePage<RouteTable> firstPage(ResultCapture<Object> extractor) {
        return new StandardResourcePage<RouteTable>(impl.firstPage(extractor),
                RouteTableImpl.CODEC);
    }
}
