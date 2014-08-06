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
