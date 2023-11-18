package edu.project4.utils;

import edu.project4.model.world.Point;
import edu.project4.model.world.Rect;

public final class RectUtils {

    private RectUtils() {
    }

    public static Point randomPoint(Rect rect) {
        return new Point(rect.x() + Math.random() * rect.width(), rect.y() + Math.random() * rect.height());
    }

    public static Point rotatePoint(Rect rect, Point point, double angle) {
        double centerX = rect.x() + rect.width() / 2;
        double centerY = rect.y() + rect.height() / 2;
        double x = (point.x() - centerX) * Math.cos(angle)
            - (point.y() - centerY) * Math.sin(angle) + centerX;
        double y = (point.x() - centerX) * Math.sin(angle)
            + (point.y() - centerY) * Math.cos(angle) + centerY;
        return new Point(x, y);
    }

}
