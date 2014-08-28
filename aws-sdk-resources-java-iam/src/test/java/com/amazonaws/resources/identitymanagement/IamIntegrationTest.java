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
package com.amazonaws.resources.identitymanagement;

import org.junit.AfterClass;
import org.junit.Test;

import com.amazonaws.resources.ServiceBuilder;
import com.amazonaws.services.identitymanagement.model.StatusType;

public class IamIntegrationTest {

    private static final IdentityManagement iam = ServiceBuilder
            .forService(IdentityManagement.class)
            .build();

    @Test
    public void testIt() {
        Group badDudes = iam.getGroup("BadDudes");

        for (User user : badDudes.getUsers()) {
            System.out.println(user.getArn());

            for (AccessKey old : user.getAccessKeys()) {
                System.out.println("  Deleting: " + old.getId());

                if (old.getStatus().equals(StatusType.Active.toString())) {
                    old.deactivate();
                }

                old.delete();
            }

            AccessKey key = user.createAccessKey();
            System.out.println("  Created: " + key.getId());
        }
    }

    @AfterClass
    public static void cleanUp() {
        iam.client().shutdown();
    }
}
