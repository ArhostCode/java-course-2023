package edu.project2.model;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MazeTest {

    @Test
    @DisplayName("Тестирование Maze#create с верными входными данными")
    public void create_shouldReturnCorrectMaze() {
        Maze maze = new Maze(5, 5, createCells(5, 5));
        Assertions.assertThat(maze)
            .extracting("width", "height", "grid")
            .containsExactly(5, 5, createCells(5, 5));
    }

    @Test
    @DisplayName("Тестирование Maze#create с неверными размерами лабиринта")
    public void create_shouldThrowIllegalArgumentException_whenSizeIncorrect() {
        Assertions.assertThatThrownBy(() -> new Maze(1, 1, new Cell[][] {}))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("Maze data is not valid");
    }

    @Test
    @DisplayName("Тестирование Maze#create с неверными размерами сетки ячеек")
    public void create_shouldThrowIllegalArgumentException_whenGridSizeIncorrect() {
        Assertions.assertThatThrownBy(() -> new Maze(6, 6, createCells(5, 5)))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("Maze data is not valid");
    }

    @Test
    @DisplayName("Тестирование Maze#create с неверными размерами сетки ячеек")
    public void create_shouldThrowIllegalArgumentException_whenGridSizeIncorrect_2() {
        Assertions.assertThatThrownBy(() -> new Maze(4, 6, createCells(5, 6)))
            .isInstanceOf(IllegalArgumentException.class).hasMessage("Maze data is not valid");
    }

    private Cell[][] createCells(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(j, i, Cell.Type.WALL);
            }
        }
        return cells;
    }

}
