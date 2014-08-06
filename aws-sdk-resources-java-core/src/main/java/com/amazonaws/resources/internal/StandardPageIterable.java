package com.amazonaws.resources.internal;

import java.util.Iterator;

import com.amazonaws.resources.ResourcePage;

public final class StandardPageIterable<T>
        implements Iterable<ResourcePage<T>> {

    private final Iterable<ResourcePageImpl> impl;
    private final ResourceCodec<T> codec;

    public StandardPageIterable(
            Iterable<ResourcePageImpl> impl,
            ResourceCodec<T> codec) {

        this.impl = impl;
        this.codec = codec;
    }

    @Override
    public Iterator<ResourcePage<T>> iterator() {
        return new Iterator<ResourcePage<T>>() {

            private final Iterator<ResourcePageImpl> pages = impl.iterator();

            @Override
            public boolean hasNext() {
                return pages.hasNext();
            }

            @Override
            public ResourcePage<T> next() {
                return new StandardResourcePage<T>(pages.next(), codec);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
