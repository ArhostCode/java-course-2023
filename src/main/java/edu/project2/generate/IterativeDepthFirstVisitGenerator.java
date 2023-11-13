package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.LinkedPositionElement;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Идея алгоритма:
 * 1. Выбираем стартовую ячейку
 * 2. Добавляем ячейку в стек
 * 3. Пока стек не пустой
 * 3.1. Берем ячейку из стека
 * 3.1. Добавляем её соседей в стек
 * 3.2. Проделываем стену между ячейкой и соседней
 *
 * @author ardyc
 */
public class IterativeDepthFirstVisitGenerator extends DepthFirstVisitGenerator {

    @Override
    protected void depthFirstGenerate(Cell start) {
        Stack<LinkedPositionElement> cells = new Stack<>();
        cells.push(new LinkedPositionElement(null, start.x(), start.y()));

        while (!cells.isEmpty()) {
            LinkedPositionElement current = cells.pop();
            if (isVisited(current.x(), current.y())) {
                continue;
            }
            visit(current.x(), current.y());
            if (current.parent() != null) {
                markWallAsPassage(
                    mazeGrid[current.parent().y()][current.parent().x()],
                    mazeGrid[current.y()][current.x()]
                );
            }
            List<Cell> neighbours = getValidNeighbours(current.x(), current.y());
            mazeGrid[current.y()][current.x()] = new Cell(current.x(), current.y(), Cell.Type.PASSAGE);
            if (neighbours.isEmpty()) {
                continue;
            }
            Collections.shuffle(neighbours);

            for (Cell newNeighbour : neighbours) {
                cells.push(new LinkedPositionElement(current, newNeighbour.x(), newNeighbour.y()));
            }
        }

    }
}
