package edu.hw9.task3;

import edu.project2.generate.DepthFirstVisitGenerator;
import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solve.Solver;
import java.util.List;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultiThreadDepthFirstSolverTest {

    @SneakyThrows
    @Test
    @DisplayName("Тестирование MultiThreadDepthFirstSolver#solve")
    public void solve_shouldReturnCorrectPathInMaze() {
        Maze maze = new DepthFirstVisitGenerator().generate(7, 7);
        Solver solver = new MultiThreadDepthFirstSolver();

        final Coordinate start = new Coordinate(1, 1);
        final Coordinate end = new Coordinate(5, 5);

        List<Coordinate> coordinates = solver.solve(maze, start, end);
        Assertions.assertThat(isPathValid(maze, coordinates, start, end)).isTrue();

    }

    private boolean isPathValid(Maze maze, List<Coordinate> coordinates, Coordinate start, Coordinate end) {
        if (!coordinates.contains(start) || !coordinates.contains(end)) {
            return false;
        }
        for (int i = 1; i < coordinates.size(); i++) {
            Coordinate coordinate = coordinates.get(i);
            Cell cell = maze.getGrid()[coordinate.y()][coordinate.x()];
            int distance = Math.abs(coordinate.x() - coordinates.get(i - 1).x()) +
                Math.abs(coordinate.y() - coordinates.get(i - 1).y());
            if (distance != 1 || cell.type() == Cell.Type.WALL) {
                return false;
            }
        }
        return true;
    }

}
