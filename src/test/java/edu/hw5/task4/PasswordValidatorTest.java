package edu.hw5.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    @DisplayName("Тестирование PasswordValidator#isValid на верном пароле")
    public void isValid_shouldReturnTrue_whenPasswordContainsSpecialCharacters() {
        Assertions.assertThat(PasswordValidator.isValid("abc#")).isTrue();
    }

    @Test
    @DisplayName("Тестирование PasswordValidator#isValid на неверном пароле")
    public void isValid_shouldReturnFalse_whenPasswordNotContainsSpecialCharacters() {
        Assertions.assertThat(PasswordValidator.isValid("abc123sdfsdJDSHD")).isFalse();
    }

}
