package edu.hw8.task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class FibonacciComputer {

    private FibonacciComputer() {
    }

    public static int compute(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return compute(n - 1) + compute(n - 2);
    }

    public static List<Integer> computeList(List<Integer> list, int threadsCount) {
        ThreadPool threadPool = FixedThreadPool.create(threadsCount);
        threadPool.start();
        List<Integer> result = new CopyOnWriteArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int current = i;
            threadPool.execute(() -> result.add(FibonacciComputer.compute(list.get(current))));
        }
        threadPool.close();
        threadPool.awaitTermination();
        return result;
    }
}
