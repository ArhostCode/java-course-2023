package edu.project2.generate;

import edu.project2.model.Maze;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DepthFirstVisitGeneratorTest extends GeneratorTest {

    @DisplayName("Тест DepthFirstVisitGenerator#generate на создание проходимого лабиринта")
    @ParameterizedTest(name = "{0} {1} - лабиринт проходим")
    @MethodSource("sizes")
    public void generate_shouldReturnCorrectMaze(int width, int height) {
        Maze maze = new DepthFirstVisitGenerator().generate(width, height);
        Assertions.assertThat(isPassable(maze)).isTrue();
    }

}
