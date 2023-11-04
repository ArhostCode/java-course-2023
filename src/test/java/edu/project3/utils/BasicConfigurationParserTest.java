package edu.project3.utils;

import edu.project3.model.BasicConfiguration;
import edu.project3.model.adoc.AdocRenderer;
import edu.project3.model.markdown.MarkdownRenderer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasicConfigurationParserTest {

    @Test
    @DisplayName("Тестирование BasicConfigurationParser#parse на удалённых данных")
    public void parse_shouldReturnCorrectBasicConfiguration() {
        Map<String, String> argumentsMap = Map.of("path", "http://localhost:8080", "format", "markdown");
        BasicConfiguration configuration = BasicConfigurationParser.parse(argumentsMap);
        Assertions.assertThat(configuration)
            .satisfies(c -> Assertions.assertThat(c.renderer()).isInstanceOf(MarkdownRenderer.class))
            .extracting("paths", "isRemote")
            .containsExactly(List.of("http://localhost:8080"), true);
    }

    @Test
    @DisplayName("Тестирование BasicConfigurationParser#parse на локальных данных")
    public void parse_shouldReturnCorrectBasicConfiguration_whenIsLocale() {
        Map<String, String> argumentsMap = Map.of("path", "logs/**", "format", "adoc", "from", "2023-01-10");
        BasicConfiguration configuration = BasicConfigurationParser.parse(argumentsMap);
        Assertions.assertThat(configuration)
            .satisfies(c -> {
                    Assertions.assertThat(c.renderer()).isInstanceOf(AdocRenderer.class);
                    Assertions.assertThat(c.from()).isEqualTo(OffsetDateTime.of(2023, 1, 10, 0, 0, 0, 0, ZoneOffset.UTC));
                }
            )
            .extracting("paths", "isRemote")
            .containsExactly(List.of("logs\\dir\\log3.txt", "logs\\log1.txt", "logs\\log2.txt"), false);
    }

}
