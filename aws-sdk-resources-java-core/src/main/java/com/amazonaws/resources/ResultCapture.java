package com.amazonaws.resources;

import java.util.Collections;
import java.util.Map;

/**
 * An extractor object that can optionally be passed to action methods on a
 * resource to collect the request id and raw client-level result received
 * from the service.
 *
 * @param <T> the type of the raw result object
 */
public final class ResultCapture<T> {

    private Map<String, String> responseMetadata;
    private T clientResult;

    public Map<String, String> getResponseMetadata() {
        return responseMetadata;
    }

    /**
     * @return the request id assigned by the service
     */
    public String getRequestId() {
        if (responseMetadata == null) {
            return null;
        }
        return responseMetadata.get("RequestId");
    }

    /**
     * @return the raw client-level result returned by the service
     */
    public T getClientResult() {
        return clientResult;
    }

    public void setResponseMetadata(Map<String, String> value) {
        if (value == null) {
            responseMetadata = null;
        } else {
            responseMetadata = Collections.unmodifiableMap(value);
        }
    }

    public void setClientResult(T value) {
        clientResult = value;
    }

    @Override
    public String toString() {
        return "{reponseMetadata=" + responseMetadata
                + ", clientResult=" + clientResult
                + "}";
    }
}
