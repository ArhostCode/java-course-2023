package edu.project3.model.metric.components;

import edu.project3.renderer.RenderedComponent;
import edu.project3.renderer.Renderer;

public record MetricHeader(String text, int level) implements MetricComponent {
    @Override
    public RenderedComponent render(Renderer renderer) {
        return renderer.renderHeader(this);
    }
}
