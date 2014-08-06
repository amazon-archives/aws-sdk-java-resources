package com.amazonaws.resources;

import com.amazonaws.resources.internal.CustomServiceInterface;
import com.amazonaws.resources.internal.ReflectionUtils;

final class CustomServiceFactory<C, T extends Service<C>>
        implements ServiceFactory<C, T> {

    private final Class<T> interfaceType;
    private final CustomServiceInterface annotation;

    public CustomServiceFactory(
            Class<T> interfaceType,
            CustomServiceInterface annotation) {

        this.interfaceType = interfaceType;
        this.annotation = annotation;
    }

    @Override
    public Class<? extends C> getClientImplType() {
        return ReflectionUtils.loadClass(interfaceType, annotation.client());
    }

    @Override
    public T create(C client) {
        Class<? extends T> implType = ReflectionUtils.loadClass(
                interfaceType, annotation.impl());

        return ReflectionUtils.newInstance(implType, client);
    }
}
