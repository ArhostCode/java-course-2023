package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Friday13Finder {
    private static final int SEARCHING_DAY_OF_MONTH = 13;

    private Friday13Finder() {
    }

    public static List<LocalDate> searchAllAtYear(int year) {
        List<LocalDate> fridaysThe13th = new ArrayList<>();
        LocalDate date = LocalDate.of(year, Month.JANUARY, SEARCHING_DAY_OF_MONTH);
        while (date.getYear() == year) {
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridaysThe13th.add(date);
            }
            date = date.plusMonths(1);
        }
        return fridaysThe13th;
    }

    public static LocalDate getNextFridayThe13th(LocalDate date) {
        LocalDate currentDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (currentDate.getDayOfMonth() != SEARCHING_DAY_OF_MONTH) {
            currentDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return currentDate;
    }
}
