package edu.project3.collector;

import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.components.MetricComponent;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.model.remote.HttpStatusCode;
import java.util.Collections;
import java.util.List;

public class ResponseCodesMetricCollector extends TopMetricCollector<Integer> {

    public ResponseCodesMetricCollector() {
        super("Коды ответа");
    }

    @Override
    protected List<MetricComponent> createComponents(List<TopElement<Integer>> topElements) {
        final int topCount = 5;
        MetricTable table = new MetricTable.ExtendedTableBuilder<TopElement<Integer>>()
            .headers("Код", "Имя", "Количество")
            .rows(topElements)
            .stringExtractors(List.of(
                element -> element.element().toString(),
                element -> HttpStatusCode.getByValue(element.element()).getDescription(),
                element -> element.count().toString()
            ))
            .rowsCount(topCount)
            .build();
        return Collections.singletonList(table);
    }

    @Override
    protected Integer extractElement(NginxLog log) {
        return log.response().code();
    }
}
