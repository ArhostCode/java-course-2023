package edu.project2.generate;

import edu.project2.model.Cell;
import edu.project2.model.Maze;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbstractGeneratorTest {

    private AbstractGenerator abstractGenerator = new AbstractGenerator() {
        @Override
        protected Maze performGenerationAlgorithm() {
            return null;
        }
    };

    @Test
    @DisplayName("Тест AbstractGenerator#fillGridWalls на правильное заполнение массива")
    public void fillGridWalls_shouldCorrectFillsGrid() {
        abstractGenerator.initializeGenerator(5, 5);
        abstractGenerator.fillGridWalls();
        Assertions.assertThat(isWallsFillsCorrect(abstractGenerator.createMaze())).isTrue();
    }

    @Test
    @DisplayName("Тест AbstractGenerator#generate на неверных входных данных")
    public void generate_shouldThrowIllegalArgumentException_whenInputDataIncorrect() {
        Assertions.assertThatThrownBy(() -> abstractGenerator.generate(2, 2))
            .isInstanceOf(IllegalArgumentException.class);
    }

    private boolean isWallsFillsCorrect(Maze maze) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if ((i % 2 == 0 || j % 2 == 0) && maze.getGrid()[i][j].type() != Cell.Type.WALL) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    @DisplayName("Тест AbstractGenerator#markWallAsPassage на правильное заполнение массива")
    public void markWallAsPassage_shouldCorrectFillPassage() {
        abstractGenerator.initializeGenerator(5, 5);
        abstractGenerator.fillGridWalls();
        abstractGenerator.markWallAsPassage(abstractGenerator.mazeGrid[1][1], abstractGenerator.mazeGrid[1][3]);
        Assertions.assertThat(abstractGenerator.mazeGrid[1][2].type()).isEqualTo(Cell.Type.PASSAGE);
    }

}
