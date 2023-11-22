package edu.project4.transforms;

import edu.project4.model.world.Point;
import java.util.concurrent.ThreadLocalRandom;

public class SineTransformation implements Transformation {
    private double weight = ThreadLocalRandom.current().nextDouble() * 3;

    @Override
    public Point apply(Point point) {
        return new Point(weight * Math.sin(point.x()), weight * Math.sin(point.y()));
    }
}
