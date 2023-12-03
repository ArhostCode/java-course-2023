package edu.hw10.task2.serializer;

import java.util.Map;

public final class Serializers {

    private Serializers() {
    }

    public static Map<Class<?>, Serializer<?>> defaultSerializers() {
        return Map.of(
            Long.class, new LongSerializer(),
            long.class, new LongSerializer()
        );
    }

}
