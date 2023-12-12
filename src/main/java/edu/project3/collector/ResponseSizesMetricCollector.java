package edu.project3.collector;

import edu.project3.model.log.LogsContainer;
import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.utils.MetricComponentsUtils;
import java.util.Comparator;
import java.util.List;

public class ResponseSizesMetricCollector implements MetricCollector {

    @Override
    public Metric collect(LogsContainer container) {
        Metric metric = MetricComponentsUtils.createMetricWithHeader("Размеры ответов");
        List<UrlWithResponseSize> urlWithResponseSizes = container
            .logs()
            .stream()
            .map(log -> new UrlWithResponseSize(log.request().url(), log.response().bytesSend()))
            .sorted(Comparator.comparing(UrlWithResponseSize::responseSize).reversed())
            .distinct()
            .toList();
        final int topCount = 5;
        MetricTable table = new MetricTable.ExtendedTableBuilder<UrlWithResponseSize>()
            .headers("Запрос", "Размер ответа")
            .rows(urlWithResponseSizes)
            .stringExtractors(List.of(
                urlWithResponseSize -> urlWithResponseSize.url,
                urlWithResponseSize -> urlWithResponseSize.responseSize.toString()
            ))
            .rowsCount(topCount)
            .build();
        metric.components().add(table);
        return metric;
    }

    private record UrlWithResponseSize(String url, Integer responseSize) {
    }
}
