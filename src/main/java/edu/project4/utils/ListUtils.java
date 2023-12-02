package edu.project4.utils;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class ListUtils {

    public static <T> T random(List<T> list) {
        return list.get((int) (ThreadLocalRandom.current().nextDouble() * list.size()));
    }
}
