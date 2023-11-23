package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiCounter {

    private final AtomicInteger counter;

    public MultiCounter() {
        counter = new AtomicInteger(0);
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
