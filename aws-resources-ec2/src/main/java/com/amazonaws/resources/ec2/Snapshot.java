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

/**
 * The <code>Snapshot</code> resource.
 * Each <code>Snapshot</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 * </ul>
 */
public interface Snapshot {
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
     * @see #load(DescribeSnapshotsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeSnapshotsRequest
     */
    boolean load(DescribeSnapshotsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotIds[*]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeSnapshotsRequest
     */
    boolean load(DescribeSnapshotsRequest request,
            ResultCapture<DescribeSnapshotsResult> extractor);

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
     * Gets the value of the Description attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getDescription();

    /**
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getState();

    /**
     * Gets the value of the OwnerAlias attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getOwnerAlias();

    /**
     * Gets the value of the VolumeId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVolumeId();

    /**
     * Gets the value of the VolumeSize attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Integer getVolumeSize();

    /**
     * Gets the value of the OwnerId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getOwnerId();

    /**
     * Gets the value of the Encrypted attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getEncrypted();

    /**
     * Gets the value of the StartTime attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getStartTime();

    /**
     * Gets the value of the Progress attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getProgress();

    /**
     * Retrieves the <code>Volume</code> resource referenced by this resource.
     */
    Volume getVolume();

    /**
     * Performs the <code>ResetAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetSnapshotAttributeRequest
     */
    void resetAttribute(ResetSnapshotAttributeRequest request);

    /**
     * Performs the <code>ResetAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetSnapshotAttributeRequest
     */
    void resetAttribute(ResetSnapshotAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetSnapshotAttributeRequest)
     */
    void resetAttribute(String attribute);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetSnapshotAttributeRequest, ResultCapture)
     */
    void resetAttribute(String attribute, ResultCapture<Void> extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifySnapshotAttributeRequest
     */
    void modifyAttribute(ModifySnapshotAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifySnapshotAttributeRequest
     */
    void modifyAttribute(ModifySnapshotAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifySnapshotAttributeRequest)
     */
    void modifyAttribute(String attribute, String operationType);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifySnapshotAttributeRequest, ResultCapture)
     */
    void modifyAttribute(String attribute, String operationType,
            ResultCapture<Void> extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeSnapshotAttributeRequest
     */
    DescribeSnapshotAttributeResult describeAttribute(
            DescribeSnapshotAttributeRequest request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeSnapshotAttributeRequest
     */
    DescribeSnapshotAttributeResult describeAttribute(
            DescribeSnapshotAttributeRequest request,
            ResultCapture<DescribeSnapshotAttributeResult> extractor);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeSnapshotAttributeRequest)
     */
    DescribeSnapshotAttributeResult describeAttribute(String attribute);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeSnapshotAttributeRequest, ResultCapture)
     */
    DescribeSnapshotAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeSnapshotAttributeResult> extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSnapshotRequest
     */
    void delete(DeleteSnapshotRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteSnapshotRequest
     */
    void delete(DeleteSnapshotRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteSnapshotRequest)
     */
    void delete();

    /**
     * The convenient method form for the <code>Delete</code> action.
     *
     * @see #delete(DeleteSnapshotRequest, ResultCapture)
     */
    void delete(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
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
     * <code>Snapshot</code> resource, and any conflicting parameter value set
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

    /**
     * Performs the <code>Copy</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SourceSnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CopySnapshotRequest
     */
    CopySnapshotResult copy(CopySnapshotRequest request);

    /**
     * Performs the <code>Copy</code> action and use a ResultCapture to retrieve
     * the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Snapshot</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>SourceSnapshotId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see CopySnapshotRequest
     */
    CopySnapshotResult copy(CopySnapshotRequest request,
            ResultCapture<CopySnapshotResult> extractor);
}
