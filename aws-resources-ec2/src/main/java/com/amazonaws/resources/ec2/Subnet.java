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
import com.amazonaws.services.ec2.model.CreateNetworkInterfaceRequest;
import com.amazonaws.services.ec2.model.CreateNetworkInterfaceResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteSubnetRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The <code>Subnet</code> resource.
 * Each <code>Subnet</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 * </ul>
 */
public interface Subnet {
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
     * @see #load(DescribeSubnetsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeSubnetsRequest
     */
    boolean load(DescribeSubnetsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeSubnetsRequest
     */
    boolean load(DescribeSubnetsRequest request,
            ResultCapture<DescribeSubnetsResult> extractor);

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
     * Gets the value of the CidrBlock attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getCidrBlock();

    /**
     * Gets the value of the MapPublicIpOnLaunch attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    Boolean getMapPublicIpOnLaunch();

    /**
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getState();

    /**
     * Gets the value of the DefaultForAz attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getDefaultForAz();

    /**
     * Gets the value of the AvailableIpAddressCount attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    Integer getAvailableIpAddressCount();

    /**
     * Gets the value of the AvailabilityZone attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getAvailabilityZone();

    /**
     * Gets the value of the VpcId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVpcId();

    /**
     * Retrieves the <code>Vpc</code> resource referenced by this resource.
     */
    Vpc getVpc();

    /**
     * Retrieves the NetworkInterfaces collection referenced by this resource.
     */
    NetworkInterfaceCollection getNetworkInterfaces();

    /**
     * Retrieves the NetworkInterfaces collection referenced by this resource.
     */
    NetworkInterfaceCollection getNetworkInterfaces(
            DescribeNetworkInterfacesRequest request);

    /**
     * Retrieves the Instances collection referenced by this resource.
     */
    InstanceCollection getInstances();

    /**
     * Retrieves the Instances collection referenced by this resource.
     */
    InstanceCollection getInstances(DescribeInstancesRequest request);

    /**
     * Performs the <code>CreateNetworkInterface</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>NetworkInterface</code> resource object associated with
     *         the result of this action.
     * @see CreateNetworkInterfaceRequest
     */
    NetworkInterface createNetworkInterface(CreateNetworkInterfaceRequest
            request);

    /**
     * Performs the <code>CreateNetworkInterface</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>NetworkInterface</code> resource object associated with
     *         the result of this action.
     * @see CreateNetworkInterfaceRequest
     */
    NetworkInterface createNetworkInterface(CreateNetworkInterfaceRequest
            request, ResultCapture<CreateNetworkInterfaceResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSubnetRequest
     */
    void delete(DeleteSubnetRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSubnetRequest
     */
    void delete(DeleteSubnetRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteSubnetRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteSubnetRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources.0</code></b>
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
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources.0</code></b>
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
     * Performs the <code>CreateInstances</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Instance</code> resource objects associated with
     *         the result of this action.
     * @see RunInstancesRequest
     */
    List<Instance> createInstances(RunInstancesRequest request);

    /**
     * Performs the <code>CreateInstances</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Subnet</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SubnetId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return A list of <code>Instance</code> resource objects associated with
     *         the result of this action.
     * @see RunInstancesRequest
     */
    List<Instance> createInstances(RunInstancesRequest request,
            ResultCapture<RunInstancesResult> extractor);
}
