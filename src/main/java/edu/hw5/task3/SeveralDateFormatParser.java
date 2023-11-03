package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public final class SeveralDateFormatParser {

    private SeveralDateFormatParser() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        DateInterpreter interpreter = DateInterpreter.link(
            new DashDateInterpreter(),
            new SlashDateInterpreter(),
            new LiteralsDateInterpreter(),
            new DaysAgoDateInterpreter()
        );
        return interpreter.parseDate(date);
    }

}
