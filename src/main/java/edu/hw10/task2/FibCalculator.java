package edu.hw10.task2;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(long number);
}
