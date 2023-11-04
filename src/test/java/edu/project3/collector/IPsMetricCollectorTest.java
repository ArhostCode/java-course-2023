package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class IPsMetricCollectorTest extends MetricCollectorTest {

    @Test
    @DisplayName("Тестирование IPsMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new IPsMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("IP адреса", List.of(
                new MetricHeader("IP адреса", 4),
                new MetricTable()
                    .addHeaders("IP адрес", "Количество")
                    .addRowElements("1.1.1.1", "3")
                    .addRowElements("2.2.2.2", "1")
                    .addRowElements("5.5.5.5", "1")
            ));
    }

}
