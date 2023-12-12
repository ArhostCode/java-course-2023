package edu.project3.model.markdown;

import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.renderer.RenderedComponent;
import edu.project3.renderer.Renderer;

public class MarkdownRenderer implements Renderer {
    @Override
    public RenderedComponent renderHeader(MetricHeader header) {
        return new HeaderMarkdown(header.text(), header.level());
    }

    @Override
    public RenderedComponent renderTable(MetricTable table) {
        return new TableMarkdown(table.headers(), table.rows());
    }
}
