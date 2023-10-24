package edu.project2.render;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.render.Renderer;
import edu.project2.render.UnicodeRenderer;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnicodeRendererTest {

    private final Renderer unicodeRenderer = new UnicodeRenderer();

    @Test
    @DisplayName("Тестирование UnicodeRenderer#render для вывода лабиринта")
    public void render_shouldReturnCorrectMazeString() {
        Maze maze = generateMaze(5, 5);
        Assertions.assertThat(unicodeRenderer.render(maze)).isEqualTo("""
            ⬛⬛⬛⬛⬛
            ⬛⬜⬛⬜⬛
            ⬛⬛⬛⬛⬛
            ⬛⬜⬛⬜⬛
            ⬛⬛⬛⬛⬛
            """);
    }

    @Test
    @DisplayName("Тестирование UnicodeRenderer#render для вывода лабиринта и пути")
    public void render_shouldReturnCorrectMazeString_whenGivenPath() {
        Maze maze = generateMaze(5, 5);
        List<Coordinate> path = new ArrayList<>();
        path.add(new Coordinate(1, 1));
        path.add(new Coordinate(3, 1));
        Assertions.assertThat(unicodeRenderer.render(maze, path)).isEqualTo("""
            ⬛⬛⬛⬛⬛
            ⬛🟩⬛🟩⬛
            ⬛⬛⬛⬛⬛
            ⬛⬜⬛⬜⬛
            ⬛⬛⬛⬛⬛
            """);
    }

    private Maze generateMaze(int width, int height) {
        Cell[][] cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(j, i, (i % 2 == 0) || (j % 2 == 0) ? Cell.Type.WALL : Cell.Type.PASSAGE);
            }
        }
        return Maze.create(width, height, cells);
    }

}
