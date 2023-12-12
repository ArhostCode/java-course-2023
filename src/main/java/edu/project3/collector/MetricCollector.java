package edu.project3.collector;

import edu.project3.model.log.LogsContainer;
import edu.project3.model.metric.Metric;

public interface MetricCollector {
    Metric collect(LogsContainer container);
}
