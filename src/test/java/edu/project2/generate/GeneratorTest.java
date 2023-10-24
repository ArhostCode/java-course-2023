package edu.project2.generate;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solve.DepthFirstSolver;
import edu.project2.solve.Solver;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class GeneratorTest {

    protected Stream<Arguments> sizes() {
        return Stream.of(
            Arguments.of(5, 5),
            Arguments.of(7, 5),
            Arguments.of(35, 35),
            Arguments.of(101, 151)
        );
    }

    protected boolean isPassable(Maze maze) {
        Solver solver = new DepthFirstSolver();
        return !solver.solve(maze, new Coordinate(1, 1), new Coordinate(maze.getWidth() - 2, maze.getHeight() - 2))
            .isEmpty();
    }
}
