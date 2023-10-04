package edu.hw1;

import edu.hw1.task8.HorsesOnBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TestHorsesOnBoard {

    @Test
    @DisplayName("Тест HorsesOnBoard.isSafeSituation(int[][]) с ситуацией, когда кони не могут убить друг друга")
    public void isSafeSituation_shouldReturnTrue() {

        int[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean actual = HorsesOnBoard.isSafeSituation(board);
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("Тест HorsesOnBoard.isSafeSituation(int[][]) с ситуацией, когда кони могут убить друг друга")
    public void isSafeSituation_shouldReturnFalse_whenSituationIsNotSafe() {

        int[][] board = {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        boolean actual = HorsesOnBoard.isSafeSituation(board);
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тест HorsesOnBoard.isSafeSituation(int[][]) с ситуацией, когда кони могут убить друг друга")
    public void isSafeSituation_shouldReturnFalse_whenSituationIsNotSafe_2() {

        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean actual = HorsesOnBoard.isSafeSituation(board);
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("Тест HorsesOnBoard.isSafeSituation(int[][]) с неправильными входными данными")
    public void isSafeSituation_shouldThrowIllegalArgumentException_whenDataIsIncorrect() {

        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
        };

        assertThatThrownBy(() -> {
            boolean actual = HorsesOnBoard.isSafeSituation(board);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тест HorsesOnBoard.isSafeSituation(int[][]) с null входными данными")
    public void isSafeSituation_shouldThrowNullPointerException_whenDataIsIncorrect() {

        int[][] board = null;

        assertThatThrownBy(() -> {
            boolean actual = HorsesOnBoard.isSafeSituation(board);
        }).isInstanceOf(NullPointerException.class);
    }

}
