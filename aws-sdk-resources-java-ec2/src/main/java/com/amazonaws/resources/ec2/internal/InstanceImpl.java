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
import com.amazonaws.resources.ec2.Image;
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.KeyPair;
import com.amazonaws.resources.ec2.PlacementGroup;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.VolumeCollection;
import com.amazonaws.resources.ec2.Vpc;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceCollectionImpl;
import com.amazonaws.resources.internal.ResourceImpl;
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
    public KeyPair getKeyPair() {
        ResourceImpl result = resource.getReference("KeyPair");
        if (result == null) return null;
        return new KeyPairImpl(result);
    }

    @Override
    public Image getImage() {
        ResourceImpl result = resource.getReference("Image");
        if (result == null) return null;
        return new ImageImpl(result);
    }

    @Override
    public PlacementGroup getPlacementGroup() {
        ResourceImpl result = resource.getReference("PlacementGroup");
        if (result == null) return null;
        return new PlacementGroupImpl(result);
    }

    @Override
    public Subnet getSubnet() {
        ResourceImpl result = resource.getReference("Subnet");
        if (result == null) return null;
        return new SubnetImpl(result);
    }

    @Override
    public VolumeCollection getVolumes() {
        return getVolumes(null);
    }

    @Override
    public VolumeCollection getVolumes(DescribeVolumesRequest request) {
        ResourceCollectionImpl result = resource.getCollection("Volumes",
                request);

        if (result == null) return null;
        return new VolumeCollectionImpl(result);
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
    public TerminateInstancesResult terminate() {
        return terminate((ResultCapture<TerminateInstancesResult>)null);
    }

    @Override
    public TerminateInstancesResult terminate(
            ResultCapture<TerminateInstancesResult> extractor) {

        TerminateInstancesRequest request = new TerminateInstancesRequest();
        return terminate(request, extractor);
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
    public void resetRamdisk() {
        resetRamdisk((ResultCapture<Void>)null);
    }

    @Override
    public void resetRamdisk(ResultCapture<Void> extractor) {
        ResetInstanceAttributeRequest request = new
                ResetInstanceAttributeRequest();

        resetRamdisk(request, extractor);
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
    public StartInstancesResult start() {
        return start((ResultCapture<StartInstancesResult>)null);
    }

    @Override
    public StartInstancesResult start(ResultCapture<StartInstancesResult>
            extractor) {

        StartInstancesRequest request = new StartInstancesRequest();
        return start(request, extractor);
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
    public GetConsoleOutputResult consoleOutput() {
        return consoleOutput((ResultCapture<GetConsoleOutputResult>)null);
    }

    @Override
    public GetConsoleOutputResult consoleOutput(
            ResultCapture<GetConsoleOutputResult> extractor) {

        GetConsoleOutputRequest request = new GetConsoleOutputRequest();
        return consoleOutput(request, extractor);
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
    public DetachVolumeResult detachVolume(DetachVolumeRequest request) {
        return detachVolume(request, null);
    }

    @Override
    public DetachVolumeResult detachVolume(DetachVolumeRequest request,
            ResultCapture<DetachVolumeResult> extractor) {

        ActionResult result = resource.performAction("DetachVolume", request,
                extractor);

        if (result == null) return null;
        return (DetachVolumeResult) result.getData();
    }

    @Override
    public AttachVolumeResult attachVolume(AttachVolumeRequest request) {
        return attachVolume(request, null);
    }

    @Override
    public AttachVolumeResult attachVolume(AttachVolumeRequest request,
            ResultCapture<AttachVolumeResult> extractor) {

        ActionResult result = resource.performAction("AttachVolume", request,
                extractor);

        if (result == null) return null;
        return (AttachVolumeResult) result.getData();
    }

    @Override
    public AttachVolumeResult attachVolume(String device, String volumeId) {
        return attachVolume(device, volumeId,
                (ResultCapture<AttachVolumeResult>)null);
    }

    @Override
    public AttachVolumeResult attachVolume(String device, String volumeId,
            ResultCapture<AttachVolumeResult> extractor) {

        AttachVolumeRequest request = new AttachVolumeRequest()
            .withDevice(device)
            .withVolumeId(volumeId);
        return attachVolume(request, extractor);
    }

    @Override
    public Image createImage(CreateImageRequest request) {
        return createImage(request, null);
    }

    @Override
    public Image createImage(CreateImageRequest request,
            ResultCapture<CreateImageResult> extractor) {

        ActionResult result = resource.performAction("CreateImage", request,
                extractor);

        if (result == null) return null;
        return new ImageImpl(result.getResource());
    }

    @Override
    public Image createImage(String name) {
        return createImage(name, (ResultCapture<CreateImageResult>)null);
    }

    @Override
    public Image createImage(String name, ResultCapture<CreateImageResult>
            extractor) {

        CreateImageRequest request = new CreateImageRequest()
            .withName(name);
        return createImage(request, extractor);
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
    public StopInstancesResult stop() {
        return stop((ResultCapture<StopInstancesResult>)null);
    }

    @Override
    public StopInstancesResult stop(ResultCapture<StopInstancesResult> extractor
            ) {

        StopInstancesRequest request = new StopInstancesRequest();
        return stop(request, extractor);
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
    public GetPasswordDataResult passwordData() {
        return passwordData((ResultCapture<GetPasswordDataResult>)null);
    }

    @Override
    public GetPasswordDataResult passwordData(
            ResultCapture<GetPasswordDataResult> extractor) {

        GetPasswordDataRequest request = new GetPasswordDataRequest();
        return passwordData(request, extractor);
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
    public void resetAttribute(String attribute) {
        resetAttribute(attribute, (ResultCapture<Void>)null);
    }

    @Override
    public void resetAttribute(String attribute, ResultCapture<Void> extractor)
            {

        ResetInstanceAttributeRequest request = new
                ResetInstanceAttributeRequest()

            .withAttribute(attribute);
        resetAttribute(request, extractor);
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
    public MonitorInstancesResult monitor() {
        return monitor((ResultCapture<MonitorInstancesResult>)null);
    }

    @Override
    public MonitorInstancesResult monitor(ResultCapture<MonitorInstancesResult>
            extractor) {

        MonitorInstancesRequest request = new MonitorInstancesRequest();
        return monitor(request, extractor);
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
    public void reboot() {
        reboot((ResultCapture<Void>)null);
    }

    @Override
    public void reboot(ResultCapture<Void> extractor) {
        RebootInstancesRequest request = new RebootInstancesRequest();
        reboot(request, extractor);
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
    public void resetSourceDestCheck() {
        resetSourceDestCheck((ResultCapture<Void>)null);
    }

    @Override
    public void resetSourceDestCheck(ResultCapture<Void> extractor) {
        ResetInstanceAttributeRequest request = new
                ResetInstanceAttributeRequest();

        resetSourceDestCheck(request, extractor);
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
    public UnmonitorInstancesResult unmonitor() {
        return unmonitor((ResultCapture<UnmonitorInstancesResult>)null);
    }

    @Override
    public UnmonitorInstancesResult unmonitor(
            ResultCapture<UnmonitorInstancesResult> extractor) {

        UnmonitorInstancesRequest request = new UnmonitorInstancesRequest();
        return unmonitor(request, extractor);
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
    public void modifyAttribute(String attribute) {
        modifyAttribute(attribute, (ResultCapture<Void>)null);
    }

    @Override
    public void modifyAttribute(String attribute, ResultCapture<Void> extractor)
            {

        ModifyInstanceAttributeRequest request = new
                ModifyInstanceAttributeRequest()

            .withAttribute(attribute);
        modifyAttribute(request, extractor);
    }

    @Override
    public DescribeInstanceAttributeResult describeAttribute(
            DescribeInstanceAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeInstanceAttributeResult describeAttribute(
            DescribeInstanceAttributeRequest request,
            ResultCapture<DescribeInstanceAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeInstanceAttributeResult) result.getData();
    }

    @Override
    public DescribeInstanceAttributeResult describeAttribute(String attribute) {
        return describeAttribute(attribute,
                (ResultCapture<DescribeInstanceAttributeResult>)null);
    }

    @Override
    public DescribeInstanceAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeInstanceAttributeResult> extractor) {

        DescribeInstanceAttributeRequest request = new
                DescribeInstanceAttributeRequest()

            .withAttribute(attribute);
        return describeAttribute(request, extractor);
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
    public void resetKernel(ResetInstanceAttributeRequest request) {
        resetKernel(request, null);
    }

    @Override
    public void resetKernel(ResetInstanceAttributeRequest request,
            ResultCapture<Void> extractor) {

        resource.performAction("ResetKernel", request, extractor);
    }

    @Override
    public void resetKernel() {
        resetKernel((ResultCapture<Void>)null);
    }

    @Override
    public void resetKernel(ResultCapture<Void> extractor) {
        ResetInstanceAttributeRequest request = new
                ResetInstanceAttributeRequest();

        resetKernel(request, extractor);
    }

    private static class Codec implements ResourceCodec<Instance> {
        @Override
        public Instance transform(ResourceImpl resource) {
            return new InstanceImpl(resource);
        }
    }
}
