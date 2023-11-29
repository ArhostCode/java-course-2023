package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MonteCarloPiComputer {

    private static final double MONTE_CARLO_CONST = 4.0;
    private static final double RADIUS = 0.5;

    public static double computePI(long iterationsCount) {
        double result = countRandomCollisions(iterationsCount);
        return MONTE_CARLO_CONST * (result / iterationsCount);
    }

    @SneakyThrows
    public static double computePIParallel(long n, int threadCount) {
        var executorService = Executors.newFixedThreadPool(threadCount);
        double count = 0;

        // Not using atomics here to speed up the process
        Future<Long>[] futures = new Future[threadCount];
        for (int thread = 0; thread < threadCount; thread++) {
            futures[thread] = executorService.submit(() -> countRandomCollisions(n / threadCount));
        }
        for (int i = 0; i < threadCount; i++) {
            count += futures[i].get();
        }
        return MONTE_CARLO_CONST * (count / n);
    }

    private long countRandomCollisions(long iterationsCount) {
        long count = 0;
        Random random = ThreadLocalRandom.current();
        for (long i = 0; i < iterationsCount; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isInSingleCircle(x, y)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isInSingleCircle(double x, double y) {
        return (x - RADIUS) * (x - RADIUS) + (y - RADIUS) * (y - RADIUS) <= RADIUS * RADIUS;
    }
}
