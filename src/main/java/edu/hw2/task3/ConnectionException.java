package edu.hw2.task3;

public class ConnectionException extends RuntimeException {

    private final Exception cause;

    public ConnectionException() {
        this.cause = null;
    }

    public ConnectionException(ConnectionException cause) {
        this.cause = cause;
    }
}
