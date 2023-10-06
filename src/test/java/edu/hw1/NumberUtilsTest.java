package edu.hw1;

import edu.hw1.task2.NumberUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;

public class NumberUtilsTest {
    @DisplayName("Тест NumberUtils.countDigits(int)")
    @ParameterizedTest(name = "{index} - len {0} = {1} - верный ответ")
    @CsvSource({
        "544, 3",
        "-4666, 4",
        "10, 2",
        "0, 1"
    }
    )
    public void countDigits_shouldReturnCorrectAnswer(int num, int expected) {
        int actual = NumberUtils.countDigits(num);
        assertThat(actual).isEqualTo(expected);
    }

}
