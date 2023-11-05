package edu.hw3.task3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FrequencyUtilsTest {

    private static Stream<Arguments> frequencyArguments() {
        return Stream.of(
            Arguments.of(
                Arrays.asList("a", "bb", "a", "bb"),
                Map.of("a", 2, "bb", 2)
            ),
            Arguments.of(
                Arrays.asList("this", "and", "that", "and"),
                Map.of("this", 1, "and", 2, "that", 1)
            ),
            Arguments.of(
                Arrays.asList("код", "код", "код", "bug"),
                Map.of("код", 3, "bug", 1)
            ),
            Arguments.of(
                Arrays.asList(1, 1, 2, 2),
                Map.of(1, 2, 2, 2)
            )
        );
    }

    @DisplayName("Тест FrequencyUtils#computeFreqDictionary на верных входных данных")
    @ParameterizedTest(name = "{0} - правильно считается частота")
    @MethodSource("frequencyArguments")
    public void computeFreqDictionary_shouldReturnCorrectFreqDictionary(
        List<Object> objects,
        Map<Object, Integer> frequencies
    ) {
        Map<Object, Integer> actual = FrequencyUtils.computeFreqDictionary(objects);
        Assertions.assertThat(actual).isEqualTo(frequencies);
    }

    @Test
    @DisplayName("Тест FrequencyUtils#computeFreqDictionary на null")
    public void computeFreqDictionary_shouldThrowNullPointerException_whenInputIsNull() {
        Assertions.assertThatThrownBy(() -> FrequencyUtils.computeFreqDictionary(null))
            .isInstanceOf(NullPointerException.class);
    }

}
