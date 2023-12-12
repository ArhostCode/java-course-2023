package edu.hw8.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

public class FibonacciComputerTest {

    @Test
    @DisplayName("Тестирование FibonacciComputer#computeList")
    public void computeList_shouldReturnCorrectResult() {
        List<Integer> list = IntStream.range(0, 13).boxed().toList();
        List<Integer> result = FibonacciComputer.computeList(list, 4);
        Assertions.assertThat(result).containsExactlyInAnyOrder(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    }

}
