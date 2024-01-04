package edu.project3.collector;

import edu.project3.model.log.LogsContainer;
import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.Metric;
import edu.project3.model.metric.components.MetricTable;
import edu.project3.utils.MetricComponentsUtils;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TimeOfDayMetricCollector implements MetricCollector {
    @Override
    public Metric collect(LogsContainer container) {
        Metric metric = MetricComponentsUtils.createMetricWithHeader("Время суток");
        Map<TimeOfDay, Long> times =
            Arrays.stream(TimeOfDay.values()).collect(Collectors.toMap(Function.identity(), timeOfDay -> 0L));
        for (NginxLog log : container.logs()) {
            for (TimeOfDay timeOfDay : TimeOfDay.values()) {
                if (timeOfDay.isInRange(log.timeStamp())) {
                    times.compute(timeOfDay, (k, v) -> v + 1);
                }
            }
        }

        MetricTable table = new MetricTable.ExtendedTableBuilder<Map.Entry<TimeOfDay, Long>>()
            .rows(times.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList())
            .headers(metric.name(), "Количество запросов")
            .stringExtractors(List.of(
                entry -> entry.getKey().getName(),
                entry -> entry.getValue().toString()
            ))
            .rowsCount(times.size())
            .build();
        metric.components().add(table);
        return metric;
    }

    @RequiredArgsConstructor
    private enum TimeOfDay {
        MORNING(6, 11, "Утро"),
        DAY(12, 17, "День"),
        EVENING(18, 23, "Вечер"),
        NIGHT(0, 5, "Ночь");

        private final int hourStart;
        private final int hourEnd;
        @Getter
        private final String name;

        private boolean isInRange(OffsetDateTime time) {
            return time.getHour() >= hourStart && time.getHour() < hourEnd;
        }
    }
}
