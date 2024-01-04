package edu.project4.transforms;

import edu.project4.model.world.Point;

public class SineTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
