package edu.hw3.task2;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BracketClustererTest {

    private static Stream<Arguments> bracketSource() {
        return Stream.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())"),
                Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
            ),
            Arguments.of("(((())())(()(()()))", List.of()),
            Arguments.of("())", List.of()),
            Arguments.of("()(()", List.of())
        );
    }

    @DisplayName("Тестирование BracketClusterer#clusterize на верных значениях")
    @ParameterizedTest(name = "{0} верно кластеризуется") @MethodSource("bracketSource")
    public void clusterize_shouldReturnCorrectClusters(String original, List<String> result) {
        Assertions.assertThat(BracketClusterer.clusterize(original)).isEqualTo(result);
    }

    @ParameterizedTest
    @DisplayName("Тестирование BracketClusterer#clusterize на неверных данных")
    @NullSource
    @ValueSource(strings = {"(original)"})
    public void clusterize_shouldThrowNullPointerException_whenInputIncorrect(String input) {
        Assertions.assertThatThrownBy(() -> BracketClusterer.clusterize(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
