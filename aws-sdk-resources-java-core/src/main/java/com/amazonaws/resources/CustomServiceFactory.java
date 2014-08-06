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
