package edu.project2.model;

public final class Maze {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Maze(int width, int height, Cell[][] grid) {
        if (!isValid(grid) || width % 2 == 0 || height % 2 == 0) {
            throw new IllegalArgumentException("Maze data is not valid");
        }
        this.width = width;
        this.height = height;
        this.grid = grid;
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

    private boolean isValid(Cell[][] grid) {
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
