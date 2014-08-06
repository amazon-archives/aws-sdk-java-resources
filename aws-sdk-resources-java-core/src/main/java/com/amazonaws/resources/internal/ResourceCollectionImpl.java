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
package com.amazonaws.resources.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.internal.model.ActionModel;

/**
 * A generic implementation of an arbitrary collection type. Wraps a set of
 * metadata about the collection which describes how to map operations on the
 * collection to service calls.
 */
public final class ResourceCollectionImpl {

    private final ActionContext context;
    private final ActionModel listActionModel;
    private final AmazonWebServiceRequest request;

    public ResourceCollectionImpl(
            ActionContext context,
            ActionModel listActionModel,
            AmazonWebServiceRequest request) {

        this.context = context;
        this.listActionModel = listActionModel;
        this.request = request;
    }

    public Iterator<ResourceImpl> iterator() {
        return new Iterator<ResourceImpl>() {

            private final Iterator<ResourcePageImpl> pages = new PageIterator();
            private Iterator<ResourceImpl> current;

            @Override
            public boolean hasNext() {
                if (current != null && current.hasNext()) {
                    return true;
                }
                getNextPage();
                return (current != null && current.hasNext());
            }

            @Override
            public ResourceImpl next() {
                if (current == null) {
                    getNextPage();
                    if (current == null) {
                        throw new NoSuchElementException();
                    }
                }

                return current.next();
            }

            private void getNextPage() {
                while (pages.hasNext()) {
                    current = pages.next().getResources().iterator();
                    if (current.hasNext()) {
                        return;
                    }
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterable<ResourcePageImpl> pages() {
        return new Iterable<ResourcePageImpl>() {
            @Override
            public Iterator<ResourcePageImpl> iterator() {
                return new PageIterator() ;
            }
        };
    }

    /**
     * @param extractor an optional result extractor
     * @return the first page of resources in this collection
     */
    public ResourcePageImpl firstPage(ResultCapture<Object> extractor) {
        ActionResult result = ActionUtils.perform(
                context, listActionModel, request, extractor);

        return new ResourcePageImpl(
                context,
                listActionModel,
                request,
                result);
    }

    private class PageIterator implements Iterator<ResourcePageImpl> {

        private ResourcePageImpl current;

        @Override
        public boolean hasNext() {
            return (current == null || current.hasNextPage());
        }

        @Override
        public ResourcePageImpl next() {
            if (current == null) {
                current = firstPage(null);
            } else {
                current = current.nextPage(null);
            }
            return current;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
