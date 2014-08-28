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
package com.amazonaws.resources.ec2;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.ec2.model.DeleteTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsResult;

/**
 * The <code>Tag</code> resource.
 * Each <code>Tag</code> object is uniquely identified by these identifier(s):
 * <ul>
 *   <li>Value</li>
 *   <li>Key</li>
 *   <li>ResourceId</li>
 * </ul>
 */
public interface Tag {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(DescribeTagsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Tag</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Filters[0].Values[*]</code></b>
     *         - mapped from the <code>Key</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Filters[1].Values[*]</code></b>
     *         - mapped from the <code>Value</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Filters[0].Name</code></b>
     *         - constant value <code>key</code>.
     *   </li>
     *   <li>
     *     <b><code>Filters[1].Name</code></b>
     *         - constant value <code>value</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeTagsRequest
     */
    boolean load(DescribeTagsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Tag</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Filters[0].Values[*]</code></b>
     *         - mapped from the <code>Key</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Filters[1].Values[*]</code></b>
     *         - mapped from the <code>Value</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Filters[0].Name</code></b>
     *         - constant value <code>key</code>.
     *   </li>
     *   <li>
     *     <b><code>Filters[1].Name</code></b>
     *         - constant value <code>value</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeTagsRequest
     */
    boolean load(DescribeTagsRequest request, ResultCapture<DescribeTagsResult>
            extractor);

    /**
     * Gets the value of the Value identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getValue();

    /**
     * Gets the value of the Key identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getKey();

    /**
     * Gets the value of the ResourceId identifier. This method always directly
     * returns the identifier and never involves a service call.
     */
    String getResourceId();

    /**
     * Gets the value of the ResourceType attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getResourceType();

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Tag</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[*]</code></b>
     *         - mapped from the <code>ResourceId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Tags[0].Key</code></b>
     *         - mapped from the <code>Key</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Tags[0].Value</code></b>
     *         - mapped from the <code>Value</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteTagsRequest
     */
    void delete(DeleteTagsRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Tag</code> resource, and any conflicting parameter value set in the
     * request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[*]</code></b>
     *         - mapped from the <code>ResourceId</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Tags[0].Key</code></b>
     *         - mapped from the <code>Key</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Tags[0].Value</code></b>
     *         - mapped from the <code>Value</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteTagsRequest
     */
    void delete(DeleteTagsRequest request, ResultCapture<Void> extractor);
}
