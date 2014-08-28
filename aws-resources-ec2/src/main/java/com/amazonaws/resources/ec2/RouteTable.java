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
import com.amazonaws.services.ec2.model.AssociateRouteTableRequest;
import com.amazonaws.services.ec2.model.AssociateRouteTableResult;
import com.amazonaws.services.ec2.model.CreateRouteRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.PropagatingVgw;
import com.amazonaws.services.ec2.model.Route;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The <code>RouteTable</code> resource.
 * Each <code>RouteTable</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 * </ul>
 */
public interface RouteTable {
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
     * @see #load(DescribeRouteTablesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeRouteTablesRequest
     */
    boolean load(DescribeRouteTablesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeRouteTablesRequest
     */
    boolean load(DescribeRouteTablesRequest request,
            ResultCapture<DescribeRouteTablesResult> extractor);

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
     * Gets the value of the PropagatingVgws attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<PropagatingVgw> getPropagatingVgws();

    /**
     * Gets the value of the Routes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<Route> getRoutes();

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
     * Retrieves the Associations collection referenced by this resource.
     */
    RouteTableAssociationCollection getAssociations();

    /**
     * Retrieves the Associations collection referenced by this resource.
     */
    RouteTableAssociationCollection getAssociations(DescribeRouteTablesRequest
            request);

    /**
     * Performs the <code>AssociateWithSubnet</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTableAssociation</code> resource object associated
     *         with the result of this action.
     * @see AssociateRouteTableRequest
     */
    RouteTableAssociation associateWithSubnet(AssociateRouteTableRequest request
            );

    /**
     * Performs the <code>AssociateWithSubnet</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTableAssociation</code> resource object associated
     *         with the result of this action.
     * @see AssociateRouteTableRequest
     */
    RouteTableAssociation associateWithSubnet(AssociateRouteTableRequest request
            , ResultCapture<AssociateRouteTableResult> extractor);

    /**
     * Performs the <code>CreateRoute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see CreateRouteRequest
     */
    void createRoute(CreateRouteRequest request);

    /**
     * Performs the <code>CreateRoute</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>RouteTableId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see CreateRouteRequest
     */
    void createRoute(CreateRouteRequest request, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
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
     * <code>RouteTable</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
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
