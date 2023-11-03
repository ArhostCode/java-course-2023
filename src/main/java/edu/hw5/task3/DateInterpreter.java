package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateInterpreter {

    private DateInterpreter next;

    public static DateInterpreter link(DateInterpreter first, DateInterpreter... dateParsers) {
        DateInterpreter head = first;
        for (DateInterpreter nextInChain : dateParsers) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Optional<LocalDate> parseDate(String date);

    protected Optional<LocalDate> parseNext(String date) {
        if (next == null) {
            return Optional.empty();
        }
        return next.parseDate(date);
    }
}
