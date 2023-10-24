package edu.project2.render;

import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
