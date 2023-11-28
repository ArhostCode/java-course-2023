package edu.hw8.task2;

public interface ThreadPool extends AutoCloseable {

    void execute(Runnable runnable);

    void start();

    void awaitTermination();

    @Override
    void close();
}
