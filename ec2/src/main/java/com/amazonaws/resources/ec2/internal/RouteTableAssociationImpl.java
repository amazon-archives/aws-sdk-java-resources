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
package com.amazonaws.resources.ec2.internal;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.RouteTable;
import com.amazonaws.resources.ec2.RouteTableAssociation;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.DisassociateRouteTableRequest;
import com.amazonaws.services.ec2.model.ReplaceRouteTableAssociationRequest;
import com.amazonaws.services.ec2.model.ReplaceRouteTableAssociationResult;

class RouteTableAssociationImpl implements RouteTableAssociation {
    public static final ResourceCodec<RouteTableAssociation> CODEC = new Codec()
            ;

    private final ResourceImpl resource;

    public RouteTableAssociationImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public Boolean getMain() {
        return (Boolean) resource.getAttribute("Main");
    }

    @Override
    public String getSubnetId() {
        return (String) resource.getAttribute("SubnetId");
    }

    @Override
    public String getRouteTableId() {
        return (String) resource.getAttribute("RouteTableId");
    }

    @Override
    public RouteTable getRouteTable() {
        ResourceImpl result = resource.getReference("RouteTable");
        if (result == null) return null;
        return new RouteTableImpl(result);
    }

    @Override
    public Subnet getSubnet() {
        ResourceImpl result = resource.getReference("Subnet");
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public RouteTableAssociation replaceSubnet(
            ReplaceRouteTableAssociationRequest request) {

        return replaceSubnet(request, null);
    }

    @Override
    public RouteTableAssociation replaceSubnet(
            ReplaceRouteTableAssociationRequest request,
            ResultCapture<ReplaceRouteTableAssociationResult> extractor) {

        ActionResult result = resource.performAction("ReplaceSubnet", request,
                extractor);

        if (result == null) return null;
        return new RouteTableAssociationImpl(result.getResource());
    }

    @Override
    public void delete(DisassociateRouteTableRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DisassociateRouteTableRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("Delete", request, extractor);
    }

    private static class Codec implements ResourceCodec<RouteTableAssociation> {
        @Override
        public RouteTableAssociation transform(ResourceImpl resource) {
            return new RouteTableAssociationImpl(resource);
        }
    }
}
