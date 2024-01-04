package edu.project4.transforms;

import edu.project4.model.AffineCoefficient;
import edu.project4.model.world.Point;

public record AffineTransformation(AffineCoefficient affineCoefficient) implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = affineCoefficient.a() + point.x() * affineCoefficient.b() + point.y() * affineCoefficient.c();
        double y = affineCoefficient.d() + point.x() * affineCoefficient.e() + point.y() * affineCoefficient.f();
        return new Point(x, y);
    }
}
