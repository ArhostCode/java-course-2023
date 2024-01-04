package edu.hw5.task6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubstringCheckerTest {

    @ParameterizedTest
    @DisplayName("Тестирование SubstringChecker#isSubstring")
    @CsvSource({
        "achfdbaabgabcaabg,abc,true",
        "figvam,gva,true",
        "figvam,ga,false",
        "figvam,fig,true",
        "figvam,kur,false"
    })
    public void isSubstring_shouldReturnCorrectAnswer(String original, String substring, boolean isSubstring) {
        Assertions.assertThat(SubstringChecker.isSubstring(original, substring)).isEqualTo(isSubstring);
    }

}
