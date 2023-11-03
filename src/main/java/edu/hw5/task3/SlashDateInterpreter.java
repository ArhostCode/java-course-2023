package edu.hw5.task3;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlashDateInterpreter extends DateInterpreter {
    private static final Pattern PATTERN = Pattern.compile("(\\d{1,2})/(\\d{1,2})/(\\d{2}|\\d{4})");

    @Override
    @SuppressWarnings("checkstyle:MagicNumber") // Extract 3 as final variable non reasonable
    public Optional<LocalDate> parseDate(String date) {
        Matcher dateMatcher = PATTERN.matcher(date);
        if (!dateMatcher.matches()) {
            return parseNext(date);
        }
        int day = Integer.parseInt(dateMatcher.group(1));
        int month = Integer.parseInt(dateMatcher.group(2));
        int year = Integer.parseInt(dateMatcher.group(3));

        final int centuryYearsCount = 100;
        final int currentCenturyYear = LocalDate.now().getYear() / centuryYearsCount * centuryYearsCount;
        if (year < centuryYearsCount) {
            year += currentCenturyYear;
        }
        return Optional.of(LocalDate.of(year, month, day));
    }
}
