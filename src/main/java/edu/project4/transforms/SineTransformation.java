package edu.project4.transforms;

import edu.project4.model.world.Point;

public class SineTransformation implements Transformation {
    private double weight = Math.random() * 3;

    @Override
    public Point apply(Point point) {
        return new Point(weight * Math.sin(point.x()), weight * Math.sin(point.y()));
    }
}
