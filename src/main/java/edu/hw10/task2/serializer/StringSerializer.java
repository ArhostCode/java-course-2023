package edu.hw10.task2.serializer;

public class StringSerializer implements Serializer<String> {
    @Override
    public String serialize(Object object) {
        if (object == null) {
            return null;
        }
        return (String) object;
    }

    @Override
    public String deserialize(String string) {
        return string;
    }
}
