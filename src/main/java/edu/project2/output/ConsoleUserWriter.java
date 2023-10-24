package edu.project2.output;

public class ConsoleUserWriter implements UserWriter {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
