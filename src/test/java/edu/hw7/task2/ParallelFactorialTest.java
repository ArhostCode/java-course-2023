package edu.hw7.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParallelFactorialTest {

    @Test
    @DisplayName("Тестирование ParallelFactorial#computeFactorial")
    public void computeFactorial_shouldReturnCorrectInt() {
        Assertions.assertThat(ParallelFactorial.computeFactorial(5)).isEqualTo(120);
    }
}
