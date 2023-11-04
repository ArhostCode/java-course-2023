package edu.project3;

import edu.project3.collector.MetricCollector;
import edu.project3.log.NginxLogParser;
import edu.project3.model.BasicConfiguration;
import edu.project3.model.filter.DateFilter;
import edu.project3.model.filter.LogFilter;
import edu.project3.model.log.LogsContainer;
import edu.project3.model.log.LogsMetadata;
import edu.project3.model.log.NginxLog;
import edu.project3.model.metric.Metric;
import edu.project3.source.LogSource;
import java.util.ArrayList;
import java.util.List;

public class NginxLogManager {
    private final LogSource logSource;
    private final NginxLogParser parser;
    private final BasicConfiguration configuration;
    private final List<MetricCollector> collectors = new ArrayList<>();
    private final List<LogFilter> filters = new ArrayList<>();

    public NginxLogManager(LogSource logSource, NginxLogParser parser, BasicConfiguration configuration) {
        this.logSource = logSource;
        this.parser = parser;
        this.configuration = configuration;
        registerLogFilter(new DateFilter(configuration.from(), configuration.to()));
    }

    public void registerLogFilter(LogFilter filter) {
        filters.add(filter);
    }

    public void registerMetricCollectors(MetricCollector... collector) {
        collectors.addAll(List.of(collector));
    }

    public List<Metric> collect() {
        List<NginxLog> logs = applyFilters(obtainLogs());
        final LogsContainer container = createContainer(logs);
        return collectors.stream().map(collector -> collector.collect(container)).toList();
    }

    private List<NginxLog> obtainLogs() {
        return parser.parse(logSource.getLogs());
    }

    private List<NginxLog> applyFilters(List<NginxLog> logs) {
        List<NginxLog> filteredLogs = logs;
        for (LogFilter filter : filters) {
            filteredLogs = filteredLogs.stream().filter(filter::isSuitable).toList();
        }
        return filteredLogs;
    }

    private LogsContainer createContainer(List<NginxLog> logs) {
        return new LogsContainer(
            logs,
            new LogsMetadata(configuration.paths(), configuration.from(), configuration.to())
        );
    }

}
