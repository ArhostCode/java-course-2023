package edu.hw9.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;

public class StatsCollector {

    private final List<Future<Metric>> futures = new CopyOnWriteArrayList<>();
    private final ExecutorService executorService;

    public StatsCollector(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void push(String metricName, double... values) {
        futures.add(executorService.submit(() -> collect(metricName, values)));
    }

    @SneakyThrows
    public List<Metric> stats() {
        List<Metric> metrics = new ArrayList<>();
        for (Future<Metric> future : futures) {
            metrics.add(future.get());
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
