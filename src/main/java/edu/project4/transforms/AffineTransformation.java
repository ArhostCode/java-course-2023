package edu.project4.transforms;

import edu.project4.model.AffineCoefficient;
import edu.project4.model.world.Point;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AffineTransformation implements Transformation {

    private final AffineCoefficient affineCoefficient;

    @Override
    public Point apply(Point point) {
        double x = affineCoefficient.a() + point.x() * affineCoefficient.b() + point.y() * affineCoefficient.c();
        double y = affineCoefficient.d() + point.x() * affineCoefficient.e() + point.y() * affineCoefficient.f();
//        System.out.println(x + " " + y);
        return new Point(x, y);
    }
}
