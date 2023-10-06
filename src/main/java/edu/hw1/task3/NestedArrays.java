package edu.hw1.task3;

import java.util.Arrays;

public final class NestedArrays {
    private NestedArrays() {
    }

    public static boolean isNestable(int[] internal, int[] external) {
        if (internal == null || external == null || internal.length == 0 || external.length == 0) {
            return false;
        }

        // Min and Max in internal
        int minimumInternal = Arrays.stream(internal).min().getAsInt();
        int maxInternal = Arrays.stream(internal).max().getAsInt();

        // Min and Max in external
        int minimumExternal = Arrays.stream(external).min().getAsInt();
        int maxExternal = Arrays.stream(external).max().getAsInt();

        return minimumInternal > minimumExternal && maxInternal < maxExternal;
    }
}
