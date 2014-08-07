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
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
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
 * The Instance resource.
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
     * Makes a call to the service to load this resource's attributes.
     */
    boolean load();

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeInstancesRequest request);

    /**
     * TODO: Make better javadocs.
     */
    boolean load(DescribeInstancesRequest request,
            ResultCapture<DescribeInstancesResult> extractor);

    /**
     * Gets the value of the Id identifier.
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
     * Gets the value of the InstanceId attribute. If this resource is not yet
     * loaded, a call to {@code load()} is made to retrieve the value of the
     * attribute.
     */
    String getInstanceId();

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
     * Retrieves the Vpc referenced by this resource.
     */
    Vpc getVpc();

    /**
     * Retrieves the Subnet referenced by this resource.
     */
    Subnet getSubnet();

    /**
     * Performs an action.
     */
    TerminateInstancesResult terminate(TerminateInstancesRequest request);

    /**
     * Performs an action.
     */
    TerminateInstancesResult terminate(TerminateInstancesRequest request,
            ResultCapture<TerminateInstancesResult> extractor);

    /**
     * Performs an action.
     */
    void resetRamdisk(ResetInstanceAttributeRequest request);

    /**
     * Performs an action.
     */
    void resetRamdisk(ResetInstanceAttributeRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    StartInstancesResult start(StartInstancesRequest request);

    /**
     * Performs an action.
     */
    StartInstancesResult start(StartInstancesRequest request,
            ResultCapture<StartInstancesResult> extractor);

    /**
     * Performs an action.
     */
    GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request);

    /**
     * Performs an action.
     */
    GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request,
            ResultCapture<GetConsoleOutputResult> extractor);

    /**
     * Performs an action.
     */
    void reportStatus(ReportInstanceStatusRequest request);

    /**
     * Performs an action.
     */
    void reportStatus(ReportInstanceStatusRequest request, ResultCapture<Void>
            extractor);

    /**
     * Performs an action.
     */
    StopInstancesResult stop(StopInstancesRequest request);

    /**
     * Performs an action.
     */
    StopInstancesResult stop(StopInstancesRequest request,
            ResultCapture<StopInstancesResult> extractor);

    /**
     * Performs an action.
     */
    GetPasswordDataResult passwordData(GetPasswordDataRequest request);

    /**
     * Performs an action.
     */
    GetPasswordDataResult passwordData(GetPasswordDataRequest request,
            ResultCapture<GetPasswordDataResult> extractor);

    /**
     * Performs an action.
     */
    void resetAttribute(ResetInstanceAttributeRequest request);

    /**
     * Performs an action.
     */
    void resetAttribute(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    MonitorInstancesResult monitor(MonitorInstancesRequest request);

    /**
     * Performs an action.
     */
    MonitorInstancesResult monitor(MonitorInstancesRequest request,
            ResultCapture<MonitorInstancesResult> extractor);

    /**
     * Performs an action.
     */
    void resetSourceDestCheck(ResetInstanceAttributeRequest request);

    /**
     * Performs an action.
     */
    void resetSourceDestCheck(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void reboot(RebootInstancesRequest request);

    /**
     * Performs an action.
     */
    void reboot(RebootInstancesRequest request, ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request);

    /**
     * Performs an action.
     */
    UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request,
            ResultCapture<UnmonitorInstancesResult> extractor);

    /**
     * Performs an action.
     */
    void modifyAttribute(ModifyInstanceAttributeRequest request);

    /**
     * Performs an action.
     */
    void modifyAttribute(ModifyInstanceAttributeRequest request,
            ResultCapture<Void> extractor);

    /**
     * Performs an action.
     */
    void resetKernel(ResetInstanceAttributeRequest request);

    /**
     * Performs an action.
     */
    void resetKernel(ResetInstanceAttributeRequest request, ResultCapture<Void>
            extractor);
}
