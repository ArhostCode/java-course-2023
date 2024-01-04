package edu.project3.renderer;

import edu.project3.model.adoc.AdocRenderer;
import edu.project3.model.metric.components.MetricHeader;
import edu.project3.model.metric.components.MetricTable;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdocRendererTest {

    @Test
    @DisplayName("Тестирование AdocRenderer#renderHeader")
    public void renderHeader_shouldReturnCorrectString() {
        RenderedComponent component = new AdocRenderer().renderHeader(new MetricHeader("Тестовое содержание", 2));
        Assertions.assertThat(component.convertToString()).isEqualTo("== Тестовое содержание");
    }

    @Test
    @DisplayName("Тестирование AdocRenderer#renderTable")
    public void renderTable_shouldReturnCorrectString() {
        RenderedComponent component = new AdocRenderer().renderTable(new MetricTable()
            .addHeaders("Тестовое содержание", "Тестовое содержание")
            .addRowElements("Строка", "Строка2")
        );
        Assertions.assertThat(component.convertToString()).isEqualTo("""
            |==========================================
            | Тестовое содержание | Тестовое содержание\s

            |              Строка |             Строка2\s
            |==========================================""");
    }

}
