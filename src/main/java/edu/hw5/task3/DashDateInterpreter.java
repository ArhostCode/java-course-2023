package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashDateInterpreter extends DateInterpreter {

    private static final Pattern PATTERN = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{1,2})");

    @SuppressWarnings("checkstyle:MagicNumber") // Extract 3 as final variable non reasonable
    @Override
    public Optional<LocalDate> parseDate(String date) {
        Matcher dateMatcher = PATTERN.matcher(date);
        if (!dateMatcher.matches()) {
            return parseNext(date);
        }
        return Optional.of(LocalDate.of(
            Integer.parseInt(dateMatcher.group(1)),
            Integer.parseInt(dateMatcher.group(2)),
            Integer.parseInt(dateMatcher.group(3))
        ));
    }
}
