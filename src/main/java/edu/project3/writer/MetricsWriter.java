package edu.project3.writer;

import edu.project3.model.metric.Metric;
import edu.project3.renderer.Renderer;
import java.util.List;

public interface MetricsWriter {

    void print(List<Metric> metrics, Renderer renderer);

}
