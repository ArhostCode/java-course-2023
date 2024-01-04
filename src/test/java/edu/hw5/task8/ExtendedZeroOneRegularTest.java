package edu.hw5.task8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtendedZeroOneRegularTest {
    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isOddLength")
    @CsvSource({
        "00010,true",
        "as,false",
        "sd0,false",
        "11111,true",
        "110110,false",
        "1,true",
        "010,true",
        "11,false"
    })
    public void isOddLength_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isOddLength(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isZeroOddOrOneEven")
    @CsvSource({
        "00010,true",
        "as,false",
        "sd0,false",
        "11111,false",
        "110110,true",
        "1,false",
        "010,true",
        "11,true"
    })
    public void isZeroOddOrOneEven_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isZeroOddOrOneEven(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isZeroCountMultiplyThree")
    @CsvSource({
        "00010,false",
        "as,false",
        "sd0,false",
        "11111,true",
        "110010,true",
        "1,true",
        "0100000,true",
        "11,true"
    })
    public void isZeroCountMultiplyThree_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isZeroCountMultiplyThree(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isNotThreeOrTwoOnes")
    @CsvSource({
        "00010,true",
        "as,false",
        "sd0,false",
        "11111,true",
        "110010,true",
        "1,true",
        "0100000,true",
        "11,false",
        "111,false",
        "110,true",
        "101,true"
    })
    public void isNotThreeOrTwoOnes_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isNotThreeOrTwoOnes(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isEveryOddSymbolIsOne")
    @CsvSource({
        "10111,true",
        "as,false",
        "sd0,false",
        "11111,true",
        "111010,true",
        "1,true",
        "0100000,false",
        "11,true",
        "10,true",
        "111,true",
        "110,false",
        "101,true"
    })
    public void isEveryOddSymbolIsOne_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isEveryOddSymbolIsOne(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isMoreTwoZerosAndLessOneOnes")
    @CsvSource({
        "10111,false",
        "as,false",
        "sd0,false",
        "11111,false",
        "111010,false",
        "1,false",
        "0100000,true",
        "11,false",
        "100,true",
        "001,true",
        "110,false"
    })
    public void isMoreTwoZerosAndLessOneOnes_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isMoreTwoZerosAndLessOneOnes(string)).isEqualTo(isCorrect);
    }

    @ParameterizedTest
    @DisplayName("Тестирование ExtendedZeroOneRegular#isNotContainSerialOnes")
    @CsvSource({
        "10111,false",
        "as,false",
        "sd0,false",
        "11111,false",
        "111010,false",
        "1,true",
        "0100000,true",
        "101,true",
        "100,true",
        "001,true",
        "110,false"
    })
    public void isNotContainSerialOnes_shouldReturnCorrectAnswer(String string, boolean isCorrect) {
        Assertions.assertThat(ExtendedZeroOneRegular.isNotContainSerialOnes(string)).isEqualTo(isCorrect);
    }

}
