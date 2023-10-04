package edu.hw1.task3;

import java.util.Arrays;

public final class NestedArrays {
    private NestedArrays() {
    }

    public static boolean isNestable(int[] value, int[] external) {
        if (value == null || external == null) {
            return false;
        }

        // Min and Max in value
        int minimumValue = Arrays.stream(value).min().orElse(0);
        int maxValue = Arrays.stream(value).max().orElse(0);

        // Min and Max in external
        int minimumExternal = Arrays.stream(external).min().orElse(0);
        int maxExternal = Arrays.stream(external).max().orElse(0);

        return minimumValue > minimumExternal && maxValue < maxExternal;
    }
}
