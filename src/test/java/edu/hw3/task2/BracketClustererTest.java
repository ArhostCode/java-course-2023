package edu.hw3.task2;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BracketClustererTest {

    private static Stream<Arguments> bracketSource() {
        return Stream.of(
            Arguments.of("()()()", new String[] {"()", "()", "()"}),
            Arguments.of("((()))", new String[] {"((()))"}),
            Arguments.of("((()))(())()()(()())", new String[] {"((()))", "(())", "()", "()", "(()())"}),
            Arguments.of("((())())(()(()()))", new String[] {"((())())", "(()(()()))"}),
            Arguments.of("(((())())(()(()()))", new String[] {}),
            Arguments.of("())", new String[] {}),
            Arguments.of("()(()", new String[] {})
        );
    }

    @DisplayName("Тестирование BracketClusterer#clusterize на верных значениях")
    @ParameterizedTest(name = "{0} верно кластеризуется") @MethodSource("bracketSource")
    public void clusterize_shouldReturnCorrectClusters(String original, String[] result) {
        Assertions.assertThat(BracketClusterer.clusterize(original).toArray()).isEqualTo(result);
    }

    @Test
    @DisplayName("Тестирование BracketClusterer#clusterize на null")
    public void clusterize_shouldThrowNullPointerException_whenInputNull() {
        Assertions.assertThatThrownBy(() -> BracketClusterer.clusterize(null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Тестирование BracketClusterer#clusterize на неверной строке")
    public void clusterize_shouldThrowIllegalArgumentException_whenInputIncorrect() {
        Assertions.assertThatThrownBy(() -> BracketClusterer.clusterize("(original)"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
