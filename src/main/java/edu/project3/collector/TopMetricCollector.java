package edu.project3.collector;

import edu.project3.model.log.LogsContainer;
import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricComponent;
import edu.project3.utils.MetricComponentsUtils;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class TopMetricCollector<T> implements MetricCollector {

    private final String metricName;

    @Override
    public Metric collect(LogsContainer container) {
        Metric metric = MetricComponentsUtils.createMetricWithHeader(metricName);
        var topResources = getTopResources(container.logs());
        metric.components().addAll(createComponents(topResources));
        return metric;
    }

    protected abstract List<MetricComponent> createComponents(List<TopElement<T>> topElements);

    protected abstract T extractElement(NginxLog log);

    private List<TopElement<T>> getTopResources(List<NginxLog> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(this::extractElement, Collectors.counting()))
            .entrySet()
            .stream()
            .map(entry -> new TopElement<>(entry.getKey(), entry.getValue()))
            .sorted(Comparator.comparingLong(TopElement<T>::count).reversed())
            .toList();
    }

    protected record TopElement<E>(E element, Long count) {
    }
}
