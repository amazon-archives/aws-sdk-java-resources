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
import com.amazonaws.services.ec2.model.DisassociateRouteTableRequest;
import com.amazonaws.services.ec2.model.ReplaceRouteTableAssociationRequest;
import com.amazonaws.services.ec2.model.ReplaceRouteTableAssociationResult;

/**
 * The RouteTableAssociation resource.
 */
public interface RouteTableAssociation {
    /**
     * Returns true if this resource's attributes have been loaded. If this
     * method returns {@code false}, calls to attribute getter methods on this
     * instance will make an implicit call to {@code load()} to retrieve the
     * value.
     */
    boolean isLoaded();

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the Main attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    Boolean getMain();

    /**
     * Gets the value of the SubnetId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSubnetId();

    /**
     * Gets the value of the RouteTableId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRouteTableId();

    /**
     * Retrieves the <code>RouteTable</code> resource referenced by this
     * resource.
     */
    RouteTable getRouteTable();

    /**
     * Retrieves the <code>Subnet</code> resource referenced by this resource.
     */
    Subnet getSubnet();

    /**
     * Performs the <code>ReplaceSubnet</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTableAssociation</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AssociationId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTableAssociation</code> resource object associated
     *         with the result of this action.
     * @see ReplaceRouteTableAssociationRequest
     */
    RouteTableAssociation replaceSubnet(ReplaceRouteTableAssociationRequest
            request);

    /**
     * Performs the <code>ReplaceSubnet</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTableAssociation</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AssociationId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>RouteTableAssociation</code> resource object associated
     *         with the result of this action.
     * @see ReplaceRouteTableAssociationRequest
     */
    RouteTableAssociation replaceSubnet(ReplaceRouteTableAssociationRequest
            request, ResultCapture<ReplaceRouteTableAssociationResult> extractor
            );

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTableAssociation</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AssociationId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DisassociateRouteTableRequest
     */
    void delete(DisassociateRouteTableRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>RouteTableAssociation</code> resource, and any conflicting
     * parameter value set in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>AssociationId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DisassociateRouteTableRequest
     */
    void delete(DisassociateRouteTableRequest request, ResultCapture<Void>
            extractor);
}
