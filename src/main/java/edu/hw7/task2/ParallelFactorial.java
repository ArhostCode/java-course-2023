package edu.hw7.task2;

import java.util.stream.IntStream;

public final class ParallelFactorial {

    private ParallelFactorial() {
    }

    public static int computeFactorial(int n) {
        return IntStream.rangeClosed(1, n)
            .parallel()
            .reduce((i1, i2) -> i1 * i2)
            .orElseThrow();
    }
}
