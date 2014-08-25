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
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.ModifyImageAttributeRequest;
import com.amazonaws.services.ec2.model.ProductCode;
import com.amazonaws.services.ec2.model.ResetImageAttributeRequest;
import com.amazonaws.services.ec2.model.StateReason;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The Image resource.
 */
public interface Image {
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
     * @see #load(DescribeImagesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeImagesRequest
     */
    boolean load(DescribeImagesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeImagesRequest
     */
    boolean load(DescribeImagesRequest request,
            ResultCapture<DescribeImagesResult> extractor);

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the Description attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getDescription();

    /**
     * Gets the value of the RootDeviceType attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRootDeviceType();

    /**
     * Gets the value of the ImageType attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getImageType();

    /**
     * Gets the value of the KernelId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getKernelId();

    /**
     * Gets the value of the StateReason attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    StateReason getStateReason();

    /**
     * Gets the value of the OwnerId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getOwnerId();

    /**
     * Gets the value of the ProductCodes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<ProductCode> getProductCodes();

    /**
     * Gets the value of the SriovNetSupport attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSriovNetSupport();

    /**
     * Gets the value of the Architecture attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getArchitecture();

    /**
     * Gets the value of the ImageOwnerAlias attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getImageOwnerAlias();

    /**
     * Gets the value of the ImageLocation attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getImageLocation();

    /**
     * Gets the value of the Name attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    String getName();

    /**
     * Gets the value of the Tags attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    List<Tag> getTags();

    /**
     * Gets the value of the RootDeviceName attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRootDeviceName();

    /**
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getState();

    /**
     * Gets the value of the RamdiskId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRamdiskId();

    /**
     * Gets the value of the Hypervisor attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getHypervisor();

    /**
     * Gets the value of the Platform attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPlatform();

    /**
     * Gets the value of the VirtualizationType attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getVirtualizationType();

    /**
     * Gets the value of the BlockDeviceMappings attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    List<BlockDeviceMapping> getBlockDeviceMappings();

    /**
     * Gets the value of the Public attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getPublic();

    /**
     * Performs the <code>Deregister</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeregisterImageRequest
     */
    void deregister(DeregisterImageRequest request);

    /**
     * Performs the <code>Deregister</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeregisterImageRequest
     */
    void deregister(DeregisterImageRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>Deregister</code> action.
     *
     * @see #deregister(DeregisterImageRequest)
     */
    void deregister();

    /**
     * The convenient method form for the <code>Deregister</code> action.
     *
     * @see #deregister(DeregisterImageRequest, ResultCapture)
     */
    void deregister(ResultCapture<Void> extractor);

    /**
     * Performs the <code>ResetAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetImageAttributeRequest
     */
    void resetAttribute(ResetImageAttributeRequest request);

    /**
     * Performs the <code>ResetAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetImageAttributeRequest
     */
    void resetAttribute(ResetImageAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetImageAttributeRequest)
     */
    void resetAttribute(String attribute);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetImageAttributeRequest, ResultCapture)
     */
    void resetAttribute(String attribute, ResultCapture<Void> extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyImageAttributeRequest
     */
    void modifyAttribute(ModifyImageAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyImageAttributeRequest
     */
    void modifyAttribute(ModifyImageAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifyImageAttributeRequest)
     */
    void modifyAttribute(String attribute);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifyImageAttributeRequest, ResultCapture)
     */
    void modifyAttribute(String attribute, ResultCapture<Void> extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeImageAttributeRequest
     */
    DescribeImageAttributeResult describeAttribute(DescribeImageAttributeRequest
            request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>ImageId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeImageAttributeRequest
     */
    DescribeImageAttributeResult describeAttribute(DescribeImageAttributeRequest
            request, ResultCapture<DescribeImageAttributeResult> extractor);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeImageAttributeRequest)
     */
    DescribeImageAttributeResult describeAttribute(String attribute);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeImageAttributeRequest, ResultCapture)
     */
    DescribeImageAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeImageAttributeResult> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
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
     * <code>Image</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
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
}
