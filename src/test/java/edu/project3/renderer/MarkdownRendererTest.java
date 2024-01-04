package edu.project3.renderer;

import edu.project3.model.markdown.MarkdownRenderer;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MarkdownRendererTest {

    @Test
    @DisplayName("Тестирование MarkdownRenderer#renderHeader")
    public void renderHeader_shouldReturnCorrectString() {
        RenderedComponent component = new MarkdownRenderer().renderHeader(new MetricHeader("Тестовое содержание", 2));
        Assertions.assertThat(component.convertToString()).isEqualTo("## Тестовое содержание");
    }

    @Test
    @DisplayName("Тестирование MarkdownRenderer#renderTable")
    public void renderTable_shouldReturnCorrectString() {
        RenderedComponent component = new MarkdownRenderer().renderTable(new MetricTable()
            .addHeaders("Тестовое содержание", "Тестовое содержание")
            .addRowElements("Строка", "Строка2")
        );
        Assertions.assertThat(component.convertToString()).isEqualTo("""
            | Тестовое содержание | Тестовое содержание |
            |:-------------------:|:-------------------:|
            |              Строка |             Строка2 |""");
    }

}
