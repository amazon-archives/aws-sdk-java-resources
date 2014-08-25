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

import java.util.Date;
import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.ec2.VpcPeeringConnection;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
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

class VpcPeeringConnectionImpl implements VpcPeeringConnection {
    public static final ResourceCodec<VpcPeeringConnection> CODEC = new Codec();

    private final ResourceImpl resource;

    public VpcPeeringConnectionImpl(ResourceImpl resource) {
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
    public boolean load(DescribeVpcPeeringConnectionsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeVpcPeeringConnectionsRequest request,
            ResultCapture<DescribeVpcPeeringConnectionsResult> extractor) {

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
    public VpcPeeringConnectionStateReason getStatus() {
        return (VpcPeeringConnectionStateReason)
                resource.getAttribute("Status");
    }

    @Override
    public VpcPeeringConnectionVpcInfo getAccepterVpcInfo() {
        return (VpcPeeringConnectionVpcInfo)
                resource.getAttribute("AccepterVpcInfo");
    }

    @Override
    public VpcPeeringConnectionVpcInfo getRequesterVpcInfo() {
        return (VpcPeeringConnectionVpcInfo)
                resource.getAttribute("RequesterVpcInfo");
    }

    @Override
    public Date getExpirationTime() {
        return (Date) resource.getAttribute("ExpirationTime");
    }

    @Override
    public Vpc getRequesterVpc() {
        ResourceImpl result = resource.getReference("RequesterVpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public Vpc getAccepterVpc() {
        ResourceImpl result = resource.getReference("AccepterVpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public DeleteVpcPeeringConnectionResult delete(
            DeleteVpcPeeringConnectionRequest request) {

        return delete(request, null);
    }

    @Override
    public DeleteVpcPeeringConnectionResult delete(
            DeleteVpcPeeringConnectionRequest request,
            ResultCapture<DeleteVpcPeeringConnectionResult> extractor) {

        ActionResult result = resource.performAction("Delete", request,
                extractor);

        if (result == null) return null;
        return (DeleteVpcPeeringConnectionResult) result.getData();
    }

    @Override
    public RejectVpcPeeringConnectionResult reject(
            RejectVpcPeeringConnectionRequest request) {

        return reject(request, null);
    }

    @Override
    public RejectVpcPeeringConnectionResult reject(
            RejectVpcPeeringConnectionRequest request,
            ResultCapture<RejectVpcPeeringConnectionResult> extractor) {

        ActionResult result = resource.performAction("Reject", request,
                extractor);

        if (result == null) return null;
        return (RejectVpcPeeringConnectionResult) result.getData();
    }

    @Override
    public AcceptVpcPeeringConnectionResult accept(
            AcceptVpcPeeringConnectionRequest request) {

        return accept(request, null);
    }

    @Override
    public AcceptVpcPeeringConnectionResult accept(
            AcceptVpcPeeringConnectionRequest request,
            ResultCapture<AcceptVpcPeeringConnectionResult> extractor) {

        ActionResult result = resource.performAction("Accept", request,
                extractor);

        if (result == null) return null;
        return (AcceptVpcPeeringConnectionResult) result.getData();
    }

    private static class Codec implements ResourceCodec<VpcPeeringConnection> {
        @Override
        public VpcPeeringConnection transform(ResourceImpl resource) {
            return new VpcPeeringConnectionImpl(resource);
        }
    }
}
