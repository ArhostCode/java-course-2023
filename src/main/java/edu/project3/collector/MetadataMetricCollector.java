package edu.project3.collector;

import edu.project3.model.log.LogsContainer;
import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.utils.MetricComponentsUtils;
import java.util.List;

public class MetadataMetricCollector implements MetricCollector {
    @Override
    public Metric collect(LogsContainer container) {
        Metric metric = MetricComponentsUtils.createMetricWithHeader("Общая информация");
        metric.components().add(
            new MetricTable()
                .addHeaders("Метрика", "Значение")
                .addRowElements("Файл(-ы)", "`" + String.join(", ", container.metadata().paths()) + "`")
                .addRowElements(
                    "Начальная дата",
                    container.metadata().from() == null ? "-" : container.metadata().from().toString()
                )
                .addRowElements(
                    "Конечная дата",
                    container.metadata().to() == null ? "-" : container.metadata().to().toString()
                )
                .addRowElements("Количество запросов", String.valueOf(container.logs().size()))
                .addRowElements("Средний размер ответа", getAverageResponseSize(container.logs()))
        );
        return metric;
    }

    private String getAverageResponseSize(List<NginxLog> logs) {
        return String.format(
            "%.2fb",
            logs.stream().map(log -> log.response().bytesSend()).mapToInt(Integer::intValue).average()
                .orElse(0)
        );
    }
}
