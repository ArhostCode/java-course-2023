package edu.hw1;

import edu.hw1.task4.StringFixer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.*;

public class StringFixerTest {

    @ParameterizedTest(name = "{index} - {0} = {1} - верный ответ")
    @DisplayName("Тест StringFixer.fix(String)")
    @CsvSource({
        "123456, 214365",
        "hTsii  s aimex dpus rtni.g, This is a mixed up string.",
        "badce, abcde"
    })
    public void fix_shouldReturnCorrectString(String given, String expected) {
        String actual = StringFixer.fix(given);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{index} - {0} - даёт пустой ответ")
    @DisplayName("Тест StringFixer.fix(String)")
    @NullAndEmptySource
    public void fix_shouldReturnEmptyString_whenDataIncorrect(String given) {
        String actual = StringFixer.fix(given);
        assertThat(actual).isBlank();
    }


}
