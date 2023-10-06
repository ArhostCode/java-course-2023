package edu.hw1;

import edu.hw1.task5.PalindromeChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeCheckerTest {

    @DisplayName("Тест PalindromeChecker.isPalindrome(int) с палиндромом 1 степени")
    @ParameterizedTest(name = "{index} - {0} - палиндром 1 степени")
    @ValueSource(ints = {363, 11})
    public void isPalindrome_shouldReturnTrue_whenInputIsPalindrome1Degree(int num) {
        boolean actual = PalindromeChecker.isPalindrome(num);
        assertThat(actual).isTrue();
    }

    @DisplayName("Тест PalindromeChecker.isPalindrome(int) с палиндромом 2 степени")
    @ParameterizedTest(name = "{index} - {0} - палиндром 2 степени")
    @ValueSource(ints = {23336014, 123312})
    public void isPalindrome_shouldReturnTrue_whenInputIsPalindrome2Degree(int num) {
        boolean actual = PalindromeChecker.isPalindrome(num);
        assertThat(actual).isTrue();
    }

    @DisplayName("Тест PalindromeChecker.isPalindrome(int) с палиндромом 3+ степени")
    @ParameterizedTest(name = "{index} - {0} - палиндром 2 степени")
    @ValueSource(ints = {11211230, 13001120})
    public void isPalindrome_shouldReturnTrue_whenInputIsPalindrome(int num) {
        boolean actual = PalindromeChecker.isPalindrome(num);
        assertThat(actual).isTrue();
    }

    @DisplayName("Тест PalindromeChecker.isPalindrome(int) с не палиндромом")
    @ParameterizedTest(name = "{index} - {0} - не палиндром")
    @ValueSource(ints = {124, 432,2939})
    public void isPalindrome_shouldReturnFalse_whenInputIsNotPalindrome(int num) {
        boolean actual = PalindromeChecker.isPalindrome(num);
        assertThat(actual).isFalse();
    }

}
