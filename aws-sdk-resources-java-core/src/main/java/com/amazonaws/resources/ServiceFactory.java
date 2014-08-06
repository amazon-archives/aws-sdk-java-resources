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
