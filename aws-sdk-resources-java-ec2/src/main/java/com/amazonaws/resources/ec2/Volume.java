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

/**
 * The Volume resource.
 */
public interface Volume {
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
     * @see #load(DescribeVolumesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVolumesRequest
     */
    boolean load(DescribeVolumesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeVolumesRequest
     */
    boolean load(DescribeVolumesRequest request,
            ResultCapture<DescribeVolumesResult> extractor);

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
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getState();

    /**
     * Gets the value of the VolumeType attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVolumeType();

    /**
     * Gets the value of the Iops attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    Integer getIops();

    /**
     * Gets the value of the CreateTime attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getCreateTime();

    /**
     * Gets the value of the SnapshotId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSnapshotId();

    /**
     * Gets the value of the Encrypted attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getEncrypted();

    /**
     * Gets the value of the AvailabilityZone attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getAvailabilityZone();

    /**
     * Gets the value of the Attachments attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<VolumeAttachment> getAttachments();

    /**
     * Gets the value of the Size attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    Integer getSize();

    /**
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots();

    /**
     * Retrieves the Snapshots collection referenced by this resource.
     */
    SnapshotCollection getSnapshots(DescribeSnapshotsRequest request);

    /**
     * Performs the <code>DetachFromInstance</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DetachVolumeRequest
     */
    DetachVolumeResult detachFromInstance(DetachVolumeRequest request);

    /**
     * Performs the <code>DetachFromInstance</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DetachVolumeRequest
     */
    DetachVolumeResult detachFromInstance(DetachVolumeRequest request,
            ResultCapture<DetachVolumeResult> extractor);

    /**
     * The convenient method form for the <code>DetachFromInstance</code>
     * action.
     *
     * @see #detachFromInstance(DetachVolumeRequest)
     */
    DetachVolumeResult detachFromInstance();

    /**
     * The convenient method form for the <code>DetachFromInstance</code>
     * action.
     *
     * @see #detachFromInstance(DetachVolumeRequest, ResultCapture)
     */
    DetachVolumeResult detachFromInstance(ResultCapture<DetachVolumeResult>
            extractor);

    /**
     * Performs the <code>AttachToInstance</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AttachVolumeRequest
     */
    AttachVolumeResult attachToInstance(AttachVolumeRequest request);

    /**
     * Performs the <code>AttachToInstance</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see AttachVolumeRequest
     */
    AttachVolumeResult attachToInstance(AttachVolumeRequest request,
            ResultCapture<AttachVolumeResult> extractor);

    /**
     * The convenient method form for the <code>AttachToInstance</code> action.
     *
     * @see #attachToInstance(AttachVolumeRequest)
     */
    AttachVolumeResult attachToInstance(String device, String instanceId);

    /**
     * The convenient method form for the <code>AttachToInstance</code> action.
     *
     * @see #attachToInstance(AttachVolumeRequest, ResultCapture)
     */
    AttachVolumeResult attachToInstance(String device, String instanceId,
            ResultCapture<AttachVolumeResult> extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyVolumeAttributeRequest
     */
    void modifyAttribute(ModifyVolumeAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyVolumeAttributeRequest
     */
    void modifyAttribute(ModifyVolumeAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVolumeAttributeRequest
     */
    DescribeVolumeAttributeResult describeAttribute(
            DescribeVolumeAttributeRequest request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVolumeAttributeRequest
     */
    DescribeVolumeAttributeResult describeAttribute(
            DescribeVolumeAttributeRequest request,
            ResultCapture<DescribeVolumeAttributeResult> extractor);

    /**
     * Performs the <code>DescribeStatus</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVolumeStatusRequest
     */
    DescribeVolumeStatusResult describeStatus(DescribeVolumeStatusRequest
            request);

    /**
     * Performs the <code>DescribeStatus</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeVolumeStatusRequest
     */
    DescribeVolumeStatusResult describeStatus(DescribeVolumeStatusRequest
            request, ResultCapture<DescribeVolumeStatusResult> extractor);

    /**
     * Performs the <code>EnableIo</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see EnableVolumeIORequest
     */
    void enableIo(EnableVolumeIORequest request);

    /**
     * Performs the <code>EnableIo</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see EnableVolumeIORequest
     */
    void enableIo(EnableVolumeIORequest request, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
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
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources[]</code></b>
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
     * Performs the <code>CreateSnapshot</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Snapshot</code> resource object associated with the
     *         result of this action.
     * @see CreateSnapshotRequest
     */
    Snapshot createSnapshot(CreateSnapshotRequest request);

    /**
     * Performs the <code>CreateSnapshot</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Volume</code> resource, and any conflicting parameter value set in
     * the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>VolumeId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Snapshot</code> resource object associated with the
     *         result of this action.
     * @see CreateSnapshotRequest
     */
    Snapshot createSnapshot(CreateSnapshotRequest request,
            ResultCapture<CreateSnapshotResult> extractor);

    /**
     * The convenient method form for the <code>CreateSnapshot</code> action.
     *
     * @see #createSnapshot(CreateSnapshotRequest)
     */
    Snapshot createSnapshot(String description);

    /**
     * The convenient method form for the <code>CreateSnapshot</code> action.
     *
     * @see #createSnapshot(CreateSnapshotRequest, ResultCapture)
     */
    Snapshot createSnapshot(String description,
            ResultCapture<CreateSnapshotResult> extractor);
}
