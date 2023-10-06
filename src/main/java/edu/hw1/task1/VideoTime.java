package edu.hw1.task1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VideoTime {
    private static final int SECONDS_IN_MINUTE = 60;

    private VideoTime() {
    }

    public static int extractInSeconds(String time) {
        if (time == null) {
            return -1;
        }
        // Выделяет из строки 2 группы, 1 - 2+ символа на минуты, 2 символа на секунды
        Matcher timeMatcher = Pattern.compile("(\\d{2,}):([0-5]\\d)").matcher(time);
        if (!timeMatcher.matches()) {
            return -1;
        }
        int minutes = Integer.parseInt(timeMatcher.group(1));
        int seconds = Integer.parseInt(timeMatcher.group(2));
        return minutes * SECONDS_IN_MINUTE + seconds;
    }

}
