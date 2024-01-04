package edu.project3.writer;

import edu.project3.model.metric.Metric;
import edu.project3.renderer.Renderer;
import java.util.List;

public class ConsoleMetricsWriter implements MetricsWriter {
    @SuppressWarnings("checkstyle:RegexpSinglelineJava") // Non reasonable render text on logs level
    @Override
    public void print(List<Metric> metrics, Renderer renderer) {
        metrics.forEach(metric -> metric.components().forEach(metricComponent ->
            System.out.println(metricComponent.render(renderer).convertToString() + "\n")));
    }
}
