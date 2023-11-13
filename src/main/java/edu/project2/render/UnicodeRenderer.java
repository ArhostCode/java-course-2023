package edu.project2.render;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.Collections;
import java.util.List;

public class UnicodeRenderer implements Renderer {
    @Override
    public String render(Maze maze) {
        return render(maze, Collections.emptyList());
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder mazeBuilder = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(new Coordinate(j, i))) {
                    mazeBuilder.append("\uD83D\uDFE9");
                } else {
                    if (maze.getGrid()[i][j].type() == Cell.Type.WALL) {
                        mazeBuilder.append('⬛');
                    } else {
                        mazeBuilder.append('⬜');
                    }
                }
            }
            mazeBuilder.append('\n');
        }
        return mazeBuilder.toString();
    }
}
