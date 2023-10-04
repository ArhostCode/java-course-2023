package edu.hw1.task6;

public final class KaprekarConst {

    private KaprekarConst() {
    }

    private static final int KAPREKAR_CONST = 6174;
    private static final int LOW_NUMBER = 1000;
    private static final int HIGH_NUMBER = 9999;

    public static int stepsToKaprekarConst(int num) {
        if (num < LOW_NUMBER || num > HIGH_NUMBER) {
            return -1;
        }
        if (num == KAPREKAR_CONST) {
            return 0;
        }
        StringBuilder sortedNumBuilder = new StringBuilder();
        String numString = String.valueOf(num);

        numString.chars().sorted().forEach(c -> sortedNumBuilder.append((char) c));
        int sortedNum = Integer.parseInt(sortedNumBuilder.toString());
        int reverseSortedNum = Integer.parseInt(sortedNumBuilder.reverse().toString());
        int result = Math.abs(sortedNum - reverseSortedNum);

        if (result == 0) {
            return -1;
        }
        return 1 + stepsToKaprekarConst(result);
    }

}
