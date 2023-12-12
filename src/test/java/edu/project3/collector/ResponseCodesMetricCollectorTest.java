package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ResponseCodesMetricCollectorTest extends MetricCollectorTest {
    @Test
    @DisplayName("Тестирование ResponseCodesMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new ResponseCodesMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("Коды ответа", List.of(
                new MetricHeader("Коды ответа", 4),
                new MetricTable()
                    .addHeaders("Код", "Имя", "Количество")
                    .addRowElements("200", "OK","4")
                    .addRowElements("500", "Internal Server Error","1")
            ));
    }
}
