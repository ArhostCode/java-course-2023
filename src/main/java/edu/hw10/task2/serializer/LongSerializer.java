package edu.hw10.task2.serializer;

public class LongSerializer implements Serializer<Long> {
    @Override
    public String serialize(Object object) {
        return object.toString();
    }

    @Override
    public Long deserialize(String string) {
        return Long.parseLong(string);
    }
}
