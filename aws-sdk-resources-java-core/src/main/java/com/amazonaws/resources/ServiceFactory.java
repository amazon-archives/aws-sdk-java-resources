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

interface ServiceFactory<C, T extends Service<C>> {
    /**
     * @return the default client implementation type for this service
     */
    Class<? extends C> getClientImplType();

    /**
     * Creates a new Service object wrapping the given client.
     *
     * @param client the client to wrap
     * @return the newly created service
     */
    T create(C client);
}
