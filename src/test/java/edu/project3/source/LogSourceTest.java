package edu.project3.source;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class LogSourceTest {

    // Test might fail when log source is changed
    @Test
    @DisplayName("Тестирование UrlLogSource#getLogs")
    public void getLogs_shouldReturnCorrectLogs() {
        Assertions.assertThat(new UrlLogSource(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs").getLogs())
            .hasSize(51462);
    }

    @Test
    @DisplayName("Тестирование LocalFilesLogSource#getLogs")
    public void getLogs_shouldReturnCorrectLogs_whenLoadLogsLocal() {
        Assertions.assertThat(new LocalFilesLogSource(List.of("logs/log1.txt", "logs/log2.txt")).getLogs())
            .hasSize(89);
    }

}
