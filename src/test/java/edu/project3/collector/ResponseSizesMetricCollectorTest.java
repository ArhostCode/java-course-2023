package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ResponseSizesMetricCollectorTest extends MetricCollectorTest {
    @Test
    @DisplayName("Тестирование ResponseSizesMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new ResponseSizesMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("Размеры ответов", List.of(
                new MetricHeader("Размеры ответов", 4),
                new MetricTable()
                    .addHeaders("Запрос", "Размер ответа")
                    .addRowElements("/help", "2000")
                    .addRowElements("/help2", "20")
            ));
    }
}
