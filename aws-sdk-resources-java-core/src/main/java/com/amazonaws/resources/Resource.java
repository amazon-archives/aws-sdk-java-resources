package com.amazonaws.resources;

/**
 * Base interface for all Resource types, compile-time binding them to the
 * appropriate client interface.
 *
 * @param <C> the low-level client interface for this resource
 */
public interface Resource<C> {
    /**
     * Returns the low-level SDK client this resource uses.
     *
     * @returns the low-level SDK client this resource uses
     */
    C client();
}
