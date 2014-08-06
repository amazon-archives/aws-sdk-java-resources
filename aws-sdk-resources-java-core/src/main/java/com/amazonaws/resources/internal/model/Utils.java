package com.amazonaws.resources.internal.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;

final class Utils {

    public static <T> List<T> makeImmutable(List<? extends T> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    public static <T> List<T> makeImmutableOrNull(List<? extends T> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public static <K, V> Map<K, V> makeImmutable(
            Map<? extends K, ? extends V> map) {

        if (map == null) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(map);
    }
}
