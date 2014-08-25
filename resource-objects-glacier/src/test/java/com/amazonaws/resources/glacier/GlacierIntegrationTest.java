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
package com.amazonaws.resources.glacier;

import org.junit.AfterClass;
import org.junit.Test;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.resources.ServiceBuilder;

public class GlacierIntegrationTest {

    private static final Glacier glacier = ServiceBuilder
            .forService(Glacier.class)
            .withRegion(Region.getRegion(Regions.US_WEST_2))
            .build();

    @Test
    public void testGlacier() {
        for (Vault vault : glacier.getVaults()) {
            System.out.println(vault.getAccountId());
            System.out.println(vault.getName());
            System.out.println(vault.getSizeInBytes());
        }
    }

    @AfterClass
    public static void cleanUp() {
        glacier.client().shutdown();
    }
}
