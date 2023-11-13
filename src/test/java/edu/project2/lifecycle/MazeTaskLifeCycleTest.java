package edu.project2.lifecycle;

import edu.project2.output.Message;
import edu.project2.output.UserWriter;
import edu.project2.reader.UserReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MazeTaskLifeCycleTest {

    private final List<String> outputData = new ArrayList<>();
    private final Queue<Integer> inputData = new ArrayDeque<>();

    private final UserWriter writer = outputData::add;
    private final UserReader reader = inputData::poll;
    private MazeTaskLifeCycle mazeTaskLifeCycle;

    @BeforeEach
    public void initEach() {
        outputData.clear();
        inputData.clear();
        mazeTaskLifeCycle = new MazeTaskLifeCycle(reader, writer);
    }

    @Test
    @DisplayName("Тест MazeTaskLifeCycle#run на верных входных данных")
    public void run_shouldCorrectOutputData() {
        inputData.addAll(List.of(7, 7, 1, 1, 1, 1, 5, 5));
        mazeTaskLifeCycle.run();

        Assertions.assertThat(outputData.subList(0, 5)).containsExactly(
            Message.HELLO_MESSAGE.getText(),
            Message.WRITE_SIZES.getText(),
            Message.WRITE_ALGORITHM.getText(),
            Message.WRITE_SOLVER_ALGORITHM.getText(),
            Message.WRITE_COORDINATES.getText()
        );
    }

    @Test
    @DisplayName("Тест MazeTaskLifeCycle#run на неверных входных данных")
    public void run_shouldCorrectOutputData_whenDataIncorrect() {
        inputData.addAll(List.of(4, 7, 1, 1, 1, 1, 5, 5));
        mazeTaskLifeCycle.run();

        Assertions.assertThat(outputData.subList(0, 3)).containsExactly(
            Message.HELLO_MESSAGE.getText(),
            Message.WRITE_SIZES.getText(),
            Message.WRONG_INPUT.getText()
        );
    }

    @Test
    @DisplayName("Тест MazeTaskLifeCycle#run на неверных входных данных алгоритма генерации")
    public void run_shouldCorrectOutputData_whenGenerationAlgorithmIncorrect() {
        inputData.addAll(List.of(7, 7, 5, 1, 1, 1, 5, 5));
        mazeTaskLifeCycle.run();

        Assertions.assertThat(outputData.subList(0, 4)).containsExactly(
            Message.HELLO_MESSAGE.getText(),
            Message.WRITE_SIZES.getText(),
            Message.WRITE_ALGORITHM.getText(),
            Message.WRONG_INPUT.getText()
        );
    }

}
