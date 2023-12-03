package edu.hw10.task2.serializer;

public interface Serializer<T> {

    String serialize(Object object);

    T deserialize(String string);

}
