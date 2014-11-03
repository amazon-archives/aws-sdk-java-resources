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
package com.amazonaws.resources.internal.model;

import java.io.IOException;
import java.io.InputStream;

public final class V1ModelLoader {

    public static ServiceModel load(Class<?> interfaceType, String filePath) {
        try (InputStream stream =
                interfaceType.getResourceAsStream(filePath)) {

            return load(stream);

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Unable to load service model " + filePath,
                    exception);
        }
    }

    public static ServiceModel load(InputStream stream) {
        try {

            ModelLoader loader = ModelLoader.load(stream);
            if (loader.getModelVersion() != 1) {
                throw new IllegalStateException(
                        "Expected a V1 service model, but got "
                        + loader.getModelVersion() + " instead.");
            }

            return loader.getV1Model();

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "Error loading service model: " + exception.getMessage(),
                    exception);
        }
    }

    private V1ModelLoader() {
    }
}
