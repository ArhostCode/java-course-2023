package edu.project2.lifecycle;

import edu.project2.generate.DepthFirstVisitGenerator;
import edu.project2.generate.Generator;
import edu.project2.generate.IterativeDepthFirstVisitGenerator;
import edu.project2.generate.KruskalGenerator;
import edu.project2.generate.PrimGenerator;
import edu.project2.model.Coordinate;
import edu.project2.model.Maze;
import edu.project2.model.Pair;
import edu.project2.output.Message;
import edu.project2.output.UserWriter;
import edu.project2.reader.UserReader;
import edu.project2.render.Renderer;
import edu.project2.render.UnicodeRenderer;
import edu.project2.solve.BreadthFirstSolver;
import edu.project2.solve.DepthFirstSolver;
import edu.project2.solve.Solver;
import java.util.List;

public class MazeTaskLifeCycle {

    private final UserWriter userWriter;
    private final UserReader userReader;
    private List<Runnable> actions =
        List.of(this::inputSizes, this::inputAlgorithm, this::inputSolverAlgorithm, this::inputCoordinates);
    private boolean isRun = true;
    private int width;
    private int height;
    private Generator generator;
    private Solver solver;
    private Pair<Coordinate> coordinates;

    public MazeTaskLifeCycle(UserReader userReader, UserWriter userWriter) {
        this.userReader = userReader;
        this.userWriter = userWriter;
    }

    @SuppressWarnings({"checkstyle:MagicNumber"})
    public void run() {
        userWriter.write(Message.HELLO_MESSAGE);
        userWriter.write(Message.WRITE_SIZES);
        for (Runnable action : actions) {
            action.run();
            if (!isRun) {
                return;
            }
        }
        Maze maze = generator.generate(width, height);
        Renderer renderer = new UnicodeRenderer();
        userWriter.write(renderer.render(maze));
        List<Coordinate> path = solver.solve(maze, coordinates.first(), coordinates.second());
        userWriter.write(renderer.render(maze, path));
    }

    private void inputSizes() {
        final int minMazeSize = 5;
        width = userReader.readInt();
        height = userReader.readInt();
        if (height < minMazeSize || width < minMazeSize) {
            userWriter.write(Message.WRONG_INPUT);
            isRun = false;
        }
    }

    @SuppressWarnings("checkstyle:MagicNumber") // Not reasonable extract 4 numbers in constant
    private void inputAlgorithm() {
        userWriter.write(Message.WRITE_ALGORITHM);
        int algorithm = userReader.readInt();
        if (algorithm < 1 || algorithm > 4) {
            userWriter.write(Message.WRONG_INPUT);
            isRun = false;
            return;
        }
        generator = switch (algorithm) {
            case 1 -> new DepthFirstVisitGenerator();
            case 2 -> new IterativeDepthFirstVisitGenerator();
            case 3 -> new KruskalGenerator();
            case 4 -> new PrimGenerator();
            default -> throw new IllegalStateException("Неверный алгоритм генерации: " + algorithm);
        };
    }

    private void inputSolverAlgorithm() {
        userWriter.write(Message.WRITE_SOLVER_ALGORITHM);
        int algorithm = userReader.readInt();
        if (algorithm < 1 || algorithm > 2) {
            userWriter.write(Message.WRONG_INPUT);
            isRun = false;
            return;
        }
        solver = switch (algorithm) {
            case 1 -> new DepthFirstSolver();
            case 2 -> new BreadthFirstSolver();
            default -> throw new IllegalStateException("Неверный алгоритм решения: " + algorithm);
        };
    }

    private void inputCoordinates() {
        userWriter.write(Message.WRITE_COORDINATES);
        int x1 = userReader.readInt();
        int y1 = userReader.readInt();
        int x2 = userReader.readInt();
        int y2 = userReader.readInt();
        coordinates = new Pair<>(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }
}
