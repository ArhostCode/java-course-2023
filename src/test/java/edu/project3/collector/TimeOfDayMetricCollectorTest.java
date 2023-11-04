package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TimeOfDayMetricCollectorTest extends MetricCollectorTest {
    @Test
    @DisplayName("Тестирование TimeOfDayMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new TimeOfDayMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("Время суток", List.of(
                new MetricHeader("Время суток", 4),
                new MetricTable()
                    .addHeaders("Время суток", "Количество запросов")
                    .addRowElements("Утро", "5")
                    .addRowElements("День", "0")
                    .addRowElements("Вечер", "0")
                    .addRowElements("Ночь", "0")
            ));
    }
}
