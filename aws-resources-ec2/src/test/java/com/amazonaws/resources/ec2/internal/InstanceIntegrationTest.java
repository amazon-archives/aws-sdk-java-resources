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

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.resources.ServiceBuilder;
import com.amazonaws.resources.ec2.EC2;
import com.amazonaws.resources.ec2.Instance;
import com.amazonaws.resources.ec2.Subnet;
import com.amazonaws.resources.ec2.Vpc;

public class InstanceIntegrationTest {

    private static final EC2 ec2 = ServiceBuilder
            .forService(EC2.class)
            .withRegion(Region.getRegion(Regions.US_WEST_2))
            .build();

    @Test
    @Ignore
    public void testEC2() throws IOException {
        for (Vpc vpc : ec2.getVpcs()) {
            System.out.println(vpc.getId());
            System.out.println("  state = " + vpc.getState());

            for (Subnet subnet : vpc.getSubnets()) {
                System.out.println("  subnet " + subnet.getId());
                for (Instance instance : subnet.getInstances()) {
                    System.out.println("    " + instance.getId() + ": "
                            + instance.getStateReason());
                }
            }
        }
    }

    @AfterClass
    public static void cleanUp() {
        ec2.client().shutdown();
    }
}
