package edu.hw5.task5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RussianCarNumbersValidatorTest {

    @ParameterizedTest
    @DisplayName("Тестирование RussianCarNumbersValidator#isValid")
    @CsvSource({
        "А123ВЕ777,true",
        "О777ОО77,true",
        "123АВЕ777,false",
        "А123ВГ77,false",
        "А123ВЕ7777,false"
    })
    public void isValid_shouldReturnCorrectAnswer(String number, boolean isValid) {
        Assertions.assertThat(RussianCarNumbersValidator.isValid(number)).isEqualTo(isValid);
    }
}
