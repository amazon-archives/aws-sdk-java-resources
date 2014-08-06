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
