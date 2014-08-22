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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.RouteTable;
import com.amazonaws.resources.ec2.RouteTableAssociation;
import com.amazonaws.resources.ec2.RouteTableAssociationCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AssociateRouteTableRequest;
import com.amazonaws.services.ec2.model.AssociateRouteTableResult;
import com.amazonaws.services.ec2.model.CreateRouteRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.PropagatingVgw;
import com.amazonaws.services.ec2.model.Route;
import com.amazonaws.services.ec2.model.Tag;

class RouteTableImpl implements RouteTable {
    public static final ResourceCodec<RouteTable> CODEC = new Codec();

    private final ResourceImpl resource;

    public RouteTableImpl(ResourceImpl resource) {
        this.resource = resource;
    }

    @Override
    public boolean isLoaded() {
        return resource.isLoaded();
    }

    @Override
    public boolean load() {
        return load(null, null);
    }

    @Override
    public boolean load(DescribeRouteTablesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeRouteTablesRequest request,
            ResultCapture<DescribeRouteTablesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public List<Tag> getTags() {
        return (List<Tag>) resource.getAttribute("Tags");
    }

    @Override
    public List<PropagatingVgw> getPropagatingVgws() {
        return (List<PropagatingVgw>) resource.getAttribute("PropagatingVgws");
    }

    @Override
    public List<Route> getRoutes() {
        return (List<Route>) resource.getAttribute("Routes");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public Vpc getVpc() {
        ResourceImpl result = resource.getReference("Vpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public RouteTableAssociationCollection getAssociations() {
        return getAssociations(null);
    }

    @Override
    public RouteTableAssociationCollection getAssociations(
            DescribeRouteTablesRequest request) {

        ResourceCollectionImpl result = resource.getCollection("Associations",
                request);

        if (result == null) return null;
        return new RouteTableAssociationCollectionImpl(result);
    }

    @Override
    public RouteTableAssociation associateWithSubnet(AssociateRouteTableRequest
            request) {

        return associateWithSubnet(request, null);
    }

    @Override
    public RouteTableAssociation associateWithSubnet(AssociateRouteTableRequest
            request, ResultCapture<AssociateRouteTableResult> extractor) {

        ActionResult result = resource.performAction("AssociateWithSubnet",
                request, extractor);

        if (result == null) return null;
        return new RouteTableAssociationImpl(result.getResource());
    }

    @Override
    public void createRoute(CreateRouteRequest request) {
        createRoute(request, null);
    }

    @Override
    public void createRoute(CreateRouteRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("CreateRoute", request, extractor);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest
            request) {

        return createTags(request, null);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(CreateTagsRequest
            request, ResultCapture<Void> extractor) {

        ActionResult result = resource.performAction("CreateTags", request,
                extractor);

        if (result == null) return null;
        return CodecUtils.transform(result.getResources(), TagImpl.CODEC);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags) {
        return createTags(tags, (ResultCapture<Void>)null);
    }

    @Override
    public List<com.amazonaws.resources.ec2.Tag> createTags(List<Tag> tags,
            ResultCapture<Void> extractor) {

        CreateTagsRequest request = new CreateTagsRequest()
            .withTags(tags);
        return createTags(request, extractor);
    }

    private static class Codec implements ResourceCodec<RouteTable> {
        @Override
        public RouteTable transform(ResourceImpl resource) {
            return new RouteTableImpl(resource);
        }
    }
}
