package com.amazonaws.resources.foo;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;

public class AmazonFooClient implements AmazonFoo {

    private final AWSCredentialsProvider credentials;
    private final ClientConfiguration configuration;

    public AmazonFooClient(
            AWSCredentialsProvider credentials,
            ClientConfiguration configuration) {

        this.credentials = credentials;
        this.configuration = configuration;
    }

    public AWSCredentialsProvider getCredentials() {
        return credentials;
    }

    public ClientConfiguration getConfiguration() {
        return configuration;
    }
}
