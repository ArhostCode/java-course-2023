package edu.project4.utils;

import edu.project4.model.image.FractalImage;
import edu.project4.model.image.Pixel;
import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FractalImageUtils {

    public static Pixel resolvePixel(Rect rect, Point point, FractalImage image) {
        if (!rect.contains(point)) {
            return null;
        }
        return image.pixel(
            (int) ((point.x() - rect.x()) / rect.width() * image.width()),
            (int) ((point.y() - rect.y()) / rect.height() * image.height())
        );
    }

}
