package edu.project3;

import edu.project3.argument.Argument;
import edu.project3.argument.Arguments;
import edu.project3.collector.IPsMetricCollector;
import edu.project3.collector.MetadataMetricCollector;
import edu.project3.collector.ResponseCodesMetricCollector;
import edu.project3.collector.ResponseSizesMetricCollector;
import edu.project3.collector.TimeOfDayMetricCollector;
import edu.project3.collector.TopResourcesMetricCollector;
import edu.project3.log.BaseNginxLogParser;
import edu.project3.model.BasicConfiguration;
import edu.project3.source.LocalFilesLogSource;
import edu.project3.source.LogSource;
import edu.project3.source.UrlLogSource;
import edu.project3.utils.BasicConfigurationParser;
import edu.project3.writer.ConsoleMetricsWriter;
import edu.project3.writer.MetricsWriter;
import java.util.List;
import java.util.Map;

public final class NginxLogAnalyticsApp {
    private final List<String> commandLineArguments;
    private final MetricsWriter writer;

    public NginxLogAnalyticsApp(String[] args, MetricsWriter writer) {
        commandLineArguments = List.of(args);
        this.writer = writer;
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        new NginxLogAnalyticsApp(args, new ConsoleMetricsWriter()).run();
    }

    public void run() {
        Map<String, String> argumentsMap = parseArguments(commandLineArguments);
        BasicConfiguration basicConfiguration = BasicConfigurationParser.parse(argumentsMap);
        NginxLogManager manager = createLogManager(basicConfiguration);
        writer.print(manager.collect(), basicConfiguration.renderer());
    }

    private Map<String, String> parseArguments(List<String> args) {
        Arguments arguments = new Arguments()
            .addArgument(new Argument("path", true))
            .addArgument(new Argument("format", false))
            .addArgument(new Argument("from", false))
            .addArgument(new Argument("to", false));
        return arguments.parse(args);
    }

    private NginxLogManager createLogManager(BasicConfiguration configuration) {
        var parser = new BaseNginxLogParser();
        LogSource source = createLogSource(configuration);
        NginxLogManager manager = new NginxLogManager(source, parser, configuration);
        manager.registerMetricCollectors(
            new MetadataMetricCollector(),
            new TopResourcesMetricCollector(),
            new ResponseCodesMetricCollector(),
            new IPsMetricCollector(),
            new TimeOfDayMetricCollector(),
            new ResponseSizesMetricCollector()
        );

        return manager;
    }

    private LogSource createLogSource(BasicConfiguration configuration) {
        if (configuration.isRemote()) {
            return new UrlLogSource(configuration.paths().getFirst());
        }
        return new LocalFilesLogSource(configuration.paths());
    }

}
