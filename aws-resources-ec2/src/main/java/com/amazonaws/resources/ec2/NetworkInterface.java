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
import com.amazonaws.services.ec2.model.AssignPrivateIpAddressesRequest;
import com.amazonaws.services.ec2.model.AttachNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.AttachNetworkInterfaceResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfaceAttributeRequest
;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfaceAttributeResult;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesResult;
import com.amazonaws.services.ec2.model.DetachNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.ModifyNetworkInterfaceAttributeRequest;
import com.amazonaws.services.ec2.model.NetworkInterfaceAssociation;
import com.amazonaws.services.ec2.model.NetworkInterfaceAttachment;
import com.amazonaws.services.ec2.model.NetworkInterfacePrivateIpAddress;
import com.amazonaws.services.ec2.model.ResetNetworkInterfaceAttributeRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.UnassignPrivateIpAddressesRequest;

/**
 * The <code>NetworkInterface</code> resource.
 * Each <code>NetworkInterface</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 * </ul>
 */
public interface NetworkInterface {
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
     * @see #load(DescribeNetworkInterfacesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeNetworkInterfacesRequest
     */
    boolean load(DescribeNetworkInterfacesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeNetworkInterfacesRequest
     */
    boolean load(DescribeNetworkInterfacesRequest request,
            ResultCapture<DescribeNetworkInterfacesResult> extractor);

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
     * Gets the value of the Attachment attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    NetworkInterfaceAttachment getAttachment();

    /**
     * Gets the value of the SourceDestCheck attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getSourceDestCheck();

    /**
     * Gets the value of the Association attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    NetworkInterfaceAssociation getAssociation();

    /**
     * Gets the value of the OwnerId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getOwnerId();

    /**
     * Gets the value of the AvailabilityZone attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getAvailabilityZone();

    /**
     * Gets the value of the TagSet attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<Tag> getTagSet();

    /**
     * Gets the value of the VpcId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVpcId();

    /**
     * Gets the value of the Groups attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<GroupIdentifier> getGroups();

    /**
     * Gets the value of the Status attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getStatus();

    /**
     * Gets the value of the MacAddress attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getMacAddress();

    /**
     * Gets the value of the PrivateIpAddresses attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    List<NetworkInterfacePrivateIpAddress> getPrivateIpAddresses();

    /**
     * Gets the value of the PrivateDnsName attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPrivateDnsName();

    /**
     * Gets the value of the RequesterId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRequesterId();

    /**
     * Gets the value of the SubnetId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSubnetId();

    /**
     * Gets the value of the RequesterManaged attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getRequesterManaged();

    /**
     * Gets the value of the PrivateIpAddress attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPrivateIpAddress();

    /**
     * Retrieves the <code>Vpc</code> resource referenced by this resource.
     */
    Vpc getVpc();

    /**
     * Retrieves the <code>Subnet</code> resource referenced by this resource.
     */
    Subnet getSubnet();

    /**
     * Performs the <code>UnassignPrivateIpAddresses</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UnassignPrivateIpAddressesRequest
     */
    void unassignPrivateIpAddresses(UnassignPrivateIpAddressesRequest request);

    /**
     * Performs the <code>UnassignPrivateIpAddresses</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see UnassignPrivateIpAddressesRequest
     */
    void unassignPrivateIpAddresses(UnassignPrivateIpAddressesRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>Detach</code> action.
     *
     * <p>
     *
     * @see DetachNetworkInterfaceRequest
     */
    void detach(DetachNetworkInterfaceRequest request);

    /**
     * Performs the <code>Detach</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     *
     * @see DetachNetworkInterfaceRequest
     */
    void detach(DetachNetworkInterfaceRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>ResetAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetNetworkInterfaceAttributeRequest
     */
    void resetAttribute(ResetNetworkInterfaceAttributeRequest request);

    /**
     * Performs the <code>ResetAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetNetworkInterfaceAttributeRequest
     */
    void resetAttribute(ResetNetworkInterfaceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyNetworkInterfaceAttributeRequest
     */
    void modifyAttribute(ModifyNetworkInterfaceAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyNetworkInterfaceAttributeRequest
     */
    void modifyAttribute(ModifyNetworkInterfaceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeNetworkInterfaceAttributeRequest
     */
    DescribeNetworkInterfaceAttributeResult describeAttribute(
            DescribeNetworkInterfaceAttributeRequest request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeNetworkInterfaceAttributeRequest
     */
    DescribeNetworkInterfaceAttributeResult describeAttribute(
            DescribeNetworkInterfaceAttributeRequest request,
            ResultCapture<DescribeNetworkInterfaceAttributeResult> extractor);

    /**
     * Performs the <code>Attach</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AttachNetworkInterfaceRequest
     */
    AttachNetworkInterfaceResult attach(AttachNetworkInterfaceRequest request);

    /**
     * Performs the <code>Attach</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AttachNetworkInterfaceRequest
     */
    AttachNetworkInterfaceResult attach(AttachNetworkInterfaceRequest request,
            ResultCapture<AttachNetworkInterfaceResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkInterfaceRequest
     */
    void delete(DeleteNetworkInterfaceRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkInterfaceRequest
     */
    void delete(DeleteNetworkInterfaceRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>AssignPrivateIpAddresses</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AssignPrivateIpAddressesRequest
     */
    void assignPrivateIpAddresses(AssignPrivateIpAddressesRequest request);

    /**
     * Performs the <code>AssignPrivateIpAddresses</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkInterfaceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see AssignPrivateIpAddressesRequest
     */
    void assignPrivateIpAddresses(AssignPrivateIpAddressesRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[*]</code></b>
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
     * <code>NetworkInterface</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[*]</code></b>
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
