package edu.project3.argument;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArgumentsTest {

    @Test
    @DisplayName("Тестирование Arguments#parse")
    public void parse_shouldReturnCorrectArguments_whenAllArgumentsProvided() {
        Arguments arguments = createArguments();
        Assertions.assertThat(arguments.parse(List.of("--t1", "hello", "--t2", "world", "--t3", "check")))
            .containsEntry("t1", "hello")
            .containsEntry("t2", "world")
            .containsEntry("t3", "check");
    }

    @Test
    @DisplayName("Тестирование Arguments#parse")
    public void parse_shouldReturnCorrectArguments_whenRequiredArgumentsProvided() {
        Arguments arguments = createArguments();
        Assertions.assertThat(arguments.parse(List.of("--t1", "hello", "--t2", "world")))
            .containsEntry("t1", "hello")
            .containsEntry("t2", "world");
    }

    @Test
    @DisplayName("Тестирование Arguments#parse")
    public void parse_shouldThrowException_whenRequiredArgumentsNotProvided() {
        Arguments arguments = createArguments();
        Assertions.assertThatThrownBy(() -> arguments.parse(List.of("--t1", "hello", "--t3", "world")))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private static Arguments createArguments() {
        return new Arguments()
            .addArgument(new Argument("t1", true))
            .addArgument(new Argument("t2", true))
            .addArgument(new Argument("t3", false));
    }
}
