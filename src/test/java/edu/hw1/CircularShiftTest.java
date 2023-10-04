package edu.hw1;

import edu.hw1.task6.KaprekarConst;
import edu.hw1.task7.CircularShift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;

public class CircularShiftTest {
    @DisplayName("Тест CircularShift.rotateLeft(int,int) с верными входными данными")
    @ParameterizedTest(name = "{index} - {0} <<<< {1} = {2} - верный ответ")
    @CsvSource(
        {
            "16, 1, 1",
            "17, 2, 6",
            "5454, 1000, 2727",
        })
    public void rotateLeft_shouldReturnValue_whenInputCorrect(int num, int shift, int expected) {
        int actual = CircularShift.rotateLeft(num, shift);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Тест CircularShift.rotateLeft(int,int) с верными входными данными")
    @ParameterizedTest(name = "{index} - {0} >>>> {1} = {2} - верный ответ")
    @CsvSource(
        {
            "8, 1, 4",
            "0, 100, 0"
        })
    public void rotateRight_shouldReturnValue_whenInputCorrect(int num, int shift, int expected) {
        int actual = CircularShift.rotateRight(num, shift);
        assertThat(actual).isEqualTo(expected);
    }
}
