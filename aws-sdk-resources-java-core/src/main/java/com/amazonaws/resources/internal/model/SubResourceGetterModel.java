package com.amazonaws.resources.internal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model definition for a subresource getter. Subresources by definition
 * can be accessed directly from their parent resource by providing the value
 * for one additional identifier.
 * <pre>
 *   Bucket bucket = s3.getBucket("the-name-of-my-bucket");
 *   S3Object object = bucket.getObject("the-name-of-my-object");
 * </pre>
 * A getter takes exactly one parameter, which is mapped into the identifiers
 * of the resource that's returned. The returned resource may additionally map
 * in identifiers of the resource on which the method is called (for
 * example, {@code Bucket.getObject(String key)} also maps in the BucketName
 * from the Bucket it's called on).
 */
public class SubResourceGetterModel {

    private final String parameterType;
    private final FlatMapping parameterMapping;
    private final List<FlatMapping> identifierMappings;

    @JsonCreator
    public SubResourceGetterModel(
            @JsonProperty(value="ParameterType", required=false)
            String parameterType,
            @JsonProperty(value="ParameterMapping", required=false)
            FlatMapping parameterMapping,
            @JsonProperty(value="IdentifierMappings", required=false)
            List<FlatMapping> identifierMappings) {

        this.parameterType = parameterType;
        this.parameterMapping = parameterMapping;
        this.identifierMappings = Utils.makeImmutable(identifierMappings);
    }

    /**
     * @return the type of the parameter
     */
    @JsonProperty(value="ParameterType")
    public String getParameterType() {
        return parameterType;
    }

    /**
     * @return the parameter mapping for this indexer
     */
    @JsonProperty(value="ParameterMapping")
    public FlatMapping getParameterMapping() {
        return parameterMapping;
    }

    /**
     * @return the identifier mappings for this indexer
     */
    @JsonProperty(value="IdentifierMappings")
    public List<FlatMapping> getIdentifierMappings() {
        return identifierMappings;
    }

    @Override
    public String toString() {
        return "{parameterType=" + parameterType
                + ", parameterMapping=" + parameterMapping
                + ", identifierMappings=" + identifierMappings
                + "}";
    }
}
