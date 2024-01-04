package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysAgoDateInterpreter extends DateInterpreter {

    private static final Pattern PATTERN = Pattern.compile("(\\d+) days ago");

    @Override
    public Optional<LocalDate> parseDate(String date) {
        // Extracted in separated case to simplify regex because only (1) requires (day)
        if (date.equals("1 day ago")) {
            return getNowDateMinus(1);
        }
        Matcher dateMatcher = PATTERN.matcher(date);
        if (!dateMatcher.matches()) {
            return parseNext(date);
        }
        return getNowDateMinus(Integer.parseInt(dateMatcher.group(1)));
    }

    private Optional<LocalDate> getNowDateMinus(int days) {
        return Optional.of(LocalDate.now().minusDays(days));
    }
}
