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

import java.util.Date;
import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.services.ec2.model.AcceptVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.AcceptVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.DeleteVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.DeleteVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.DescribeVpcPeeringConnectionsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcPeeringConnectionsResult;
import com.amazonaws.services.ec2.model.RejectVpcPeeringConnectionRequest;
import com.amazonaws.services.ec2.model.RejectVpcPeeringConnectionResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.VpcPeeringConnectionStateReason;
import com.amazonaws.services.ec2.model.VpcPeeringConnectionVpcInfo;

/**
 * The VpcPeeringConnection resource.
 */
public interface VpcPeeringConnection {
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
     * @see #load(DescribeVpcPeeringConnectionsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVpcPeeringConnectionsRequest
     */
    boolean load(DescribeVpcPeeringConnectionsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVpcPeeringConnectionsRequest
     */
    boolean load(DescribeVpcPeeringConnectionsRequest request,
            ResultCapture<DescribeVpcPeeringConnectionsResult> extractor);

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
     * Gets the value of the Status attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    VpcPeeringConnectionStateReason getStatus();

    /**
     * Gets the value of the AccepterVpcInfo attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    VpcPeeringConnectionVpcInfo getAccepterVpcInfo();

    /**
     * Gets the value of the RequesterVpcInfo attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    VpcPeeringConnectionVpcInfo getRequesterVpcInfo();

    /**
     * Gets the value of the ExpirationTime attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getExpirationTime();

    /**
     * Retrieves the <code>RequesterVpc</code> resource referenced by this
     * resource.
     */
    Vpc getRequesterVpc();

    /**
     * Retrieves the <code>AccepterVpc</code> resource referenced by this
     * resource.
     */
    Vpc getAccepterVpc();

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DeleteVpcPeeringConnectionRequest
     */
    DeleteVpcPeeringConnectionResult delete(DeleteVpcPeeringConnectionRequest
            request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DeleteVpcPeeringConnectionRequest
     */
    DeleteVpcPeeringConnectionResult delete(DeleteVpcPeeringConnectionRequest
            request, ResultCapture<DeleteVpcPeeringConnectionResult> extractor);

    /**
     * Performs the <code>Reject</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see RejectVpcPeeringConnectionRequest
     */
    RejectVpcPeeringConnectionResult reject(RejectVpcPeeringConnectionRequest
            request);

    /**
     * Performs the <code>Reject</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see RejectVpcPeeringConnectionRequest
     */
    RejectVpcPeeringConnectionResult reject(RejectVpcPeeringConnectionRequest
            request, ResultCapture<RejectVpcPeeringConnectionResult> extractor);

    /**
     * Performs the <code>Accept</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AcceptVpcPeeringConnectionRequest
     */
    AcceptVpcPeeringConnectionResult accept(AcceptVpcPeeringConnectionRequest
            request);

    /**
     * Performs the <code>Accept</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>VpcPeeringConnection</code> resource, and any conflicting parameter
     * value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VpcPeeringConnectionId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AcceptVpcPeeringConnectionRequest
     */
    AcceptVpcPeeringConnectionResult accept(AcceptVpcPeeringConnectionRequest
            request, ResultCapture<AcceptVpcPeeringConnectionResult> extractor);
}
