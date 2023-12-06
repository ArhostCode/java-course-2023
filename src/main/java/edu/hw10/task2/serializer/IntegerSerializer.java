package edu.hw10.task2.serializer;

public class IntegerSerializer implements Serializer<Integer> {
    @Override
    public String serialize(Object integer) {
        if (integer == null) {
            return null;
        }
        return String.valueOf(integer);
    }

    @Override
    public Integer deserialize(String string) {
        return Integer.parseInt(string);
    }
}
