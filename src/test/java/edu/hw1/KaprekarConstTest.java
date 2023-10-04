package edu.hw1;

import edu.hw1.task6.KaprekarConst;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

public class KaprekarConstTest {
    @DisplayName("Тест KaprekarConst.stepsToKaprekarConst(int) с верными входными данными")
    @ParameterizedTest(name = "{index} - {0} - {1} - верный ответ")
    @CsvSource(
        {"6174, 0",
            "3524, 3",
            "6621, 5",
            "6554, 4",
            "1234, 3"})
    public void stepsToKaprekarConst_shouldReturnValue_whenInputCorrect(int num, int expected) {
        int actual = KaprekarConst.stepsToKaprekarConst(num);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Тест KaprekarConst.stepsToKaprekarConst(int) с неверными входными данными")
    @ParameterizedTest(name = "{index} - {0} - неверные входные данные")
    @ValueSource(ints = {1111, 5555, 100, -1000, 10000})
    public void stepsToKaprekarConst_shouldReturnMinusOne_whenInputInCorrect(int num) {
        int actual = KaprekarConst.stepsToKaprekarConst(num);
        assertThat(actual).isEqualTo(-1);
    }
}
