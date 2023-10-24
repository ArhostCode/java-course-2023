package edu.project2.output;

public interface UserWriter {

    void write(String message);

    default void write(Message message) {
        write(message.getText());
    }

}
