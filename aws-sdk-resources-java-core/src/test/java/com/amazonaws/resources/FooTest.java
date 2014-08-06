package com.amazonaws.resources;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.resources.foo.AmazonFooClient;
import com.amazonaws.resources.foo.Foo;

public class FooTest {
    @Test
    public void testFooService() {
        Foo foo = ServiceBuilder.forService(Foo.class).build();

        Assert.assertNotNull(foo.client());
        Assert.assertTrue(foo.client() instanceof AmazonFooClient);
    }
}
