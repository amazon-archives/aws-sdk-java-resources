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
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
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

class InstanceImpl implements Instance {
    public static final ResourceCodec<Instance> CODEC = new Codec();

    private final ResourceImpl resource;

    public InstanceImpl(ResourceImpl resource) {
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
    public boolean load(DescribeInstancesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeInstancesRequest request,
            ResultCapture<DescribeInstancesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public IamInstanceProfile getIamInstanceProfile() {
        return (IamInstanceProfile) resource.getAttribute("IamInstanceProfile");
    }

    @Override
    public String getClientToken() {
        return (String) resource.getAttribute("ClientToken");
    }

    @Override
    public String getImageId() {
        return (String) resource.getAttribute("ImageId");
    }

    @Override
    public String getKernelId() {
        return (String) resource.getAttribute("KernelId");
    }

    @Override
    public List<InstanceNetworkInterface> getNetworkInterfaces() {
        return (List<InstanceNetworkInterface>)
                resource.getAttribute("NetworkInterfaces");
    }

    @Override
    public List<ProductCode> getProductCodes() {
        return (List<ProductCode>) resource.getAttribute("ProductCodes");
    }

    @Override
    public Integer getAmiLaunchIndex() {
        return (Integer) resource.getAttribute("AmiLaunchIndex");
    }

    @Override
    public String getInstanceId() {
        return (String) resource.getAttribute("InstanceId");
    }

    @Override
    public String getSriovNetSupport() {
        return (String) resource.getAttribute("SriovNetSupport");
    }

    @Override
    public String getArchitecture() {
        return (String) resource.getAttribute("Architecture");
    }

    @Override
    public List<GroupIdentifier> getSecurityGroups() {
        return (List<GroupIdentifier>) resource.getAttribute("SecurityGroups");
    }

    @Override
    public Boolean getEbsOptimized() {
        return (Boolean) resource.getAttribute("EbsOptimized");
    }

    @Override
    public Placement getPlacement() {
        return (Placement) resource.getAttribute("Placement");
    }

    @Override
    public String getPublicDnsName() {
        return (String) resource.getAttribute("PublicDnsName");
    }

    @Override
    public String getVpcId() {
        return (String) resource.getAttribute("VpcId");
    }

    @Override
    public String getPrivateDnsName() {
        return (String) resource.getAttribute("PrivateDnsName");
    }

    @Override
    public String getRootDeviceName() {
        return (String) resource.getAttribute("RootDeviceName");
    }

    @Override
    public String getRamdiskId() {
        return (String) resource.getAttribute("RamdiskId");
    }

    @Override
    public String getPrivateIpAddress() {
        return (String) resource.getAttribute("PrivateIpAddress");
    }

    @Override
    public String getHypervisor() {
        return (String) resource.getAttribute("Hypervisor");
    }

    @Override
    public String getPlatform() {
        return (String) resource.getAttribute("Platform");
    }

    @Override
    public List<InstanceBlockDeviceMapping> getBlockDeviceMappings() {
        return (List<InstanceBlockDeviceMapping>)
                resource.getAttribute("BlockDeviceMappings");
    }

    @Override
    public String getRootDeviceType() {
        return (String) resource.getAttribute("RootDeviceType");
    }

    @Override
    public Boolean getSourceDestCheck() {
        return (Boolean) resource.getAttribute("SourceDestCheck");
    }

    @Override
    public String getSpotInstanceRequestId() {
        return (String) resource.getAttribute("SpotInstanceRequestId");
    }

    @Override
    public StateReason getStateReason() {
        return (StateReason) resource.getAttribute("StateReason");
    }

    @Override
    public String getPublicIpAddress() {
        return (String) resource.getAttribute("PublicIpAddress");
    }

    @Override
    public String getInstanceType() {
        return (String) resource.getAttribute("InstanceType");
    }

    @Override
    public List<Tag> getTags() {
        return (List<Tag>) resource.getAttribute("Tags");
    }

    @Override
    public String getStateTransitionReason() {
        return (String) resource.getAttribute("StateTransitionReason");
    }

    @Override
    public InstanceState getState() {
        return (InstanceState) resource.getAttribute("State");
    }

    @Override
    public String getSubnetId() {
        return (String) resource.getAttribute("SubnetId");
    }

    @Override
    public Date getLaunchTime() {
        return (Date) resource.getAttribute("LaunchTime");
    }

    @Override
    public String getInstanceLifecycle() {
        return (String) resource.getAttribute("InstanceLifecycle");
    }

    @Override
    public Monitoring getMonitoring() {
        return (Monitoring) resource.getAttribute("Monitoring");
    }

    @Override
    public String getVirtualizationType() {
        return (String) resource.getAttribute("VirtualizationType");
    }

    @Override
    public String getKeyName() {
        return (String) resource.getAttribute("KeyName");
    }

    @Override
    public Vpc getVpc() {
        ResourceImpl result = resource.getReference("Vpc");
        if (result == null) return null;
        return new VpcImpl(result);
    }

    @Override
    public Subnet getSubnet() {
        ResourceImpl result = resource.getReference("Subnet");
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public TerminateInstancesResult terminate(TerminateInstancesRequest request)
            {

        return terminate(request, null);
    }

    @Override
    public TerminateInstancesResult terminate(TerminateInstancesRequest request,
            ResultCapture<TerminateInstancesResult> extractor) {

        ActionResult result = resource.performAction("Terminate", request,
                extractor);

        if (result == null) return null;
        return (TerminateInstancesResult) result.getData();
    }

    @Override
    public void resetRamdisk(ResetInstanceAttributeRequest request) {
        resetRamdisk(request, null);
    }

    @Override
    public void resetRamdisk(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetRamdisk", request, extractor);
    }

    @Override
    public StartInstancesResult start(StartInstancesRequest request) {
        return start(request, null);
    }

    @Override
    public StartInstancesResult start(StartInstancesRequest request,
            ResultCapture<StartInstancesResult> extractor) {

        ActionResult result = resource.performAction("Start", request,
                extractor);

        if (result == null) return null;
        return (StartInstancesResult) result.getData();
    }

    @Override
    public GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request)
            {

        return consoleOutput(request, null);
    }

    @Override
    public GetConsoleOutputResult consoleOutput(GetConsoleOutputRequest request,
            ResultCapture<GetConsoleOutputResult> extractor) {

        ActionResult result = resource.performAction("ConsoleOutput", request,
                extractor);

        if (result == null) return null;
        return (GetConsoleOutputResult) result.getData();
    }

    @Override
    public void reportStatus(ReportInstanceStatusRequest request) {
        reportStatus(request, null);
    }

    @Override
    public void reportStatus(ReportInstanceStatusRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ReportStatus", request, extractor);
    }

    @Override
    public StopInstancesResult stop(StopInstancesRequest request) {
        return stop(request, null);
    }

    @Override
    public StopInstancesResult stop(StopInstancesRequest request,
            ResultCapture<StopInstancesResult> extractor) {

        ActionResult result = resource.performAction("Stop", request,
                extractor);

        if (result == null) return null;
        return (StopInstancesResult) result.getData();
    }

    @Override
    public GetPasswordDataResult passwordData(GetPasswordDataRequest request) {
        return passwordData(request, null);
    }

    @Override
    public GetPasswordDataResult passwordData(GetPasswordDataRequest request,
            ResultCapture<GetPasswordDataResult> extractor) {

        ActionResult result = resource.performAction("PasswordData", request,
                extractor);

        if (result == null) return null;
        return (GetPasswordDataResult) result.getData();
    }

    @Override
    public void resetAttribute(ResetInstanceAttributeRequest request) {
        resetAttribute(request, null);
    }

    @Override
    public void resetAttribute(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetAttribute", request, extractor);
    }

    @Override
    public MonitorInstancesResult monitor(MonitorInstancesRequest request) {
        return monitor(request, null);
    }

    @Override
    public MonitorInstancesResult monitor(MonitorInstancesRequest request,
            ResultCapture<MonitorInstancesResult> extractor) {

        ActionResult result = resource.performAction("Monitor", request,
                extractor);

        if (result == null) return null;
        return (MonitorInstancesResult) result.getData();
    }

    @Override
    public void resetSourceDestCheck(ResetInstanceAttributeRequest request) {
        resetSourceDestCheck(request, null);
    }

    @Override
    public void resetSourceDestCheck(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetSourceDestCheck", request, extractor);
    }

    @Override
    public void reboot(RebootInstancesRequest request) {
        reboot(request, null);
    }

    @Override
    public void reboot(RebootInstancesRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Reboot", request, extractor);
    }

    @Override
    public UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request)
            {

        return unmonitor(request, null);
    }

    @Override
    public UnmonitorInstancesResult unmonitor(UnmonitorInstancesRequest request,
            ResultCapture<UnmonitorInstancesResult> extractor) {

        ActionResult result = resource.performAction("Unmonitor", request,
                extractor);

        if (result == null) return null;
        return (UnmonitorInstancesResult) result.getData();
    }

    @Override
    public void modifyAttribute(ModifyInstanceAttributeRequest request) {
        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifyInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ModifyAttribute", request, extractor);
    }

    @Override
    public void resetKernel(ResetInstanceAttributeRequest request) {
        resetKernel(request, null);
    }

    @Override
    public void resetKernel(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetKernel", request, extractor);
    }

    private static class Codec implements ResourceCodec<Instance> {
        @Override
        public Instance transform(ResourceImpl resource) {
            return new InstanceImpl(resource);
        }
    }
}
