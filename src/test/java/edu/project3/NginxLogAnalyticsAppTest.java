package edu.project3;

import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricComponent;
import edu.project3.model.metric.components.MetricHeader;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NginxLogAnalyticsAppTest {

    private final List<MetricComponent> response = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        response.clear();
    }

    @Test
    @DisplayName("Тестирование NginxLogAnalyticsApp")
    public void run_shouldReturnCorrectElements() {
        new NginxLogAnalyticsApp(
            new String[] {"--path", "logs/*"},
            (metrics, renderer) -> {
                for (Metric metric : metrics) {
                    response.addAll(metric.components());
                }
            }
        ).run();
        Assertions.assertThat(response)
            .contains(new MetricHeader("Общая информация", 4))
            .contains(new MetricHeader("Запрашиваемые ресурсы", 4))
            .contains(new MetricHeader("Коды ответа", 4))
            .contains(new MetricHeader("IP адреса", 4))
            .contains(new MetricHeader("Время суток", 4))
            .contains(new MetricHeader("Размеры ответов", 4))
            .hasSize(12)
        ;
    }

}
