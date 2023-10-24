package edu.project2.generate;

import edu.project2.model.Cell;

public abstract class AbstractStoringVisitGenerator extends AbstractGenerator {

    private boolean[][] visitedCells;

    @Override
    protected void initializeGenerator(int width, int height) {
        super.initializeGenerator(width, height);
        visitedCells = new boolean[height][width];
    }

    protected boolean isValidElement(int x, int y) {
        return isInBound(x, y) && !isVisited(x, y);
    }

    protected boolean isVisited(int x, int y) {
        return visitedCells[y][x];
    }

    protected boolean isVisited(Cell cell) {
        return isVisited(cell.x(), cell.y());
    }

    protected void visit(int x, int y) {
        visitedCells[y][x] = true;
    }

    protected void visit(Cell cell) {
        visit(cell.x(), cell.y());
    }
}
