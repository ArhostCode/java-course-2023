package edu.project4.utils;

import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FractalImageUtilsTest {

    @Test
    @DisplayName("Тестирование FractalImageUtils#resolvePixel")
    public void resolvePixel_shouldReturnCorrectPixel() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel expected = image.pixel(5, 5);
        expected.setHitCount(5);
        Pixel actual = FractalImageUtils.resolvePixel(
            new Rect(0, 0, 100, 100),
            new Point(50, 50),
            image
        );
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование FractalImageUtils#resolvePixel - выход за пределы изображения")
    public void resolvePixel_shouldReturnNull_whenPointIsOutOfImage() {
        FractalImage image = FractalImage.create(10, 10);
        Pixel actual = FractalImageUtils.resolvePixel(
            new Rect(0, 0, 100, 100),
            new Point(102, 50),
            image
        );
        Assertions.assertThat(actual).isNull();
    }

}
