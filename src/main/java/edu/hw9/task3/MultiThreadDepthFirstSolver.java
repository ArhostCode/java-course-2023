package edu.hw9.task3;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.solve.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.RequiredArgsConstructor;

public class MultiThreadDepthFirstSolver implements Solver {
    private Maze maze;
    private boolean[][] visitedCells;

    private boolean isCoordinateNotInMaze(Coordinate coordinate) {
        return coordinate.x() < 0
            || coordinate.y() < 0
            || coordinate.x() >= maze.getWidth()
            || coordinate.y() >= maze.getHeight();
    }

    private boolean isInBound(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.getWidth() && y < maze.getHeight();
    }

    @Override
    public synchronized List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];
        this.maze = maze;
        if (isCoordinateNotInMaze(start) || isCoordinateNotInMaze(end)
            || maze.getGrid()[start.y()][start.x()].type() == Cell.Type.WALL) {
            return Collections.emptyList();
        }
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new RecursiveSolverTask(start.x(), start.y(), end.x(), end.y()));
    }

    @RequiredArgsConstructor
    private final class RecursiveSolverTask extends RecursiveTask<List<Coordinate>> {

        private static final int[][] PADDINGS = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };

        private final int x;
        private final int y;
        private final int endX;
        private final int endY;

        @Override
        protected List<Coordinate> compute() {
            // Not needed to synchronize with visitedCells or maze, because maze is ideal
            // i.e. We have only one path from start to end, and we will not produce cell crossing
            visitedCells[y][x] = true;
            List<Coordinate> coordinates = new ArrayList<>();
            if (x == endX && y == endY) {
                coordinates.add(new Coordinate(x, y));
                return coordinates;
            }
            List<ForkJoinTask<List<Coordinate>>> subTasks = createNeededTasks();
            for (ForkJoinTask<List<Coordinate>> subTask : subTasks) {
                List<Coordinate> subTaskResult = subTask.join();
                if (!subTaskResult.isEmpty()) {
                    coordinates.add(new Coordinate(x, y));
                    coordinates.addAll(subTaskResult);
                }
            }
            return coordinates;
        }

        private List<ForkJoinTask<List<Coordinate>>> createNeededTasks() {
            List<ForkJoinTask<List<Coordinate>>> tasks = new ArrayList<>();
            for (int[] padding : PADDINGS) {
                int newX = x + padding[0];
                int newY = y + padding[1];
                if (!isInBound(newX, newY) || visitedCells[newY][newX]
                    || maze.getGrid()[newY][newX].type() == Cell.Type.WALL) {
                    continue;
                }
                tasks.add(new RecursiveSolverTask(x + padding[0], y + padding[1], endX, endY).fork());
            }
            return tasks;
        }
    }
}
