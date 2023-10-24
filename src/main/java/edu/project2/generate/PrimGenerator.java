package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Идея алгоритма:
 * 1. Начните с сетки, полной стен.
 * 2. Выберите ячейку, пометьте ее как часть лабиринта. Добавьте стены ячейки в список стен.
 * 3. Пока в списке есть стены:
 * 3.1. Выберите случайную стену из списка. Если посещена только одна из ячеек, разделенных стеной, то:
 * 3.1.1. Сделайте стену проходом и отметьте непройденную ячейку как часть лабиринта.
 * 3.1.2. Добавьте соседние стены ячейки в список стен.
 * 3.2. Удалите стену из списка.
 *
 * @author ardyc
 */
public class PrimGenerator extends AbstractStoringVisitGenerator {

    private static final int[][] DIRECTIONS = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};
    private final Random random = new Random();
    private List<Cell> walls;

    @Override
    protected Maze performGenerationAlgorithm() {
        generatePrim(mazeGrid[2][1]);
        return createMaze();
    }

    private void generatePrim(Cell start) {
        walls = new ArrayList<>();
        walls.add(mazeGrid[start.y()][start.x()]);
        while (!walls.isEmpty()) {
            Cell wall = walls.remove(random.nextInt(walls.size()));
            Pair<Cell> cells = getConnectedCells(wall.x(), wall.y());
            if (!isVisited(cells.first())) {
                processCell(cells.first());
            } else if (!isVisited(cells.second())) {
                processCell(cells.second());
            } else {
                continue;
            }
            markWallAsPassage(cells.first(), cells.second());
        }
    }

    private void processCell(Cell cell) {
        visit(cell);
        for (int[] direction : DIRECTIONS) {
            if (isInBound(cell.x() + direction[0], cell.y() + direction[1])) {
                walls.add(mazeGrid[cell.y() + direction[1]][cell.x() + direction[0]]);
            }
        }
    }

}
