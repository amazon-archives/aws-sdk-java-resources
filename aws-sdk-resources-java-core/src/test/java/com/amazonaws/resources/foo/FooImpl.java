package com.amazonaws.resources.foo;

import com.amazonaws.resources.internal.ServiceImpl;

public class FooImpl implements Foo {

    private final ServiceImpl<AmazonFoo> service;

    public FooImpl(ServiceImpl<AmazonFoo> service) {
        this.service = service;
    }

    @Override
    public AmazonFoo client() {
        return service.getClient();
    }
}
