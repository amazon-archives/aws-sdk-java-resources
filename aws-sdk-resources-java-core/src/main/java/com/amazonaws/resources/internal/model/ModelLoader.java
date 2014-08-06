package com.amazonaws.resources.internal.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for loading a service model, dealing with the version number
 * attributes in the model file and parsing the service model as an instance
 * of the appropriate model class.
 */
public final class ModelLoader {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final int LATEST_V1_MINOR_VERSION = 0;

    private final int modelVersion;
    private final ServiceModel v1Model;

    /**
     * Loads a model from a file on disk.
     */
    public static ModelLoader load(File file) throws IOException {
        return load(new FileInputStream(file));
    }

    /**
     * Loads a model from an arbitrary {@code InputStream}.
     */
    public static ModelLoader load(InputStream stream) throws IOException {
        JsonNode tree = MAPPER.readTree(stream);
        JsonNode version = tree.get("FormatVersion");
        if (version == null || !version.isObject()) {
            throw new IllegalStateException("No FormatVersion found");
        }

        JsonNode major = version.get("Major");
        if (major == null || !major.isNumber()) {
            throw new IllegalStateException("No Major version found");
        }

        int majorVersion = major.asInt();

        switch (majorVersion) {
        case 1:
            return new ModelLoader(loadV1Model(tree, version));

        default:
            throw new IllegalStateException(
                    "Unknown major version " + majorVersion + ". Perhaps "
                    + "you need a newer version of the resources runtime?");
        }
    }

    private ModelLoader(ServiceModel v1Model) {
        this.modelVersion = 1;
        this.v1Model = v1Model;
    }

    public int getModelVersion() {
        return modelVersion;
    }

    public ServiceModel getV1Model() {
        return v1Model;
    }

    private static ServiceModel loadV1Model(JsonNode tree, JsonNode version)
            throws JsonProcessingException {

        JsonNode minor = version.get("Minor");
        if (minor == null || !minor.isNumber()) {
            throw new IllegalStateException("No Minor version found");
        }
        if (minor.asInt() < 0) {
            throw new IllegalStateException("Invalid minor version: "
                    + minor.asInt());
        }

        if (minor.asInt() > LATEST_V1_MINOR_VERSION) {
            throw new IllegalStateException(
                    "Unknown minor version " + minor.asInt() + ". Perhaps "
                    + "you need a newer version of the resources runtime?");
        }

        JsonNode service = tree.get("Service");
        if (service == null) {
            throw new IllegalStateException("No Service found");
        }

        return MAPPER.treeToValue(service, ServiceModel.class);
    }
}
