package edu.project3.model.metric;

import edu.project3.model.metric.components.MetricComponent;
import java.util.ArrayList;
import java.util.List;

public record Metric(String name, List<MetricComponent> components) {
    public Metric(String name) {
        this(name, new ArrayList<>());
    }
}
