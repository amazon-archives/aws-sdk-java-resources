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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DetachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.InternetGatewayAttachment;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The InternetGateway resource.
 */
public interface InternetGateway {
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
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see #load(DescribeInternetGatewaysRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeInternetGatewaysRequest
     */
    boolean load(DescribeInternetGatewaysRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeInternetGatewaysRequest
     */
    boolean load(DescribeInternetGatewaysRequest request,
            ResultCapture<DescribeInternetGatewaysResult> extractor);

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the Tags attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    List<Tag> getTags();

    /**
     * Gets the value of the Attachments attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<InternetGatewayAttachment> getAttachments();

    /**
     * Performs the <code>DetachFromVpc</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DetachInternetGatewayRequest
     */
    void detachFromVpc(DetachInternetGatewayRequest request);

    /**
     * Performs the <code>DetachFromVpc</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DetachInternetGatewayRequest
     */
    void detachFromVpc(DetachInternetGatewayRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteInternetGatewayRequest
     */
    void delete(DeleteInternetGatewayRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteInternetGatewayRequest
     */
    void delete(DeleteInternetGatewayRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest request);

    /**
     * Performs the <code>CreateTags</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Tag</code> resource objects associated with the
     *         result of this action.
     * @see CreateTagsRequest
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest)
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags);

    /**
     * The convenient method form for the <code>CreateTags</code> action.
     *
     * @see #createTags(CreateTagsRequest, ResultCapture)
     */
    List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>AttachToVpc</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AttachInternetGatewayRequest
     */
    void attachToVpc(AttachInternetGatewayRequest request);

    /**
     * Performs the <code>AttachToVpc</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>InternetGateway</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InternetGatewayId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AttachInternetGatewayRequest
     */
    void attachToVpc(AttachInternetGatewayRequest request, ResultCapture<Void>
            extractor);
}
