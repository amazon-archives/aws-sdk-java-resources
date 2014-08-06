package com.amazonaws.resources.internal;

import java.util.List;

/**
 * The result of an action. Serves as a generic wrapper for a *Result type from
 * the low-level API, with a set of resources extracted for easy consumption.
 */
public final class ActionResult {

    private final Object data;
    private final List<ResourceImpl> resources;
    private final Object token;

    public ActionResult(
            Object data,
            List<ResourceImpl> resources,
            Object token) {

        this.data = data;
        this.resources = resources;
        this.token = token;
    }

    /**
     * @return the data element of this response
     */
    public Object getData() {
        return data;
    }

    /**
     * @return the list of resources for this response
     */
    public List<ResourceImpl> getResources() {
        return resources;
    }

    /**
     * @return the (single) resource for this response
     */
    public ResourceImpl getResource() {
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        if (resources.size() > 1) {
            throw new IllegalStateException(
                    "This result has more than one resource");
        }
        return resources.get(0);
    }

    /**
     * @return the 'next page' token associated with this response
     */
    public Object getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "{data=" + data
                + ", resources=" + resources
                + ", token=" + token
                + "}";
    }
}
