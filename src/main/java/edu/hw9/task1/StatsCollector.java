package edu.hw9.task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector {

    private final List<Metric> metrics = new CopyOnWriteArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private final ExecutorService executorService;

    public StatsCollector(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void push(String metricName, double... values) {
        counter.incrementAndGet();
        executorService.execute(() -> {
            metrics.add(collect(metricName, values));
            counter.decrementAndGet();
        });
    }

    public List<Metric> stats() {
        // Await all tasks to finish, tasks may come from different threads during this method execution
        while (counter.get() != 0) {
        }
        return metrics;
    }

    private Metric collect(String metricName, double... values) {
        return new Metric(
            metricName,
            Arrays.stream(values).sum(),
            Arrays.stream(values).average()
                .orElseThrow(() -> new RuntimeException("No values in metric")),
            // Checks next no necessary, because first thrown exception
            Arrays.stream(values).min().orElseThrow(),
            Arrays.stream(values).max().orElseThrow()
        );
    }

}
