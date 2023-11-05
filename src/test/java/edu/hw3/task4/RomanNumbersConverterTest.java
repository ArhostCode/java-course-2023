package edu.hw3.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RomanNumbersConverterTest {

    @DisplayName("Тест RomanNumbersConverter#convertToRomanNumber на верных данных")
    @ParameterizedTest(name = "{0} = {1} в римской системе счисления")
    @CsvSource(value = {
        "2,II",
        "12,XII",
        "2009,MMIX",
        "2562,MMDLXII",
        "946,CMXLVI",
        "444,CDXLIV",
        "3999,MMMCMXCIX"
    })
    public void convertToRomanNumber_shouldReturnCorrectAnswer(int num, String expected) {
        Assertions.assertThat(RomanNumbersConverter.convertToRomanNumber(num)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("Тест RomanNumbersConverter#convertToRomanNumber на неверных данных <= 0")
    @ValueSource(ints = {-5, 4000})
    public void convertToRomanNumber_shouldThrowIllegalArgumentException_whenInputIncorrect(int value) {
        Assertions.assertThatThrownBy(() -> RomanNumbersConverter.convertToRomanNumber(value))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
