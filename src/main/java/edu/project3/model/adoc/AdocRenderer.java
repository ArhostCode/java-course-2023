package edu.project3.model.adoc;

import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.renderer.RenderedComponent;
import edu.project3.renderer.Renderer;

public class AdocRenderer implements Renderer {
    @Override
    public RenderedComponent renderHeader(MetricHeader header) {
        return new HeaderAdoc(header.text(), header.level());
    }

    @Override
    public RenderedComponent renderTable(MetricTable table) {
        return new TableAdoc(table.headers(), table.rows());
    }
}
