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
import com.amazonaws.resources.ec2.Volume;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.CopySnapshotRequest;
import com.amazonaws.services.ec2.model.CopySnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.ModifySnapshotAttributeRequest;
import com.amazonaws.services.ec2.model.ResetSnapshotAttributeRequest;
import com.amazonaws.services.ec2.model.Tag;

class SnapshotImpl implements Snapshot {
    public static final ResourceCodec<Snapshot> CODEC = new Codec();

    private final ResourceImpl resource;

    public SnapshotImpl(ResourceImpl resource) {
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
    public boolean load(DescribeSnapshotsRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeSnapshotsRequest request,
            ResultCapture<DescribeSnapshotsResult> extractor) {

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
    public String getDescription() {
        return (String) resource.getAttribute("Description");
    }

    @Override
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public String getOwnerAlias() {
        return (String) resource.getAttribute("OwnerAlias");
    }

    @Override
    public String getVolumeId() {
        return (String) resource.getAttribute("VolumeId");
    }

    @Override
    public Integer getVolumeSize() {
        return (Integer) resource.getAttribute("VolumeSize");
    }

    @Override
    public String getKmsKeyId() {
        return (String) resource.getAttribute("KmsKeyId");
    }

    @Override
    public String getOwnerId() {
        return (String) resource.getAttribute("OwnerId");
    }

    @Override
    public Boolean getEncrypted() {
        return (Boolean) resource.getAttribute("Encrypted");
    }

    @Override
    public Date getStartTime() {
        return (Date) resource.getAttribute("StartTime");
    }

    @Override
    public String getProgress() {
        return (String) resource.getAttribute("Progress");
    }

    @Override
    public Volume getVolume() {
        ResourceImpl result = resource.getReference("Volume");
        if (result == null) return null;
        return new VolumeImpl(result);
    }

    @Override
    public void resetAttribute(ResetSnapshotAttributeRequest request) {
        resetAttribute(request, null);
    }

    @Override
    public void resetAttribute(ResetSnapshotAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetAttribute", request, extractor);
    }

    @Override
    public void resetAttribute(String attribute) {
        resetAttribute(attribute, (ResultCapture<Void>)null);
    }

    @Override
    public void resetAttribute(String attribute, ResultCapture<Void> extractor)
            {

        ResetSnapshotAttributeRequest request = new
                ResetSnapshotAttributeRequest()

            .withAttribute(attribute);
        resetAttribute(request, extractor);
    }

    @Override
    public void modifyAttribute(ModifySnapshotAttributeRequest request) {
        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifySnapshotAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ModifyAttribute", request, extractor);
    }

    @Override
    public void modifyAttribute(String attribute, String operationType) {
        modifyAttribute(attribute, operationType, (ResultCapture<Void>)null);
    }

    @Override
    public void modifyAttribute(String attribute, String operationType,
            ResultCapture<Void> extractor) {

        ModifySnapshotAttributeRequest request = new
                ModifySnapshotAttributeRequest()

            .withAttribute(attribute)
            .withOperationType(operationType);
        modifyAttribute(request, extractor);
    }

    @Override
    public DescribeSnapshotAttributeResult describeAttribute(
            DescribeSnapshotAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeSnapshotAttributeResult describeAttribute(
            DescribeSnapshotAttributeRequest request,
            ResultCapture<DescribeSnapshotAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeSnapshotAttributeResult) result.getData();
    }

    @Override
    public DescribeSnapshotAttributeResult describeAttribute(String attribute) {
        return describeAttribute(attribute,
                (ResultCapture<DescribeSnapshotAttributeResult>)null);
    }

    @Override
    public DescribeSnapshotAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeSnapshotAttributeResult> extractor) {

        DescribeSnapshotAttributeRequest request = new
                DescribeSnapshotAttributeRequest()

            .withAttribute(attribute);
        return describeAttribute(request, extractor);
    }

    @Override
    public void delete(DeleteSnapshotRequest request) {
        delete(request, null);
    }

    @Override
    public void delete(DeleteSnapshotRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Delete", request, extractor);
    }

    @Override
    public void delete() {
        delete((ResultCapture<Void>)null);
    }

    @Override
    public void delete(ResultCapture<Void> extractor) {
        DeleteSnapshotRequest request = new DeleteSnapshotRequest();
        delete(request, extractor);
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
    public CopySnapshotResult copy(CopySnapshotRequest request) {
        return copy(request, null);
    }

    @Override
    public CopySnapshotResult copy(CopySnapshotRequest request,
            ResultCapture<CopySnapshotResult> extractor) {

        ActionResult result = resource.performAction("Copy", request,
                extractor);

        if (result == null) return null;
        return (CopySnapshotResult) result.getData();
    }

    private static class Codec implements ResourceCodec<Snapshot> {
        @Override
        public Snapshot transform(ResourceImpl resource) {
            return new SnapshotImpl(resource);
        }
    }
}
