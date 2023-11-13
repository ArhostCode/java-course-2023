package edu.project2.solve;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
