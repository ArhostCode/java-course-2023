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
            MetricInput metricInput = new MetricInput(metricName, values);
            metrics.add(collect(metricInput));
            counter.decrementAndGet();
        });
    }

    public List<Metric> stats() {
        // Await all tasks to finish, tasks may come from different threads during this method execution
        while (counter.get() != 0) {
        }
        return metrics;
    }

    private Metric collect(MetricInput metricInput) {
        return new Metric(
            metricInput.name(),
            Arrays.stream(metricInput.values()).sum(),
            Arrays.stream(metricInput.values()).average()
                .orElseThrow(() -> new RuntimeException("No values in metric")),
            // Checks next no necessary, because first thrown exception
            Arrays.stream(metricInput.values()).min().orElseThrow(),
            Arrays.stream(metricInput.values()).max().orElseThrow()
        );
    }

}
