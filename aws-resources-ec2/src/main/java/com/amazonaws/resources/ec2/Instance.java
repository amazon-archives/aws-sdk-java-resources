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
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DetachVolumeRequest;
import com.amazonaws.services.ec2.model.DetachVolumeResult;
import com.amazonaws.services.ec2.model.GetConsoleOutputRequest;
import com.amazonaws.services.ec2.model.GetConsoleOutputResult;
import com.amazonaws.services.ec2.model.GetPasswordDataRequest;
import com.amazonaws.services.ec2.model.GetPasswordDataResult;
import com.amazonaws.services.ec2.model.GroupIdentifier;
import com.amazonaws.services.ec2.model.IamInstanceProfile;
import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;
import com.amazonaws.services.ec2.model.InstanceNetworkInterface;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.ModifyInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.MonitorInstancesRequest;
import com.amazonaws.services.ec2.model.MonitorInstancesResult;
import com.amazonaws.services.ec2.model.Monitoring;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.ProductCode;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.ReportInstanceStatusRequest;
import com.amazonaws.services.ec2.model.ResetInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StateReason;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.amazonaws.services.ec2.model.UnmonitorInstancesRequest;
import com.amazonaws.services.ec2.model.UnmonitorInstancesResult;

/**
 * The <code>Instance</code> resource.
 * Each <code>Instance</code> object is uniquely identified by these
 * identifier(s):
 * <ul>
 *   <li>Id</li>
 * </ul>
 */
public interface Instance {
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
     * @see #load(DescribeInstancesRequest)
     */
    boolean load();

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet.
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeInstancesRequest
     */
    boolean load(DescribeInstancesRequest request);

    /**
     * Makes a call to the service to load this resource's attributes if they
     * are not loaded yet, and use a ResultCapture to retrieve the low-level
     * client response
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return Returns {@code true} if the resource is not yet loaded when this
     *         method was invoked, which indicates that a service call has been
     *         made to retrieve the attributes.
     * @see DescribeInstancesRequest
     */
    boolean load(DescribeInstancesRequest request,
            ResultCapture<DescribeInstancesResult> extractor);

    /**
     * Gets the value of the Id identifier. This method always directly returns
     * the identifier and never involves a service call.
     */
    String getId();

    /**
     * Gets the value of the IamInstanceProfile attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    IamInstanceProfile getIamInstanceProfile();

    /**
     * Gets the value of the ClientToken attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getClientToken();

    /**
     * Gets the value of the ImageId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getImageId();

    /**
     * Gets the value of the KernelId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getKernelId();

    /**
     * Gets the value of the NetworkInterfaces attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    List<InstanceNetworkInterface> getNetworkInterfaces();

    /**
     * Gets the value of the ProductCodes attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<ProductCode> getProductCodes();

    /**
     * Gets the value of the AmiLaunchIndex attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Integer getAmiLaunchIndex();

    /**
     * Gets the value of the SriovNetSupport attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSriovNetSupport();

    /**
     * Gets the value of the Architecture attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getArchitecture();

    /**
     * Gets the value of the SecurityGroups attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    List<GroupIdentifier> getSecurityGroups();

    /**
     * Gets the value of the EbsOptimized attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getEbsOptimized();

    /**
     * Gets the value of the Placement attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Placement getPlacement();

    /**
     * Gets the value of the PublicDnsName attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPublicDnsName();

    /**
     * Gets the value of the VpcId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getVpcId();

    /**
     * Gets the value of the PrivateDnsName attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPrivateDnsName();

    /**
     * Gets the value of the RootDeviceName attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRootDeviceName();

    /**
     * Gets the value of the RamdiskId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRamdiskId();

    /**
     * Gets the value of the PrivateIpAddress attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPrivateIpAddress();

    /**
     * Gets the value of the Hypervisor attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getHypervisor();

    /**
     * Gets the value of the Platform attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPlatform();

    /**
     * Gets the value of the BlockDeviceMappings attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    List<InstanceBlockDeviceMapping> getBlockDeviceMappings();

    /**
     * Gets the value of the RootDeviceType attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getRootDeviceType();

    /**
     * Gets the value of the SourceDestCheck attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Boolean getSourceDestCheck();

    /**
     * Gets the value of the SpotInstanceRequestId attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    String getSpotInstanceRequestId();

    /**
     * Gets the value of the StateReason attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    StateReason getStateReason();

    /**
     * Gets the value of the PublicIpAddress attribute. If this resource is not
     * yet loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getPublicIpAddress();

    /**
     * Gets the value of the InstanceType attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getInstanceType();

    /**
     * Gets the value of the Tags attribute. If this resource is not yet loaded,
     * a call to {@code load()} is made to retrieve the value of the attribute.
     */
    List<Tag> getTags();

    /**
     * Gets the value of the StateTransitionReason attribute. If this resource
     * is not yet loaded, a call to {@code load()} is made to retrieve the value
     * of the attribute.
     */
    String getStateTransitionReason();

    /**
     * Gets the value of the State attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    InstanceState getState();

    /**
     * Gets the value of the SubnetId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getSubnetId();

    /**
     * Gets the value of the LaunchTime attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Date getLaunchTime();

    /**
     * Gets the value of the InstanceLifecycle attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getInstanceLifecycle();

    /**
     * Gets the value of the Monitoring attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    Monitoring getMonitoring();

    /**
     * Gets the value of the VirtualizationType attribute. If this resource is
     * not yet loaded, a call to {@code load()} is made to retrieve the value of
     * the attribute.
     */
    String getVirtualizationType();

    /**
     * Gets the value of the KeyName attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getKeyName();

    /**
     * Retrieves the <code>Vpc</code> resource referenced by this resource.
     */
    Vpc getVpc();

    /**
     * Retrieves the <code>KeyPair</code> resource referenced by this resource.
     */
    KeyPair getKeyPair();

    /**
     * Retrieves the <code>Image</code> resource referenced by this resource.
     */
    Image getImage();

    /**
     * Retrieves the <code>PlacementGroup</code> resource referenced by this
     * resource.
     */
    PlacementGroup getPlacementGroup();

    /**
     * Retrieves the <code>Subnet</code> resource referenced by this resource.
     */
    Subnet getSubnet();

    /**
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes();

    /**
     * Retrieves the Volumes collection referenced by this resource.
     */
    VolumeCollection getVolumes(DescribeVolumesRequest request);

    /**
     * Performs the <code>Terminate</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see TerminateInstancesRequest
     */
    TerminateInstancesResult terminate(TerminateInstancesRequest request);

    /**
     * Performs the <code>Terminate</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see TerminateInstancesRequest
     */
    TerminateInstancesResult terminate(TerminateInstancesRequest request,
            ResultCapture<TerminateInstancesResult> extractor);

    /**
     * The convenient method form for the <code>Terminate</code> action.
     *
     * @see #terminate(TerminateInstancesRequest)
     */
    TerminateInstancesResult terminate();

    /**
     * The convenient method form for the <code>Terminate</code> action.
     *
     * @see #terminate(TerminateInstancesRequest, ResultCapture)
     */
    TerminateInstancesResult terminate(ResultCapture<TerminateInstancesResult>
            extractor);

    /**
     * Performs the <code>Start</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see StartInstancesRequest
     */
    StartInstancesResult start(StartInstancesRequest request);

    /**
     * Performs the <code>Start</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see StartInstancesRequest
     */
    StartInstancesResult start(StartInstancesRequest request,
            ResultCapture<StartInstancesResult> extractor);

    /**
     * The convenient method form for the <code>Start</code> action.
     *
     * @see #start(StartInstancesRequest)
     */
    StartInstancesResult start();

    /**
     * The convenient method form for the <code>Start</code> action.
     *
     * @see #start(StartInstancesRequest, ResultCapture)
     */
    StartInstancesResult start(ResultCapture<StartInstancesResult> extractor);

    /**
     * Performs the <code>ResetRamdisk</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>ramdisk</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetRamdisk(ResetInstanceAttributeRequest request);

    /**
     * Performs the <code>ResetRamdisk</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>ramdisk</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetRamdisk(ResetInstanceAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>ResetRamdisk</code> action.
     *
     * @see #resetRamdisk(ResetInstanceAttributeRequest)
     */
    void resetRamdisk();

    /**
     * The convenient method form for the <code>ResetRamdisk</code> action.
     *
     * @see #resetRamdisk(ResetInstanceAttributeRequest, ResultCapture)
     */
    void resetRamdisk(ResultCapture<Void> extractor);

    /**
     * Performs the <code>ConsoleOutput</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetConsoleOutputRequest
     */
    GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request);

    /**
     * Performs the <code>ConsoleOutput</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetConsoleOutputRequest
     */
    GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request,
            ResultCapture<GetConsoleOutputResult> extractor);

    /**
     * The convenient method form for the <code>ConsoleOutput</code> action.
     *
     * @see #consoleOutput(GetConsoleOutputRequest)
     */
    GetConsoleOutputResult consoleOutput();

    /**
     * The convenient method form for the <code>ConsoleOutput</code> action.
     *
     * @see #consoleOutput(GetConsoleOutputRequest, ResultCapture)
     */
    GetConsoleOutputResult consoleOutput(ResultCapture<GetConsoleOutputResult>
            extractor);

    /**
     * Performs the <code>ReportStatus</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Instances.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ReportInstanceStatusRequest
     */
    void reportStatus(ReportInstanceStatusRequest request);

    /**
     * Performs the <code>ReportStatus</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Instances.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ReportInstanceStatusRequest
     */
    void reportStatus(ReportInstanceStatusRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs the <code>DetachVolume</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
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
    DetachVolumeResult detachVolume(DetachVolumeRequest request);

    /**
     * Performs the <code>DetachVolume</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
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
    DetachVolumeResult detachVolume(DetachVolumeRequest request,
            ResultCapture<DetachVolumeResult> extractor);

    /**
     * Performs the <code>AttachVolume</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
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
    AttachVolumeResult attachVolume(AttachVolumeRequest request);

    /**
     * Performs the <code>AttachVolume</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
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
    AttachVolumeResult attachVolume(AttachVolumeRequest request,
            ResultCapture<AttachVolumeResult> extractor);

    /**
     * The convenient method form for the <code>AttachVolume</code> action.
     *
     * @see #attachVolume(AttachVolumeRequest)
     */
    AttachVolumeResult attachVolume(String device, String volumeId);

    /**
     * The convenient method form for the <code>AttachVolume</code> action.
     *
     * @see #attachVolume(AttachVolumeRequest, ResultCapture)
     */
    AttachVolumeResult attachVolume(String device, String volumeId,
            ResultCapture<AttachVolumeResult> extractor);

    /**
     * Performs the <code>Stop</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see StopInstancesRequest
     */
    StopInstancesResult stop(StopInstancesRequest request);

    /**
     * Performs the <code>Stop</code> action and use a ResultCapture to retrieve
     * the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see StopInstancesRequest
     */
    StopInstancesResult stop(StopInstancesRequest request,
            ResultCapture<StopInstancesResult> extractor);

    /**
     * The convenient method form for the <code>Stop</code> action.
     *
     * @see #stop(StopInstancesRequest)
     */
    StopInstancesResult stop();

    /**
     * The convenient method form for the <code>Stop</code> action.
     *
     * @see #stop(StopInstancesRequest, ResultCapture)
     */
    StopInstancesResult stop(ResultCapture<StopInstancesResult> extractor);

    /**
     * Performs the <code>CreateImage</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see CreateImageRequest
     */
    com.amazonaws.resources.ec2.Image createImage(CreateImageRequest request);

    /**
     * Performs the <code>CreateImage</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The <code>Image</code> resource object associated with the result
     *         of this action.
     * @see CreateImageRequest
     */
    com.amazonaws.resources.ec2.Image createImage(CreateImageRequest request,
            ResultCapture<CreateImageResult> extractor);

    /**
     * The convenient method form for the <code>CreateImage</code> action.
     *
     * @see #createImage(CreateImageRequest)
     */
    com.amazonaws.resources.ec2.Image createImage(String name);

    /**
     * The convenient method form for the <code>CreateImage</code> action.
     *
     * @see #createImage(CreateImageRequest, ResultCapture)
     */
    com.amazonaws.resources.ec2.Image createImage(String name,
            ResultCapture<CreateImageResult> extractor);

    /**
     * Performs the <code>PasswordData</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetPasswordDataRequest
     */
    GetPasswordDataResult passwordData(GetPasswordDataRequest request);

    /**
     * Performs the <code>PasswordData</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see GetPasswordDataRequest
     */
    GetPasswordDataResult passwordData(GetPasswordDataRequest request,
            ResultCapture<GetPasswordDataResult> extractor);

    /**
     * The convenient method form for the <code>PasswordData</code> action.
     *
     * @see #passwordData(GetPasswordDataRequest)
     */
    GetPasswordDataResult passwordData();

    /**
     * The convenient method form for the <code>PasswordData</code> action.
     *
     * @see #passwordData(GetPasswordDataRequest, ResultCapture)
     */
    GetPasswordDataResult passwordData(ResultCapture<GetPasswordDataResult>
            extractor);

    /**
     * Performs the <code>ResetAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetAttribute(ResetInstanceAttributeRequest request);

    /**
     * Performs the <code>ResetAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetAttribute(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetInstanceAttributeRequest)
     */
    void resetAttribute(String attribute);

    /**
     * The convenient method form for the <code>ResetAttribute</code> action.
     *
     * @see #resetAttribute(ResetInstanceAttributeRequest, ResultCapture)
     */
    void resetAttribute(String attribute, ResultCapture<Void> extractor);

    /**
     * Performs the <code>Monitor</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see MonitorInstancesRequest
     */
    MonitorInstancesResult monitor(MonitorInstancesRequest request);

    /**
     * Performs the <code>Monitor</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see MonitorInstancesRequest
     */
    MonitorInstancesResult monitor(MonitorInstancesRequest request,
            ResultCapture<MonitorInstancesResult> extractor);

    /**
     * The convenient method form for the <code>Monitor</code> action.
     *
     * @see #monitor(MonitorInstancesRequest)
     */
    MonitorInstancesResult monitor();

    /**
     * The convenient method form for the <code>Monitor</code> action.
     *
     * @see #monitor(MonitorInstancesRequest, ResultCapture)
     */
    MonitorInstancesResult monitor(ResultCapture<MonitorInstancesResult>
            extractor);

    /**
     * Performs the <code>ResetSourceDestCheck</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>sourceDestCheck</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetSourceDestCheck(ResetInstanceAttributeRequest request);

    /**
     * Performs the <code>ResetSourceDestCheck</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>sourceDestCheck</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetSourceDestCheck(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ResetSourceDestCheck</code>
     * action.
     *
     * @see #resetSourceDestCheck(ResetInstanceAttributeRequest)
     */
    void resetSourceDestCheck();

    /**
     * The convenient method form for the <code>ResetSourceDestCheck</code>
     * action.
     *
     * @see #resetSourceDestCheck(ResetInstanceAttributeRequest, ResultCapture)
     */
    void resetSourceDestCheck(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Reboot</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RebootInstancesRequest
     */
    void reboot(RebootInstancesRequest request);

    /**
     * Performs the <code>Reboot</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see RebootInstancesRequest
     */
    void reboot(RebootInstancesRequest request, ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>Reboot</code> action.
     *
     * @see #reboot(RebootInstancesRequest)
     */
    void reboot();

    /**
     * The convenient method form for the <code>Reboot</code> action.
     *
     * @see #reboot(RebootInstancesRequest, ResultCapture)
     */
    void reboot(ResultCapture<Void> extractor);

    /**
     * Performs the <code>Unmonitor</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UnmonitorInstancesRequest
     */
    UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request);

    /**
     * Performs the <code>Unmonitor</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceIds.0</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see UnmonitorInstancesRequest
     */
    UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request,
            ResultCapture<UnmonitorInstancesResult> extractor);

    /**
     * The convenient method form for the <code>Unmonitor</code> action.
     *
     * @see #unmonitor(UnmonitorInstancesRequest)
     */
    UnmonitorInstancesResult unmonitor();

    /**
     * The convenient method form for the <code>Unmonitor</code> action.
     *
     * @see #unmonitor(UnmonitorInstancesRequest, ResultCapture)
     */
    UnmonitorInstancesResult unmonitor(ResultCapture<UnmonitorInstancesResult>
            extractor);

    /**
     * Performs the <code>ModifyAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyInstanceAttributeRequest
     */
    void modifyAttribute(ModifyInstanceAttributeRequest request);

    /**
     * Performs the <code>ModifyAttribute</code> action and use a ResultCapture
     * to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ModifyInstanceAttributeRequest
     */
    void modifyAttribute(ModifyInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifyInstanceAttributeRequest)
     */
    void modifyAttribute(String attribute);

    /**
     * The convenient method form for the <code>ModifyAttribute</code> action.
     *
     * @see #modifyAttribute(ModifyInstanceAttributeRequest, ResultCapture)
     */
    void modifyAttribute(String attribute, ResultCapture<Void> extractor);

    /**
     * Performs the <code>DescribeAttribute</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeInstanceAttributeRequest
     */
    DescribeInstanceAttributeResult describeAttribute(
            DescribeInstanceAttributeRequest request);

    /**
     * Performs the <code>DescribeAttribute</code> action and use a
     * ResultCapture to retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @return The response of the low-level client operation associated with
     *         this resource action.
     * @see DescribeInstanceAttributeRequest
     */
    DescribeInstanceAttributeResult describeAttribute(
            DescribeInstanceAttributeRequest request,
            ResultCapture<DescribeInstanceAttributeResult> extractor);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeInstanceAttributeRequest)
     */
    DescribeInstanceAttributeResult describeAttribute(String attribute);

    /**
     * The convenient method form for the <code>DescribeAttribute</code> action.
     *
     * @see #describeAttribute(DescribeInstanceAttributeRequest, ResultCapture)
     */
    DescribeInstanceAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeInstanceAttributeResult> extractor);

    /**
     * Performs the <code>ResetKernel</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>kernel</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetKernel(ResetInstanceAttributeRequest request);

    /**
     * Performs the <code>ResetKernel</code> action and use a ResultCapture to
     * retrieve the low-level client response.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>InstanceId</code></b>
     *         - mapped from the <code>Id</code> identifier.
     *   </li>
     *   <li>
     *     <b><code>Attribute</code></b>
     *         - constant value <code>kernel</code>.
     *   </li>
     * </ul>
     *
     * <p>
     *
     * @see ResetInstanceAttributeRequest
     */
    void resetKernel(ResetInstanceAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * The convenient method form for the <code>ResetKernel</code> action.
     *
     * @see #resetKernel(ResetInstanceAttributeRequest)
     */
    void resetKernel();

    /**
     * The convenient method form for the <code>ResetKernel</code> action.
     *
     * @see #resetKernel(ResetInstanceAttributeRequest, ResultCapture)
     */
    void resetKernel(ResultCapture<Void> extractor);

    /**
     * Performs the <code>CreateTags</code> action.
     *
     * <p>
     * The following request parameters will be populated from the data of this
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources.0</code></b>
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
     * <code>Instance</code> resource, and any conflicting parameter value set
     * in the request will be overridden:
     * <ul>
     *   <li>
     *     <b><code>Resources.0</code></b>
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
