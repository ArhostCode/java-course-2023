package edu.project3.utils;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricHeader;

public final class MetricComponentsUtils {
    private MetricComponentsUtils() {
    }

    public static Metric createMetricWithHeader(String name) {
        final int headerLevel = 4;
        Metric metric = new Metric(name);
        metric.components().add(new MetricHeader(metric.name(), headerLevel));
        return metric;
    }
}
