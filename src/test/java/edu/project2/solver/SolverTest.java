package edu.project2.solver;

import edu.project2.model.Cell;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import java.util.List;

public class SolverTest {
    protected Maze createMaze() {
        Cell[][] cells = new Cell[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (i % 2 == 0 || j % 2 == 0) {
                    cells[i][j] = new Cell(j, i, Cell.Type.WALL);
                } else {
                    cells[i][j] = new Cell(j, i, Cell.Type.PASSAGE);
                }
            }
        }

        cells[3][2] = new Cell(2, 3, Cell.Type.PASSAGE);
        cells[1][2] = new Cell(2, 1, Cell.Type.PASSAGE);
        cells[2][1] = new Cell(1, 2, Cell.Type.PASSAGE);
        cells[3][4] = new Cell(4, 3, Cell.Type.PASSAGE);
        cells[2][5] = new Cell(5, 2, Cell.Type.PASSAGE);
        cells[4][5] = new Cell(5, 4, Cell.Type.PASSAGE);
        cells[5][2] = new Cell(2, 5, Cell.Type.PASSAGE);
        cells[4][3] = new Cell(3, 4, Cell.Type.PASSAGE);
        return Maze.create(7, 7, cells);
    }

    protected boolean isPathValid(List<Coordinate> coordinates, Coordinate start, Coordinate end) {
        if (!coordinates.contains(start) || !coordinates.contains(end)) {
            return false;
        }
        for (int i = 1; i < coordinates.size(); i++) {
            int distance = Math.abs(coordinates.get(i).x() - coordinates.get(i - 1).x()) +
                Math.abs(coordinates.get(i).y() - coordinates.get(i - 1).y());
            if (distance != 1) {
                return false;
            }
        }
        return true;
    }
}
