package edu.hw5.task3;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SeveralDateFormatParserTest {

    private static Stream<Arguments> parseValues() {
        return Stream.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, Month.OCTOBER, 10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, Month.DECEMBER, 2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, Month.MARCH, 1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, Month.MARCH, 1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234))),
            Arguments.of("2234 dat ao", Optional.empty())
        );
    }

    @ParameterizedTest
    @DisplayName("Тестирование SeveralDateFormatParser#parseDate")
    @MethodSource("parseValues")
    public void parseDate_shouldReturnCorrectAnswer(String date, Optional<LocalDate> parsedDate) {
        Assertions.assertThat(SeveralDateFormatParser.parseDate(date)).isEqualTo(parsedDate);
    }

}
