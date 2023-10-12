package edu.hw2.task2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class FigureTest {

    private static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    // Given in task test
    @ParameterizedTest
    @MethodSource("rectangles")
    public void rectangleArea(Rectangle rect) {
        Rectangle rectangle = rect.withWidth(20);
        Rectangle result = rectangle.withHeight(10);
        assertThat(result.area()).isEqualTo(200.0);
    }

    @Test
    @DisplayName("Проверка площади создания иммутабельного квадрата из объекта другого квадрата")
    public void withSide_shouldCreateSquareWithGivenSide() {
        Square square = new Square(5).withSide(10);
        assertThat(square.area()).isEqualTo(100.0);
    }

    @Test
    @DisplayName("Проверка созданного иммутабельного прямоугольника на соотношение к реальным данным")
    public void withHeight_shouldCreateRectangleWithGivenHeight() {
        Rectangle rectangle = new Rectangle(2, 4).withHeight(10);
        assertThat(rectangle)
            .extracting("width", "height")
            .containsExactly(2, 10);
    }

}
