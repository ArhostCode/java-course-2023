package edu.hw5.task7;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ZeroOneRegularTest {

    @ParameterizedTest
    @DisplayName("Тестирование ZeroOneRegular#containsAtLeastThreeSymbolsAndThirdIsZero")
    @CsvSource({
        "00010,true",
        "asdfadsf,false",
        "sd0fadfaf,false",
        "11111,false",
        "110111,true",
        "11,false"
    })
    public void containsAtLeastThreeSymbolsAndThirdIsZero_shouldReturnCorrectAnswer(
        String string,
        boolean isCorrect
    ) {
        Assertions.assertThat(ZeroOneRegular.containsAtLeastThreeSymbolsAndThirdIsZero(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ZeroOneRegular#isFirstSymbolEqualsLast")
    @CsvSource({
        "00010,true",
        "asdfadsf,false",
        "sd0fadfaf,false",
        "11111,true",
        "110110,false",
        "1,true",
        "0,true",
        "11,true"
    })
    public void isFirstSymbolEqualsLast_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ZeroOneRegular.isFirstSymbolEqualsLast(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ZeroOneRegular#isLengthMoreOneAndLessThree")
    @CsvSource({
        "00010,false",
        "as,false",
        "sd0fadfaf,false",
        "11111,false",
        "110110,false",
        "1,true",
        "010,true",
        "11,true"
    })
    public void isLengthMoreOneAndLessThree_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ZeroOneRegular.isLengthMoreOneAndLessThree(string)).isEqualTo(isCorrect);
    }

}
