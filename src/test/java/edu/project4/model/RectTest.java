package edu.project4.model;

import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RectTest {

    @Test
    @DisplayName("Тестирование Rect#contains")
    public void contains_shouldReturnTrue() {
        Rect rect = new Rect(0, 0, 10, 10);
        Assertions.assertThat(rect.contains(new Point(5, 5))).isTrue();
    }

    @Test
    @DisplayName("Тестирование Rect#contains")
    public void contains_shouldReturnFalse() {
        Rect rect = new Rect(0, 0, 10, 10);
        Assertions.assertThat(rect.contains(new Point(15, 15))).isFalse();
    }

}
