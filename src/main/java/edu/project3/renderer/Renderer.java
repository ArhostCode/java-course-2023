package edu.project3.renderer;

import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;

public interface Renderer {
    RenderedComponent renderHeader(MetricHeader header);

    RenderedComponent renderTable(MetricTable table);
}
