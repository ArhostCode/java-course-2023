package edu.hw5.task1;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ComputerClubAnalyticsTest {

    private static Stream<Arguments> correctInput() {
        return Stream.of(
            Arguments.of(
                List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"),
                Duration.ofHours(3).plusMinutes(40)
            ),
            Arguments.of(
                List.of("2022-03-12, 20:00 - 2022-03-12, 21:00", "2022-04-01, 21:30 - 2022-04-01, 22:00"),
                Duration.ofMinutes(45)
            ),
            Arguments.of(
                List.of("2022-03-12, 22:00 - 2022-03-12, 23:30", "2022-04-01, 13:30 - 2022-04-01, 14:09"),
                Duration.ofHours(1).plusMinutes(4)
            )
        );
    }

    @ParameterizedTest
    @MethodSource("correctInput")
    @DisplayName("Тест ComputerClubAnalytics#getAverageDuration на верных входных данных")
    public void getAverageDuration_shouldReturnCorrectAnswer(List<String> visitDates, Duration expectedDuration) {
        Assertions.assertThat(ComputerClubAnalytics.getAverageDuration(visitDates)).isEqualTo(expectedDuration);
    }

    @Test
    @DisplayName("Тест ComputerClubAnalytics#getAverageDuration на неверных входных данных")
    public void getAverageDuration_shouldThrowException_whenInputIsInvalid() {
        Assertions.assertThatThrownBy(() -> ComputerClubAnalytics.getAverageDuration(List.of("01-20 33:44")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тест ComputerClubAnalytics#getAverageDuration на неверных входных данных")
    public void getAverageDuration_shouldThrowException_whenInputDatesInvalidOrder() {
        Assertions.assertThatThrownBy(() -> ComputerClubAnalytics.getAverageDuration(List.of("2022-04-01, 21:30 - 2022-03-01, 22:00")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
