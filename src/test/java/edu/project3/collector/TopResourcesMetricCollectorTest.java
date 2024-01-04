package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TopResourcesMetricCollectorTest extends MetricCollectorTest {

    @Test
    @DisplayName("Тестирование TopResourcesMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new TopResourcesMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("Запрашиваемые ресурсы", List.of(
                new MetricHeader("Запрашиваемые ресурсы", 4),
                new MetricTable()
                    .addHeaders("Ресурс", "Количество")
                    .addRowElements("/help", "4")
                    .addRowElements("/help2", "1")
            ));
    }
}
