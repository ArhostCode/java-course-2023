package edu.project3.collector;

import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.components.MetricComponent;
import edu.project3.model.metric.components.MetricTable;
import java.util.Collections;
import java.util.List;

public class TopResourcesMetricCollector extends TopMetricCollector<String> {
    public TopResourcesMetricCollector() {
        super("Запрашиваемые ресурсы");
    }

    @Override
    protected List<MetricComponent> createComponents(List<TopElement<String>> topElements) {
        final int topCount = 5;
        MetricTable table = new MetricTable.ExtendedTableBuilder<TopElement<String>>()
            .headers("Ресурс", "Количество")
            .rows(topElements)
            .stringExtractors(List.of(
                TopElement::element,
                element -> element.count().toString()
            ))
            .rowsCount(topCount)
            .build();
        return Collections.singletonList(table);
    }

    @Override
    protected String extractElement(NginxLog log) {
        return log.request().url();
    }
}
