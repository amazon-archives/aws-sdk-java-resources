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
package com.amazonaws.resources.internal;

import java.util.Iterator;

public final class StandardResourceIterator<T> implements Iterator<T> {

    private final Iterator<ResourceImpl> impl;
    private final ResourceCodec<T> codec;

    public StandardResourceIterator(
            Iterator<ResourceImpl> impl,
            ResourceCodec<T> codec) {

        this.impl = impl;
        this.codec = codec;
    }

    @Override
    public boolean hasNext() {
        return impl.hasNext();
    }

    @Override
    public T next() {
        return codec.transform(impl.next());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
