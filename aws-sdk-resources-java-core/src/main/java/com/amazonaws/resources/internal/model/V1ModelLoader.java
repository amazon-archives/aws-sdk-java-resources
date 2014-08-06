package com.amazonaws.resources.internal.model;

import java.io.IOException;
import java.io.InputStream;

public final class V1ModelLoader {

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
