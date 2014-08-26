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
import com.amazonaws.services.ec2.model.CreateNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.DeleteNetworkAclRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsResult;
import com.amazonaws.services.ec2.model.NetworkAclAssociation;
import com.amazonaws.services.ec2.model.NetworkAclEntry;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclAssociationRequest;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclAssociationResult;
import com.amazonaws.services.ec2.model.ReplaceNetworkAclEntryRequest;
import com.amazonaws.services.ec2.model.Tag;

/**
 * The NetworkAcl resource.
 */
public interface NetworkAcl {
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
     * @see #load(DescribeNetworkAclsRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeNetworkAclsRequest
     */
    boolean load(DescribeNetworkAclsRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclIds[]</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method is invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeNetworkAclsRequest
     */
    boolean load(DescribeNetworkAclsRequest request,
            ResultCapture<DescribeNetworkAclsResult> extractor);

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
     * Gets the value of the IsDefault attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getIsDefault();

    /**
     * Gets the value of the Associations attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<NetworkAclAssociation> getAssociations();

    /**
     * Gets the value of the VpcId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVpcId();

    /**
     * Gets the value of the Entries attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<NetworkAclEntry> getEntries();

    /**
     * Retrieves the <code>Vpc</code> resource referenced by this resource.
     */
    Vpc getVpc();

    /**
     * Performs the <code>CreateEntry</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see CreateNetworkAclEntryRequest
     */
    void createEntry(CreateNetworkAclEntryRequest request);

    /**
     * Performs the <code>CreateEntry</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see CreateNetworkAclEntryRequest
     */
    void createEntry(CreateNetworkAclEntryRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>ReplaceEntry</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ReplaceNetworkAclEntryRequest
     */
    void replaceEntry(ReplaceNetworkAclEntryRequest request);

    /**
     * Performs the <code>ReplaceEntry</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ReplaceNetworkAclEntryRequest
     */
    void replaceEntry(ReplaceNetworkAclEntryRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>Delete</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkAclRequest
     */
    void delete(DeleteNetworkAclRequest request);

    /**
     * Performs the <code>Delete</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkAclRequest
     */
    void delete(DeleteNetworkAclRequest request, ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
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
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
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
     * Performs the <code>ReplaceAssociation</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ReplaceNetworkAclAssociationRequest
     */
    ReplaceNetworkAclAssociationResult replaceAssociation(
            ReplaceNetworkAclAssociationRequest request);

    /**
     * Performs the <code>ReplaceAssociation</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see ReplaceNetworkAclAssociationRequest
     */
    ReplaceNetworkAclAssociationResult replaceAssociation(
            ReplaceNetworkAclAssociationRequest request,
            ResultCapture<ReplaceNetworkAclAssociationResult> extractor);

    /**
     * Performs the <code>DeleteEntry</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkAclEntryRequest
     */
    void deleteEntry(DeleteNetworkAclEntryRequest request);

    /**
     * Performs the <code>DeleteEntry</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>NetworkAcl</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>NetworkAclId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see DeleteNetworkAclEntryRequest
     */
    void deleteEntry(DeleteNetworkAclEntryRequest request, ResultCapture<Void>
            extractor);
}
