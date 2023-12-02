package edu.project4.model;

import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FractalImageTest {

    @Test
    @DisplayName("Тестирование FractalImage#pixel")
    public void pixel_shouldReturnCorrectPixel() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel expected = image.data()[5 * 10 + 5];
        expected.setHitCount(5);
        Pixel actual = image.pixel(5, 5);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование FractalImage#pixel")
    public void pixel_shouldReturnNull() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel actual = image.pixel(15, 15);
        Assertions.assertThat(actual).isNull();
    }

    @Test
    @DisplayName("Тестирование FractalImage#contains")
    public void contains_shouldReturnTrue() {
        FractalImage image = FractalImage.create(10, 10);
        Assertions.assertThat(image.contains(5, 5)).isTrue();
    }

    @Test
    @DisplayName("Тестирование FractalImage#contains")
    public void contains_shouldReturnFalse() {
        FractalImage image = FractalImage.create(10, 10);
        Assertions.assertThat(image.contains(15, 15)).isFalse();
    }

}
