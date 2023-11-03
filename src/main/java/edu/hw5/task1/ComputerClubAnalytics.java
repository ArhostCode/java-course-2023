package edu.hw5.task1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComputerClubAnalytics {

    private static final Pattern VISIT_DATES_PATTERN =
        Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

    private ComputerClubAnalytics() {
    }

    public static Duration getAverageDuration(List<String> visitDates) {
        Duration totalDuration = Duration.ZERO;
        List<Duration> durations = visitDates.stream().map(ComputerClubAnalytics::computeDurationPerOneVisit).toList();
        for (Duration duration : durations) {
            totalDuration = totalDuration.plus(duration);
        }
        return totalDuration.dividedBy(durations.size()).truncatedTo(ChronoUnit.MINUTES);
    }

    private static Duration computeDurationPerOneVisit(String visitTime) {
        Matcher visitDatesMatcher = VISIT_DATES_PATTERN.matcher(visitTime);
        if (!visitDatesMatcher.matches()) {
            throw new IllegalArgumentException("Invalid input string " + visitTime);
        }
        LocalDateTime fromDateTime = LocalDateTime.parse(visitDatesMatcher.group(1), FORMATTER);
        LocalDateTime toDateTime = LocalDateTime.parse(visitDatesMatcher.group(2), FORMATTER);
        Duration duration = Duration.between(fromDateTime, toDateTime);
        if (duration.isNegative()) {
            throw new IllegalArgumentException("Invalid date`s order in string " + visitTime);
        }
        return duration;
    }

}
