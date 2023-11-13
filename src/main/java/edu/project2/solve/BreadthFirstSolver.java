package edu.project2.solve;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.LinkedPositionElement;
import edu.project2.model.Maze;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSolver implements Solver {

    private static final int[][] NEIGHBOURS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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
        createPath(start, end);
        return answer.reversed();
    }

    private boolean isCoordinateNotInMaze(Coordinate coordinate) {
        return coordinate.x() < 0
            || coordinate.y() < 0
            || coordinate.x() >= maze.getWidth()
            || coordinate.y() >= maze.getHeight();
    }

    private void createPath(Coordinate current, Coordinate end) {

        Queue<LinkedPositionElement> coordinates = new ArrayDeque<>();
        coordinates.offer(new LinkedPositionElement(null, current.x(), current.y()));

        LinkedPositionElement solve = null;

        while (!coordinates.isEmpty()) {
            LinkedPositionElement coordinate = coordinates.poll();
            visitedCells[coordinate.y()][coordinate.x()] = true;
            if (coordinate.x() == end.x() && coordinate.y() == end.y()) {
                solve = coordinate;
                break;
            }
            for (int[] neighbour : NEIGHBOURS) {
                int newX = coordinate.x() + neighbour[0];
                int newY = coordinate.y() + neighbour[1];
                if (isNotWallAndBound(newX, newY) && !visitedCells[newY][newX]) {
                    coordinates.offer(new LinkedPositionElement(coordinate, newX, newY));
                }
            }
        }
        if (solve == null) {
            return;
        }

        while (solve != null) {
            answer.add(new Coordinate(solve.x(), solve.y()));
            solve = solve.parent();
        }
    }

    private boolean isNotWallAndBound(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.getWidth() && y < maze.getHeight()
            && maze.getGrid()[y][x].type() != Cell.Type.WALL;
    }

}
