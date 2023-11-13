package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import edu.project2.model.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Идея алгоритма:
 * 1. Создайте список всех стен и набор для каждой ячейки, каждая из которых содержит только эту одну ячейку.
 * 2. Для каждой стены в некотором случайном порядке:
 * 2.1. Если ячейки, разделенные этой стенкой, принадлежат разным наборам:
 * 2.1.1. Удалите текущую стену.
 * 2.1.2. Соедините наборы ранее разделенных ячеек.
 *
 * @author ardyc
 */
public class KruskalGenerator extends AbstractGenerator {

    private final Random random = new Random();
    private List<Cell> walls;
    private int[] parent;

    @Override
    protected Maze performGenerationAlgorithm() {
        initializeFields();
        generateKruskal();
        return createMaze();
    }

    private void initializeFields() {
        walls = new ArrayList<>();
        parent = new int[width * height];
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                Cell cell = mazeGrid[y][x];
                if (cell.type() == Cell.Type.WALL && !(x % 2 == 0 && y % 2 == 0)) {
                    walls.add(cell);
                }
                int index = x + width * y;
                parent[index] = index;
            }
        }
    }

    private void generateKruskal() {
        while (!walls.isEmpty()) {
            Cell wall = walls.remove(random.nextInt(walls.size()));
            Pair<Cell> cells = getConnectedCells(wall.x(), wall.y());
            if (cells == null) {
                continue;
            }
            if (find(cells.first()) != find(cells.second())) {
                mazeGrid[wall.y()][wall.x()] = new Cell(wall.x(), wall.y(), Cell.Type.PASSAGE);
                union(cells.first(), cells.second());
            }
        }
    }

    private int calculateCellIndex(Cell cell) {
        return cell.x() + cell.y() * width;
    }

    private int find(Cell vertex) {
        return find(calculateCellIndex(vertex));
    }

    private int find(int vertex) {
        if (parent[vertex] != vertex) {
            return find(parent[vertex]);
        }
        return vertex;
    }

    private void union(Cell c1, Cell c2) {
        union(calculateCellIndex(c1), calculateCellIndex(c2));
    }

    private void union(int c1, int c2) {
        int xSet = find(c1);
        int ySet = find(c2);
        parent[xSet] = ySet;
    }

}
