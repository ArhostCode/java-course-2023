package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class FrequencyUtils {
    private FrequencyUtils() {
    }

    public static <T> Map<T, Integer> computeFreqDictionary(List<T> objects) {
        if (objects == null) {
            throw new NullPointerException("List objects must be not null");
        }
        return objects.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(o -> 1)));
    }
}
