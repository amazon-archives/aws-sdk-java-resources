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
import com.amazonaws.resources.ec2.Snapshot;
import com.amazonaws.resources.ec2.SnapshotCollection;
import com.amazonaws.resources.ec2.Volume;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.AttachVolumeRequest;
import com.amazonaws.services.ec2.model.AttachVolumeResult;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeVolumeAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeVolumeAttributeResult;
import com.amazonaws.services.ec2.model.DescribeVolumeStatusRequest;
import com.amazonaws.services.ec2.model.DescribeVolumeStatusResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.DetachVolumeRequest;
import com.amazonaws.services.ec2.model.DetachVolumeResult;
import com.amazonaws.services.ec2.model.EnableVolumeIORequest;
import com.amazonaws.services.ec2.model.ModifyVolumeAttributeRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.VolumeAttachment;

class VolumeImpl implements Volume {
    public static final ResourceCodec<Volume> CODEC = new Codec();

    private final ResourceImpl resource;

    public VolumeImpl(ResourceImpl resource) {
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
    public boolean load(DescribeVolumesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeVolumesRequest request,
            ResultCapture<DescribeVolumesResult> extractor) {

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
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public String getVolumeType() {
        return (String) resource.getAttribute("VolumeType");
    }

    @Override
    public String getKmsKeyId() {
        return (String) resource.getAttribute("KmsKeyId");
    }

    @Override
    public Integer getIops() {
        return (Integer) resource.getAttribute("Iops");
    }

    @Override
    public Date getCreateTime() {
        return (Date) resource.getAttribute("CreateTime");
    }

    @Override
    public String getSnapshotId() {
        return (String) resource.getAttribute("SnapshotId");
    }

    @Override
    public Boolean getEncrypted() {
        return (Boolean) resource.getAttribute("Encrypted");
    }

    @Override
    public String getAvailabilityZone() {
        return (String) resource.getAttribute("AvailabilityZone");
    }

    @Override
    public List<VolumeAttachment> getAttachments() {
        return (List<VolumeAttachment>) resource.getAttribute("Attachments");
    }

    @Override
    public Integer getSize() {
        return (Integer) resource.getAttribute("Size");
    }

    @Override
    public SnapshotCollection getSnapshots() {
        return getSnapshots(null);
    }

    @Override
    public SnapshotCollection getSnapshots(DescribeSnapshotsRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Snapshots",
                request);

        if (result == null) return null;
        return new SnapshotCollectionImpl(result);
    }

    @Override
    public DetachVolumeResult detachFromInstance(DetachVolumeRequest request) {
        return detachFromInstance(request, null);
    }

    @Override
    public DetachVolumeResult detachFromInstance(DetachVolumeRequest request,
            ResultCapture<DetachVolumeResult> extractor) {

        ActionResult result = resource.performAction("DetachFromInstance",
                request, extractor);

        if (result == null) return null;
        return (DetachVolumeResult) result.getData();
    }

    @Override
    public DetachVolumeResult detachFromInstance() {
        return detachFromInstance((ResultCapture<DetachVolumeResult>)null);
    }

    @Override
    public DetachVolumeResult detachFromInstance(
            ResultCapture<DetachVolumeResult> extractor) {

        DetachVolumeRequest request = new DetachVolumeRequest();
        return detachFromInstance(request, extractor);
    }

    @Override
    public AttachVolumeResult attachToInstance(AttachVolumeRequest request) {
        return attachToInstance(request, null);
    }

    @Override
    public AttachVolumeResult attachToInstance(AttachVolumeRequest request,
            ResultCapture<AttachVolumeResult> extractor) {

        ActionResult result = resource.performAction("AttachToInstance",
                request, extractor);

        if (result == null) return null;
        return (AttachVolumeResult) result.getData();
    }

    @Override
    public AttachVolumeResult attachToInstance(String device, String instanceId)
            {

        return attachToInstance(device, instanceId,
                (ResultCapture<AttachVolumeResult>)null);
    }

    @Override
    public AttachVolumeResult attachToInstance(String device, String instanceId,
            ResultCapture<AttachVolumeResult> extractor) {

        AttachVolumeRequest request = new AttachVolumeRequest()
            .withDevice(device)
            .withInstanceId(instanceId);
        return attachToInstance(request, extractor);
    }

    @Override
    public void modifyAttribute(ModifyVolumeAttributeRequest request) {
        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifyVolumeAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ModifyAttribute", request, extractor);
    }

    @Override
    public DescribeVolumeAttributeResult describeAttribute(
            DescribeVolumeAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeVolumeAttributeResult describeAttribute(
            DescribeVolumeAttributeRequest request,
            ResultCapture<DescribeVolumeAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeVolumeAttributeResult) result.getData();
    }

    @Override
    public DescribeVolumeStatusResult describeStatus(DescribeVolumeStatusRequest
            request) {

        return describeStatus(request, null);
    }

    @Override
    public DescribeVolumeStatusResult describeStatus(DescribeVolumeStatusRequest
            request, ResultCapture<DescribeVolumeStatusResult> extractor) {

        ActionResult result = resource.performAction("DescribeStatus", request,
                extractor);

        if (result == null) return null;
        return (DescribeVolumeStatusResult) result.getData();
    }

    @Override
    public void enableIo(EnableVolumeIORequest request) {
        enableIo(request, null);
    }

    @Override
    public void enableIo(EnableVolumeIORequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("EnableIo", request, extractor);
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

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request) {
        return createSnapshot(request, null);
    }

    @Override
    public Snapshot createSnapshot(CreateSnapshotRequest request,
            ResultCapture<CreateSnapshotResult> extractor) {

        ActionResult result = resource.performAction("CreateSnapshot", request,
                extractor);

        if (result == null) return null;
        return new SnapshotImpl(result.getResource());
    }

    @Override
    public Snapshot createSnapshot(String description) {
        return createSnapshot(description,
                (ResultCapture<CreateSnapshotResult>)null);
    }

    @Override
    public Snapshot createSnapshot(String description,
            ResultCapture<CreateSnapshotResult> extractor) {

        CreateSnapshotRequest request = new CreateSnapshotRequest()
            .withDescription(description);
        return createSnapshot(request, extractor);
    }

    private static class Codec implements ResourceCodec<Volume> {
        @Override
        public Volume transform(ResourceImpl resource) {
            return new VolumeImpl(resource);
        }
    }
}
