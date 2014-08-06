/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.resources;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.resources.internal.CustomServiceInterface;
import com.amazonaws.resources.internal.ReflectionUtils;
import com.amazonaws.resources.internal.V1ServiceInterface;

/**
 * A fluent builder for Service objects.
 *
 * @param <C> the type of the low-level client the service will wrap
 * @param <T> the type of the service to be built
 */
public final class ServiceBuilder<C, T extends Service<C>> {

    private final ServiceFactory<C, T> factory;

    private C client;

    private ClientConfiguration configuration = new ClientConfiguration();
    private AWSCredentialsProvider credentials =
            new DefaultAWSCredentialsProviderChain();

    private Region region;
    private String endpoint;

    /**
     * Creates a new {@code ServiceBuilder} for the given service interface
     * type.
     *
     * @param serviceType the service interface type
     * @return the newly created builder
     */
    public static <C, T extends Service<C>> ServiceBuilder<C, T> forService(
            Class<T> serviceType) {

        if (serviceType == null) {
            throw new NullPointerException("serviceType");
        }

        V1ServiceInterface v1annotation =
                serviceType.getAnnotation(V1ServiceInterface.class);

        if (v1annotation != null) {
            return new ServiceBuilder<>(
                    new V1ServiceFactory<>(serviceType, v1annotation));
        }

        CustomServiceInterface customAnnotation =
                serviceType.getAnnotation(CustomServiceInterface.class);

        if (customAnnotation != null) {
            return new ServiceBuilder<>(
                    new CustomServiceFactory<>(serviceType, customAnnotation));
        }

        throw new IllegalArgumentException(
                "Service interfaces must be decorated with an "
                + "@[Type]ServiceInterface annotation.");
    }


    private ServiceBuilder(ServiceFactory<C, T> factory) {
        this.factory = factory;
    }

    /**
     * @return the client that the service being built will wrap
     */
    public C getClient() {
        return client;
    }

    /**
     * @param client the client that the service being built will wrap
     */
    public void setClient(C client) {
        this.client = client;
    }

    /**
     * @param client the client that the service being built will wrap
     * @return this object, for method chaining
     */
    public ServiceBuilder<C, T> withClient(C client) {
        this.client = client;
        return this;
    }


    /**
     * @return the credentials this service will use to sign requests
     */
    public AWSCredentialsProvider getCredentials() {
        return credentials;
    }

    /**
     * @param credentials the credentials this service will use to sign requests
     */
    public void setCredentials(AWSCredentialsProvider credentials) {
        this.credentials = credentials;
    }

    /**
     * @param credentials the credentials this service will use to sign requests
     * @return this object, for method chaining
     */
    public ServiceBuilder<C, T> withCredentials(
            AWSCredentialsProvider credentials) {

        setCredentials(credentials);
        return this;
    }


    /**
     * @return the client configuration for the service being built
     */
    public ClientConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * @param configuration the client configuration for the service being built
     */
    public void setConfiguration(ClientConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * @param configuration the client configuration for the service being built
     * @return this object, for method chaining
     */
    public ServiceBuilder<C, T> withConfiguration(
            ClientConfiguration configuration) {
        setConfiguration(configuration);
        return this;
    }


    /**
     * @return the endpoint for this client
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint for this client
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @param endpoint the endpoint for this client
     * @return this object, for method chaining
     */
    public ServiceBuilder<C, T> withEndpoint(String endpoint) {
        setEndpoint(endpoint);
        return this;
    }


    /**
     * @return the region for the service instance being built
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @param region the region for the service instance being built
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * @param region the region for the service instance being built
     * @return this object, for method chaining
     */
    public ServiceBuilder<C, T> withRegion(Region region) {
        setRegion(region);
        return this;
    }

    /**
     * Builds a new service object with the given parameters.
     *
     * @return the newly-built service object
     */
    public T build() {
        C clientObject = client;
        if (clientObject == null) {
            clientObject = createClient();
        }

        return factory.create(clientObject);
    }

    private C createClient() {
        Class<? extends C> clientImplType = factory.getClientImplType();
        C client = ReflectionUtils.newInstance(
                clientImplType, credentials, configuration);

        if (client instanceof AmazonWebServiceClient) {
            AmazonWebServiceClient awsc = (AmazonWebServiceClient) client;
            if (region != null) {
                awsc.setRegion(region);
            }
            if (endpoint != null) {
                awsc.setEndpoint(endpoint);
            }
        }

        return client;
    }
}
