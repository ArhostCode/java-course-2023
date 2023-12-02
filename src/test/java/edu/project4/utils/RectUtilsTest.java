package edu.project4.utils;

import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RectUtilsTest {

    @Test
    @DisplayName("Тестирование RectUtils#rotatePoint")
    public void rotatePoint_shouldReturnCorrectPoint() {
        Rect rect = new Rect(0, 0, 10, 10);
        Point point = new Point(0, 5);
        double angle = Math.PI / 2;
        Point expected = new Point(5, 0);
        Point actual = RectUtils.rotatePoint(rect, point, angle);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование RectUtils#randomPoint")
    public void randomPoint_shouldReturnCorrectPoint() {
        Rect rect = new Rect(0, 0, 10, 10);
        Point actual = RectUtils.randomPoint(rect);
        Assertions.assertThat(rect.contains(actual)).isTrue();
    }
}
