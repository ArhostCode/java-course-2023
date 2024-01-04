package edu.project3.renderer;

import edu.project3.model.adoc.AdocRenderer;
import edu.project3.model.markdown.MarkdownRenderer;

public final class RenderFactory {

    private RenderFactory() {
    }

    public static Renderer createRenderer(String format) {
        return switch (format) {
            case "adoc" -> new AdocRenderer();
            case "markdown" -> new MarkdownRenderer();
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
