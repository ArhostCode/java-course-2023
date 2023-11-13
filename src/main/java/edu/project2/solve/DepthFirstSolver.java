package edu.project2.solve;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepthFirstSolver implements Solver {

    private Maze maze;
    private boolean[][] visitedCells;
    private List<Coordinate> answer;

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        answer = new ArrayList<>();
        this.maze = maze;
        if (isCoordinateNotInMaze(start) || isCoordinateNotInMaze(end)) {
            return Collections.emptyList();
        }
        isReachable(start.x(), start.y(), end.x(), end.y());
        return answer.reversed();
    }

    private boolean isCoordinateNotInMaze(Coordinate coordinate) {
        return coordinate.x() < 0
            || coordinate.y() < 0
            || coordinate.x() >= maze.getWidth()
            || coordinate.y() >= maze.getHeight();
    }

    private boolean isReachable(int currentX, int currentY, int endX, int endY) {
        if (visitedCells[currentY][currentX] || !isInBound(currentX, currentY)
            || maze.getGrid()[currentY][currentX].type() == Cell.Type.WALL) {
            return false;
        }
        visitedCells[currentY][currentX] = true;
        if (currentX == endX && currentY == endY) {
            answer.add(new Coordinate(currentX, currentY));
            return true;
        }
        boolean isReachable = isReachable(currentX + 1, currentY, endX, endY)
            || isReachable(currentX - 1, currentY, endX, endY)
            || isReachable(currentX, currentY + 1, endX, endY)
            || isReachable(currentX, currentY - 1, endX, endY);

        if (isReachable) {
            answer.add(new Coordinate(currentX, currentY));
            return true;
        }
        return false;
    }

    private boolean isInBound(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.getWidth() && y < maze.getHeight();
    }
}
