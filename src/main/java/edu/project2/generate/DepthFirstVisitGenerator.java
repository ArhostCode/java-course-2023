package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Идея алгоритма:
 * 1. Выбираем стартовую ячейку
 * 2. Выбираем соседей этой ячейки
 * 2.1. Выбираем рандомного соседа.
 * 2.2. Проделываем стену между ячейкой и соседней
 * 3. Рекурсивно выполняем функцию
 *
 * @author ardyc
 */
public class DepthFirstVisitGenerator extends AbstractStoringVisitGenerator {

    @Override
    protected Maze performGenerationAlgorithm() {
        depthFirstGenerate(mazeGrid[1][1]);
        return createMaze();
    }

    protected void depthFirstGenerate(Cell cell) {
        visit(cell);
        List<Cell> neighbours = getValidNeighbours(cell.x(), cell.y());
        mazeGrid[cell.y()][cell.x()] = new Cell(cell.x(), cell.y(), Cell.Type.PASSAGE);
        if (neighbours.isEmpty()) {
            return;
        }
        Collections.shuffle(neighbours);

        for (Cell newNeighbour : neighbours) {
            if (isValidElement(newNeighbour.x(), newNeighbour.y())) {
                markWallAsPassage(cell, newNeighbour);
                depthFirstGenerate(newNeighbour);
            }
        }
    }

    protected List<Cell> getValidNeighbours(int currentX, int currentY) {
        List<Cell> neighbours = new ArrayList<>();
        for (var neighbour : NEIGHBOUR_PADDINGS) {
            int newX = currentX + neighbour[0];
            int newY = currentY + neighbour[1];
            if (isValidElement(newX, newY)) {
                neighbours.add(mazeGrid[newY][newX]);
            }
        }
        return neighbours;
    }

}
