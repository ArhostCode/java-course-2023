package edu.hw10.task2.serializer;

import java.util.HashMap;
import java.util.Map;

public final class Serializers {

    private Serializers() {
    }

    public static Map<Class<?>, Serializer<?>> defaultSerializers() {
        Map<Class<?>, Serializer<?>> serializers = new HashMap<>();
        serializers.put(Long.class, new LongSerializer());
        serializers.put(long.class, new LongSerializer());
        return serializers;
    }

}
