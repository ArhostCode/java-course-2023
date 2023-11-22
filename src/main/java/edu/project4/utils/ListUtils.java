package edu.project4.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class ListUtils {
    private ListUtils() {
    }

    public static <T> T random(List<T> list) {
        return list.get((int) (ThreadLocalRandom.current().nextDouble() * list.size()));
    }
}
