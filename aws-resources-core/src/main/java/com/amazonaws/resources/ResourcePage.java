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

import java.util.List;

import com.amazonaws.AmazonServiceException;

/**
 * A page is-a (immutable) List of resources; accessing methods on the list are
 * guaranteed to be purely in-memory operations that will not block or throw
 * exceptions because of transient network issues. A page also knows whether it
 * has a "next page" of resources, and if so knows how to retrieve it (which
 * will almost certainly involve a remote network call that may block or
 * fail).
 *
 * @param <T> the type of resource contained in this page
 */
public interface ResourcePage<T> extends List<T> {
    /**
     * Checks whether this page has a "next page." If this method returns
     * true, the next page can be retrieved by calling {@code next}. If it
     * returns false, any call to {@code next} will be guaranteed to throw an
     * {@code IllegalStateException}.
     *
     * @return true if there is a next page of results
     */
    boolean hasNextPage();

    /**
     * Retrieves the next page of results.
     *
     * @return the next page of results
     * @throws IllegalStateException if there is no next page
     * @throws AmazonServiceException on error making the remote call
     */
    ResourcePage<T> nextPage();

    /**
     * Retrieves the next page of results, extracting the request id and
     * raw result object into the given context object.
     *
     * @param extractor a result extractor object
     * @return the next page of results
     * @throws IllegalStateException if there is no next page
     * @throws AmazonServiceException on error making the remote call
     */
    ResourcePage<T> nextPage(ResultCapture<Object> extractor);
}
