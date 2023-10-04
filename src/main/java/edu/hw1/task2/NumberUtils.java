package edu.hw1.task2;

public final class NumberUtils {

    private NumberUtils() {
    }

    public static int countDigits(int number) {
        int num = number;
        final int numberSystemBase = 10;
        int digitsCount = 1;

        while (num >= numberSystemBase || num <= -numberSystemBase) {
            digitsCount++;
            num /= numberSystemBase;
        }
        return digitsCount;
    }
}
