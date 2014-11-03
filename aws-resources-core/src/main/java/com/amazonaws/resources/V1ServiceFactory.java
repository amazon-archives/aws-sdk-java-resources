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
package com.amazonaws.resources;

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

        this.model = V1ModelLoader.load(interfaceType, annotation.model());
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
