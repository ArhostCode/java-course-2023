package edu.hw7.task4;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MonteCarloPiComputerTest {

    @Test
    @DisplayName("Тестирование MonteCarloPiComputer#computePI")
    public void computePI_shouldReturnCorrectValue() {
        Assertions.assertThat(MonteCarloPiComputer.computePI(1000000000))
            .isCloseTo(Math.PI, Offset.offset(0.01));
    }

    @Test
    @DisplayName("Тестирование MonteCarloPiComputer#computePIParallel")
    public void computePIParallel_shouldReturnCorrectValue() {
        Assertions.assertThat(MonteCarloPiComputer.computePIParallel(1000000000, 8))
            .isCloseTo(Math.PI, Offset.offset(0.01));
    }

}
