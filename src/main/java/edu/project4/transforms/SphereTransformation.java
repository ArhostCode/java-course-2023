package edu.project4.transforms;

import edu.project4.model.world.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x() / (point.x() * point.x() + point.y() * point.y());
        double y = point.y() / (point.x() * point.x() + point.y() * point.y());
        return new Point(x, y);
    }
}
