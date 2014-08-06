package com.amazonaws.resources;

/**
 * Base interface for all Service types, compile-time binding them to the
 * appropriate client interface.
 *
 * @param <C> the low-level client interface for this service
 */
public interface Service<C> {
    /**
     * Returns the low-level SDK client this service uses.
     *
     * @returns the low-level SDK client this service uses
     */
    C client();
}
