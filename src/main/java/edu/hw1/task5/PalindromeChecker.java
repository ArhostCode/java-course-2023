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

        int palindrome = num;
        int reverse = 0;

        while (palindrome != 0) {
            int remainder = palindrome % numberSystemBase;
            reverse = reverse * numberSystemBase + remainder;
            palindrome = palindrome / numberSystemBase;
        }

        if (reverse == num) {
            return true;
        }

        int sumNumber = 0;
        int degree = 1;
        while (reverse != 0) {
            int num1 = reverse % numberSystemBase;
            int num2 = (reverse / numberSystemBase) % numberSystemBase;

            if (num1 + num2 >= numberSystemBase && sumNumber != 0) {
                degree *= numberSystemBase;
            }
            sumNumber = sumNumber * degree + (num1 + num2);
            reverse /= numberSystemBase * numberSystemBase;
            degree = numberSystemBase;
        }

        LogManager.getLogger().trace("Проверка на палиндром следующей степени - %d".formatted(sumNumber));
        return isPalindrome(sumNumber);
    }
}
