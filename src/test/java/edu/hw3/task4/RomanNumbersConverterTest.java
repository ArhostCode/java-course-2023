package edu.hw3.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @Test
    @DisplayName("Тест RomanNumbersConverter#convertToRomanNumber на неверных данных <= 0")
    public void convertToRomanNumber_shouldThrowIllegalArgumentException_whenInputIncorrect() {
        Assertions.assertThatThrownBy(() -> RomanNumbersConverter.convertToRomanNumber(-5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тест RomanNumbersConverter#convertToRomanNumber на неверных данных > 3999")
    public void convertToRomanNumber_shouldThrowIllegalArgumentException_whenInputIncorrect_1() {
        Assertions.assertThatThrownBy(() -> RomanNumbersConverter.convertToRomanNumber(4000))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
