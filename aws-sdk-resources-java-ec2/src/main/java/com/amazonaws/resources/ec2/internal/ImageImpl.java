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

import java.util.List;

import com.amazonaws.resources.ResultCapture;
import com.amazonaws.resources.ec2.Image;
import com.amazonaws.resources.internal.ActionResult;
import com.amazonaws.resources.internal.CodecUtils;
import com.amazonaws.resources.internal.ResourceCodec;
import com.amazonaws.resources.internal.ResourceImpl;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.ModifyImageAttributeRequest;
import com.amazonaws.services.ec2.model.ProductCode;
import com.amazonaws.services.ec2.model.ResetImageAttributeRequest;
import com.amazonaws.services.ec2.model.StateReason;
import com.amazonaws.services.ec2.model.Tag;

class ImageImpl implements Image {
    public static final ResourceCodec<Image> CODEC = new Codec();

    private final ResourceImpl resource;

    public ImageImpl(ResourceImpl resource) {
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
    public boolean load(DescribeImagesRequest request) {
        return load(request, null);
    }

    @Override
    public boolean load(DescribeImagesRequest request,
            ResultCapture<DescribeImagesResult> extractor) {

        return resource.load(request, extractor);
    }

    @Override
    public String getId() {
        return (String) resource.getIdentifier("Id");
    }

    @Override
    public String getDescription() {
        return (String) resource.getAttribute("Description");
    }

    @Override
    public String getRootDeviceType() {
        return (String) resource.getAttribute("RootDeviceType");
    }

    @Override
    public String getImageType() {
        return (String) resource.getAttribute("ImageType");
    }

    @Override
    public String getKernelId() {
        return (String) resource.getAttribute("KernelId");
    }

    @Override
    public StateReason getStateReason() {
        return (StateReason) resource.getAttribute("StateReason");
    }

    @Override
    public String getOwnerId() {
        return (String) resource.getAttribute("OwnerId");
    }

    @Override
    public List<ProductCode> getProductCodes() {
        return (List<ProductCode>) resource.getAttribute("ProductCodes");
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
    public String getImageOwnerAlias() {
        return (String) resource.getAttribute("ImageOwnerAlias");
    }

    @Override
    public String getImageLocation() {
        return (String) resource.getAttribute("ImageLocation");
    }

    @Override
    public String getName() {
        return (String) resource.getAttribute("Name");
    }

    @Override
    public List<Tag> getTags() {
        return (List<Tag>) resource.getAttribute("Tags");
    }

    @Override
    public String getRootDeviceName() {
        return (String) resource.getAttribute("RootDeviceName");
    }

    @Override
    public String getState() {
        return (String) resource.getAttribute("State");
    }

    @Override
    public String getRamdiskId() {
        return (String) resource.getAttribute("RamdiskId");
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
    public String getVirtualizationType() {
        return (String) resource.getAttribute("VirtualizationType");
    }

    @Override
    public List<BlockDeviceMapping> getBlockDeviceMappings() {
        return (List<BlockDeviceMapping>)
                resource.getAttribute("BlockDeviceMappings");
    }

    @Override
    public Boolean getPublic() {
        return (Boolean) resource.getAttribute("Public");
    }

    @Override
    public void deregister(DeregisterImageRequest request) {
        deregister(request, null);
    }

    @Override
    public void deregister(DeregisterImageRequest request, ResultCapture<Void>
            extractor) {

        resource.performAction("Deregister", request, extractor);
    }

    @Override
    public void deregister() {
        deregister((ResultCapture<Void>)null);
    }

    @Override
    public void deregister(ResultCapture<Void> extractor) {
        DeregisterImageRequest request = new DeregisterImageRequest();
        deregister(request, extractor);
    }

    @Override
    public void resetAttribute(ResetImageAttributeRequest request) {
        resetAttribute(request, null);
    }

    @Override
    public void resetAttribute(ResetImageAttributeRequest request,
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

        ResetImageAttributeRequest request = new ResetImageAttributeRequest()
            .withAttribute(attribute);
        resetAttribute(request, extractor);
    }

    @Override
    public void modifyAttribute(ModifyImageAttributeRequest request) {
        modifyAttribute(request, null);
    }

    @Override
    public void modifyAttribute(ModifyImageAttributeRequest request,
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

        ModifyImageAttributeRequest request = new ModifyImageAttributeRequest()
            .withAttribute(attribute);
        modifyAttribute(request, extractor);
    }

    @Override
    public DescribeImageAttributeResult describeAttribute(
            DescribeImageAttributeRequest request) {

        return describeAttribute(request, null);
    }

    @Override
    public DescribeImageAttributeResult describeAttribute(
            DescribeImageAttributeRequest request,
            ResultCapture<DescribeImageAttributeResult> extractor) {

        ActionResult result = resource.performAction("DescribeAttribute",
                request, extractor);

        if (result == null) return null;
        return (DescribeImageAttributeResult) result.getData();
    }

    @Override
    public DescribeImageAttributeResult describeAttribute(String attribute) {
        return describeAttribute(attribute,
                (ResultCapture<DescribeImageAttributeResult>)null);
    }

    @Override
    public DescribeImageAttributeResult describeAttribute(String attribute,
            ResultCapture<DescribeImageAttributeResult> extractor) {

        DescribeImageAttributeRequest request = new
                DescribeImageAttributeRequest()

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

    private static class Codec implements ResourceCodec<Image> {
        @Override
        public Image transform(ResourceImpl resource) {
            return new ImageImpl(resource);
        }
    }
}
