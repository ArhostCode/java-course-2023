package edu.hw10.task2.serializer;

public interface Serializer<T> {
    // Yeh, it`s maybe not right, but reflection knows any parameter as object
    String serialize(Object object);

    T deserialize(String string);

}
