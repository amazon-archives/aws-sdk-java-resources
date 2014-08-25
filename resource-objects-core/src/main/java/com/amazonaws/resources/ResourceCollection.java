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

import java.util.Iterator;

/**
 * A collection of resources.
 *
 * @param <T> the type of resource contained in this collection
 */
public interface ResourceCollection<T> extends Iterable<T> {
    /**
     * Returns an {@code Iterator} which will lazily paginate through the
     * entire collection, potentially making multiple service calls as the
     * iterator is used.
     * <p>
     * This method will not block or throw an exception, but calls to
     * {@code hasNext} and {@code next} on the returned {@code Iterator}
     * may.
     * <p>
     * The code:
     * <pre>
     * for (T resource : collection) {
     *     handle(resource);
     * }
     * </pre>
     * Is precisely equivalent to:
     * <pre>
     * for (ResourcePage<T> page : collection.pages()) {
     *     for (T resource : page) {
     *         handle(resource);
     *     }
     * }
     * </pre>
     *
     * @return a lazy {@code Iterator} of the resources in this collection
     */
    Iterator<T> iterator();

    /**
     * Returns a view of this collection as a set of <em>pages</em> rather
     * than a set of resources. While iterating through this view, each call
     * to {@code Iterator.next()} will make exactly one call to the service
     * and return the full page of results.
     * <p>
     * <pre>
     * for (ResourcePage<?> page : collection.pages()) {
     *     System.out.println("Got " + page.size() + " results.");
     * }
     * </pre>
     *
     * @return an {@code Iterable} collection of resource pages
     */
    Iterable<ResourcePage<T>> pages();

    /**
     * Makes exactly one service call to retrieve the first page of
     * results from this collection. Further pages may be retrieved by
     * invoking {@code ResourcePage.nextPage()} on the returned value.
     *
     * @return the first page of resources in this collection
     */
    ResourcePage<T> firstPage();

    /**
     * Makes exactly one service call to retrieve the first page of
     * results from this collection. Further pages may be retrieved by
     * invoking {@code ResourcePage.nextPage()} on the returned value. Also
     * extracts the request id and raw client-level result object for
     * examination.
     *
     * @param extractor a result extractor object
     * @return the first page of resources in this collection
     */
    ResourcePage<T> firstPage(ResultCapture<Object> extractor);
}
