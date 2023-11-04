package edu.project3.renderer;

import edu.project3.model.adoc.AdocRenderer;
import edu.project3.model.markdown.MarkdownRenderer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RenderFactoryTest {

    @Test
    @DisplayName("Тестирование RenderFactory#createRenderer с неверным форматом")
    public void createRenderer_shouldThrowExceptionWhenFormatIsInvalid() {
        Assertions.assertThatThrownBy(() -> RenderFactory.createRenderer("abo"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тестирование RenderFactory#createRenderer с adoc")
    public void createRenderer_shouldReturnRenderer() {
        Assertions.assertThat(RenderFactory.createRenderer("adoc")).isInstanceOf(AdocRenderer.class);
    }

    @Test
    @DisplayName("Тестирование RenderFactory#createRenderer c markdown")
    public void createRenderer_shouldReturnRenderer_2() {
        Assertions.assertThat(RenderFactory.createRenderer("markdown")).isInstanceOf(MarkdownRenderer.class);
    }
}
