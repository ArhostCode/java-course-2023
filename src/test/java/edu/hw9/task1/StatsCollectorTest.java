package edu.hw9.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatsCollectorTest {

    @Test
    @DisplayName("Тестирование StatsCollector#stats")
    public void stats_shouldReturnAllStats() {
        StatsCollector collector = new StatsCollector(5);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> collector.push("metric1", 1, 4, 3, 4, 5));
        executorService.execute(() -> collector.push("metric2", 1, 9, 3, 4, 5));
        executorService.execute(() -> collector.push("metric3", 0, 44, 3, 4, 5));

        var stats = collector.stats();
        Assertions.assertThat(stats).containsExactlyInAnyOrder(
            new Metric("metric1", 17, 3.4, 1, 5),
            new Metric("metric2", 22, 4.4, 1, 9),
            new Metric("metric3", 56, 11.2, 0, 44)
        );
    }
}
