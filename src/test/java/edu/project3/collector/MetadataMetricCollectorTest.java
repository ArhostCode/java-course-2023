package edu.project3.collector;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MetadataMetricCollectorTest extends MetricCollectorTest {

    @Test
    @DisplayName("Тестирование MetadataMetricCollector")
    public void collect_shouldReturnCorrectMetric() {
        Metric metric = new MetadataMetricCollector().collect(createLogsContainer());
        Assertions.assertThat(metric)
            .extracting("name", "components")
            .containsExactly("Общая информация", List.of(
                new MetricHeader("Общая информация", 4),
                new MetricTable()
                    .addHeaders("Метрика", "Значение")
                    .addRowElements("Файл(-ы)", "`test`")
                    .addRowElements("Начальная дата", "-999999999-01-01T00:00+18:00")
                    .addRowElements("Конечная дата", "+999999999-12-31T23:59:59.999999999-18:00")
                    .addRowElements("Количество запросов", "5")
                    .addRowElements("Средний размер ответа", "1604.00b")
            ));
    }
}
