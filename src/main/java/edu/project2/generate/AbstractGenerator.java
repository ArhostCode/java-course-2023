package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.Pair;
import edu.project2.render.UnicodeRenderer;

public abstract class AbstractGenerator implements Generator {
    protected static final int WALL_OFFSET = 2;
    protected static final int[][]
        NEIGHBOUR_PADDINGS = {{0, -WALL_OFFSET}, {-WALL_OFFSET, 0}, {WALL_OFFSET, 0}, {0, WALL_OFFSET}};
    protected Cell[][] mazeGrid;
    protected int height;
    protected int width;

    protected abstract Maze performGenerationAlgorithm();

    @Override
    public Maze generate(int width, int height) {
        final int minSize = 5;
        if (width < minSize || height < minSize || width % 2 == 0 || height % 2 == 0) {
            throw new IllegalArgumentException("Height and width must be greater than 4 and odd");
        }
        initializeGenerator(width, height);
        fillGridWalls();
        return performGenerationAlgorithm();
    }

    protected void fillGridWalls() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == height - 1 || j == width - 1 || i % 2 == 0 || j % 2 == 0) {
                    mazeGrid[i][j] = new Cell(j, i, Cell.Type.WALL);
                } else {
                    mazeGrid[i][j] = new Cell(j, i, Cell.Type.PASSAGE);
                }
            }
        }
    }

    protected void initializeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        mazeGrid = new Cell[height][width];
    }

    protected Pair<Cell> getConnectedCells(int wallX, int wallY) {
        if (wallY % 2 == 0) {
            if (isInBound(wallX, wallY - 1) && isInBound(wallX, wallY + 1)) {
                return new Pair<>(mazeGrid[wallY - 1][wallX], mazeGrid[wallY + 1][wallX]);
            }
        } else {
            if (isInBound(wallX - 1, wallY) && isInBound(wallX + 1, wallY)) {
                return new Pair<>(mazeGrid[wallY][wallX - 1], mazeGrid[wallY][wallX + 1]);
            }
        }
        return null;
    }

    protected void markWallAsPassage(Cell current, Cell end) {
        int wallX = current.x() + (end.x() - current.x()) / 2;
        int wallY = current.y() + (end.y() - current.y()) / 2;
        mazeGrid[wallY][wallX] = new Cell(wallX, wallY, Cell.Type.PASSAGE);
    }

    protected Maze createMaze() {
        return Maze.create(width, height, mazeGrid);
    }

    protected boolean isInBound(int x, int y) {
        return x > 0 && y > 0 && x < width - 1 && y < height - 1;
    }
}
