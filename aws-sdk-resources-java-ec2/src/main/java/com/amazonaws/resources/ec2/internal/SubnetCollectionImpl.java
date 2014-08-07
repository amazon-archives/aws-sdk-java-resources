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
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.SubnetCollection;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.StandardPageIterable;
import com.amazonaws.resources.internal.StandardResourceIterator;
import com.amazonaws.resources.internal.StandardResourcePage;

class SubnetCollectionImpl implements SubnetCollection {

    private final ResourceCollectionImpl impl;

    public SubnetCollectionImpl(ResourceCollectionImpl impl) {
        this.impl = impl;
    }

    @Override
    public Iterator<Subnet> iterator() {
        return new StandardResourceIterator<Subnet>(impl.iterator(),
                SubnetImpl.CODEC);
    }

    @Override
    public Iterable<ResourcePage<Subnet>> pages() {
        return new StandardPageIterable<Subnet>(impl.pages(), SubnetImpl.CODEC);
    }

    @Override
    public ResourcePage<Subnet> firstPage() {
        return firstPage(null);
    }

    @Override
    public ResourcePage<Subnet> firstPage(ResultCapture<Object> extractor) {
        return new StandardResourcePage<Subnet>(impl.firstPage(extractor),
                SubnetImpl.CODEC);
    }
}
