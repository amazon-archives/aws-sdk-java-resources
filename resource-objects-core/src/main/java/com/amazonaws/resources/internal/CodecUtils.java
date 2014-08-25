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
package com.amazonaws.resources.internal;

import java.util.ArrayList;
import java.util.List;

public final class CodecUtils {

    public static <T> List<T> transform(
            List<ResourceImpl> resources,
            ResourceCodec<T> codec) {

        if (resources == null) {
            return null;
        }

        List<T> result = new ArrayList<T>(resources.size());
        for (ResourceImpl resource : resources) {
            result.add(codec.transform(resource));
        }

        return result;
    }

    private CodecUtils() {
    }
}
