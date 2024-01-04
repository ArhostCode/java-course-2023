package edu.project4.transforms;

import edu.project4.model.world.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return point;
    }
}
