package com.amazonaws.resources.internal;

import com.amazonaws.resources.internal.model.ServiceModel;

/**
 * Abstraction of the context for calling an action - typically either a
 * resource instance or a top-level service object.
 */
public interface ActionContext {

    /**
     * @return the model for the service this object belongs to
     */
    ServiceModel getServiceModel();

    /**
     * @return the client object
     */
    Object getClient();

    /**
     * @return true if this context object has identifiers or attributes
     */
    boolean hasState();

    /**
     * @param name the name of the identifier to get
     * @return the value of the given identifier for this context
     * @throws UnsupportedOperationException if this context has no state
     */
    Object getIdentifier(String name);

    /**
     * @param name the name of the attribute to get
     * @return the value of the given attribute for this context
     * @throws UnsupportedOperationException if this context has no state
     */
    Object getAttribute(String name);
}
