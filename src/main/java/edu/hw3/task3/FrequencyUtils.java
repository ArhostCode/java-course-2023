package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FrequencyUtils {
    private FrequencyUtils() {
    }

    public static <T> Map<T, Integer> computeFreqDictionary(List<T> objects) {
        if (objects == null) {
            throw new NullPointerException("List objects must be not null");
        }
        Map<T, Integer> freqMap = new HashMap<>();
        objects.forEach(obj -> {
            if (freqMap.containsKey(obj)) {
                freqMap.compute(obj, (object, value) -> (value + 1));
            } else {
                freqMap.put(obj, 1);
            }
        });
        return freqMap;
    }
}
