package com.amazonaws.resources.internal;

public interface ResourceCodec<T> {
    T transform(ResourceImpl resource);
}