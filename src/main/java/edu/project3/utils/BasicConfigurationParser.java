package edu.project3.utils;

import edu.project3.model.BasicConfiguration;
import edu.project3.model.markdown.MarkdownRenderer;
import edu.project3.renderer.RenderFactory;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

public final class BasicConfigurationParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    private BasicConfigurationParser() {
    }

    public static BasicConfiguration parse(Map<String, String> argumentsMap) {
        String path = argumentsMap.get("path");
        String format = argumentsMap.get("format");
        boolean isRemote = path.startsWith("http");

        BasicConfiguration.BasicConfigurationBuilder builder = isRemote ? loadRemoteConfigurationBuilder(path)
            : loadLocalConfigurationBuilder(path);
        builder.to(parseDateTime(argumentsMap.get("to")));
        builder.from(parseDateTime(argumentsMap.get("from")));
        builder.renderer(format == null ? new MarkdownRenderer() : RenderFactory.createRenderer(format));
        return builder.build();
    }

    private static BasicConfiguration.BasicConfigurationBuilder loadRemoteConfigurationBuilder(String path) {
        return BasicConfiguration.builder()
            .isRemote(true)
            .paths(List.of(path));
    }

    private static BasicConfiguration.BasicConfigurationBuilder loadLocalConfigurationBuilder(String path) {
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + path);
        try (Stream<Path> pathStream = Files.find(
            Path.of(""),
            Integer.MAX_VALUE,
            (currentPath, fileAttributes) -> !fileAttributes.isDirectory() && pathMatcher.matches(currentPath)
        )) {
            return BasicConfiguration.builder()
                .isRemote(false)
                .paths(pathStream.map(Path::toString).toList());
        } catch (IOException exception) {
            throw new IllegalStateException("Cannot obtain files");
        }
    }

    private static OffsetDateTime parseDateTime(String date) {
        return date == null ? null
            : OffsetDateTime.of(LocalDate.parse(date, DATE_TIME_FORMATTER), LocalTime.MIDNIGHT, ZoneOffset.UTC);
    }
}
