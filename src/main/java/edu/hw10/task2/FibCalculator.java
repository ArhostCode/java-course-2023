package edu.hw10.task2;

public interface FibCalculator {
    @Cache
    long fib(int number);
}
