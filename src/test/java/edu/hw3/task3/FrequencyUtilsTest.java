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

    private record FrequencyEntry(Object object, Integer freq) {
    }

    private static Stream<Arguments> frequencyArguments() {
        return Stream.of(
            Arguments.of(
                Arrays.asList("a", "bb", "a", "bb"),
                Arrays.asList(
                    new FrequencyEntry("a", 2),
                    new FrequencyEntry("bb", 2)
                )
            ),
            Arguments.of(
                Arrays.asList("this", "and", "that", "and"),
                Arrays.asList(
                    new FrequencyEntry("that", 1),
                    new FrequencyEntry("and", 2),
                    new FrequencyEntry("this", 1)
                )
            ),
            Arguments.of(
                Arrays.asList("код", "код", "код", "bug"),
                Arrays.asList(
                    new FrequencyEntry("код", 3),
                    new FrequencyEntry("bug", 1)
                )
            ),
            Arguments.of(
                Arrays.asList(1, 1, 2, 2),
                Arrays.asList(
                    new FrequencyEntry(1, 2),
                    new FrequencyEntry(2, 2)
                )
            )
        );
    }

    @DisplayName("Тест FrequencyUtils#computeFreqDictionary на верных входных данных")
    @ParameterizedTest(name = "{0} - правильно считается частота")
    @MethodSource("frequencyArguments")
    public void computeFreqDictionary_shouldReturnCorrectFreqDictionary(
        List<Object> objects,
        List<FrequencyEntry> frequencies
    ) {
        Map<Object, Integer> actual = FrequencyUtils.computeFreqDictionary(objects);
        Assertions.assertThat(allMatch(actual, frequencies)).isTrue();
    }

    @Test
    @DisplayName("Тест FrequencyUtils#computeFreqDictionary на null")
    public void computeFreqDictionary_shouldThrowNullPointerException_whenInputIsNull() {
        Assertions.assertThatThrownBy(() -> FrequencyUtils.computeFreqDictionary(null))
            .isInstanceOf(NullPointerException.class);
    }

    private static boolean allMatch(Map<Object, Integer> objects, List<FrequencyEntry> frequencyEntries) {
        if (objects.size() != frequencyEntries.size()) {
            return false;
        }
        for (FrequencyEntry entry : frequencyEntries) {
            if (!entry.freq.equals(objects.get(entry.object))) {
                return false;
            }
        }
        return true;
    }

}
