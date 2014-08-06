package com.amazonaws.resources.foo;

import com.amazonaws.resources.Service;
import com.amazonaws.resources.internal.V1ServiceInterface;

@V1ServiceInterface(
        model="model.json",
        impl="com.amazonaws.resources.foo.FooImpl")
public interface Foo extends Service<AmazonFoo> {

}
