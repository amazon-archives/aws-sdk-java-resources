package com.amazonaws.resources;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.resources.internal.ReflectionUtils;
import com.amazonaws.resources.internal.ServiceImpl;
import com.amazonaws.resources.internal.V1ServiceInterface;
import com.amazonaws.resources.internal.model.ServiceModel;
import com.amazonaws.resources.internal.model.V1ModelLoader;

/**
 * @param <C> client interface type
 * @param <T> service interface type
 */
class V1ServiceFactory<C,T extends Service<C>> implements ServiceFactory<C,T> {

    private final Class<T> interfaceType;
    private final V1ServiceInterface annotation;
    private final ServiceModel model;

    public V1ServiceFactory(
            Class<T> interfaceType,
            V1ServiceInterface annotation) {

        this.interfaceType = interfaceType;
        this.annotation = annotation;

        try (InputStream stream =
                interfaceType.getResourceAsStream(annotation.model())) {

            this.model = V1ModelLoader.load(stream);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to load service model " + annotation.model(),
                    exception);
        }
    }

    @Override
    public Class<? extends C> getClientImplType() {
        return ReflectionUtils.loadClass(interfaceType,
                model.getClientImplementation());
    }

    @Override
    public T create(C client) {
        ServiceImpl<C> impl = new ServiceImpl<>(model, client);
        Class<? extends T> implType = ReflectionUtils.loadClass(
                interfaceType, annotation.impl());
        return ReflectionUtils.newInstance(implType, impl);
    }
}
