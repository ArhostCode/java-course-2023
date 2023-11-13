package edu.project2.solver;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solve.BreadthFirstSolver;
import edu.project2.solve.Solver;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BreadthFirstSolverTest extends SolverTest {

    @Test
    @DisplayName("Тест BreadthSolver#solve на прохождение лабиринта")
    public void solve_shouldReturnCorrectPathInMaze() {
        Maze maze = createMaze();
        Solver solver = new BreadthFirstSolver();

        final Coordinate start = new Coordinate(1, 1);
        final Coordinate end = new Coordinate(5, 5);

        List<Coordinate> coordinates = solver.solve(maze, start, end);
        Assertions.assertThat(isPathValid(coordinates, start, end)).isTrue();
    }

}
