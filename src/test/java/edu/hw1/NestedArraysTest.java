package edu.hw1;

import edu.hw1.task3.NestedArrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class NestedArraysTest {

    @Test
    @DisplayName("Тест NestedArrays.isNestable(int[], int[]) - [1, 2, 3, 4], [0, 6]")
    public void isNestable_shouldReturnTrue_whenArrayIsNested() {
        int[] givenValue = {1, 2, 3, 4};
        int[] externalValue = {0, 6};

        boolean actual = NestedArrays.isNestable(givenValue, externalValue);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Тест NestedArrays.isNestable(int[], int[]) - [3, 1], [4, 0]")
    public void isNestable_shouldReturnTrue_whenArrayIsNested_1() {
        int[] givenValue = {3, 1};
        int[] externalValue = {0, 4};

        boolean actual = NestedArrays.isNestable(givenValue, externalValue);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Тест NestedArrays.isNestable(int[], int[]) - [9, 9, 8], [8, 9]")
    public void isNestable_shouldReturnFalse_whenArrayIsNotNested() {
        int[] givenValue = {9, 9, 8};
        int[] externalValue = {8, 9};

        boolean actual = NestedArrays.isNestable(givenValue, externalValue);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тест NestedArrays.isNestable(int[], int[]) - [1, 2, 3, 4], [2, 3]")
    public void isNestable_shouldReturnFalse_whenArrayIsNotNested_1() {
        int[] givenValue = {1, 2, 3, 4};
        int[] externalValue = {2, 3};

        boolean actual = NestedArrays.isNestable(givenValue, externalValue);

        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тест NestedArrays.isNestable(int[], int[]) - null, [2, 3]")
    public void isNestable_shouldReturnFalse_whenArrayIsNull() {
        int[] givenValue = null;
        int[] externalValue = {2, 3};

        boolean actual = NestedArrays.isNestable(givenValue, externalValue);

        assertThat(actual).isFalse();
    }

}
