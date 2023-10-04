package edu.hw1.task5;

import org.apache.logging.log4j.LogManager;

public final class PalindromeChecker {

    private PalindromeChecker() {
    }

    public static boolean isPalindrome(int num) {
        final int numberSystemBase = 10;
        if (num < numberSystemBase) {
            return false;
        }

        String stringNum = String.valueOf(num);
        String reversedNum = new StringBuilder(stringNum).reverse().toString();
        if (stringNum.equals(reversedNum)) {
            return true;
        }

        int reversed = Integer.parseInt(reversedNum);
        int sumNumber = 0;
        int degree = 1;
        while (reversed != 0) {
            sumNumber += degree * (reversed % numberSystemBase + (reversed / numberSystemBase) % numberSystemBase);
            reversed /= numberSystemBase * numberSystemBase;
            degree *= numberSystemBase;
        }
        LogManager.getLogger().trace("Проверка на палиндром следующей степени - %d".formatted(sumNumber));
        return isPalindrome(sumNumber);
    }

}
