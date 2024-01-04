package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class LiteralsDateInterpreter extends DateInterpreter {
    @Override
    public Optional<LocalDate> parseDate(String date) {
        LocalDate currentDate = LocalDate.now();
        return switch (date) {
            case "today" -> Optional.of(currentDate);
            case "tomorrow" -> Optional.of(currentDate.plusDays(1));
            case "yesterday" -> Optional.of(currentDate.minusDays(1));
            case null, default -> parseNext(date);
        };
    }
}
