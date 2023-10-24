package edu.project2.model;

public final class Maze {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    private Maze(int width, int height, Cell[][] grid) {
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public static Maze create(int width, int height, Cell[][] grid) {
        Maze maze = new Maze(width, height, grid);
        if (!maze.isValid() || width % 2 == 0 || height % 2 == 0) {
            throw new IllegalArgumentException("Maze data is not valid");
        }
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private boolean isValid() {
        if (grid.length != height) {
            return false;
        }
        for (Cell[] cells : grid) {
            if (cells.length != width) {
                return false;
            }
        }
        return true;
    }
}
