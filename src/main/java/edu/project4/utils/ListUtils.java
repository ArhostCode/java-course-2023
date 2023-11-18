package edu.project4.utils;

import java.util.List;

public final class ListUtils {
    private ListUtils() {
    }

    public static <T> T random(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }
}
