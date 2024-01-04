package edu.project4.transforms;

import edu.project4.model.AffineCoefficient;
import edu.project4.model.world.Point;
import java.awt.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralTransformsTest {

    @Test
    @DisplayName("Тестирование LinearTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformLinearly() {
        LinearTransformation transformation = new LinearTransformation();
        Point expected = new Point(5, 5);
        Point actual = transformation.apply(expected);
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тестирование AffineTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformAffinely() {
        AffineCoefficient coefficient = new AffineCoefficient(
            -0.5820557028871267,
            0.12571172175301148,
            0.022642959436758536,
            0.43430404843812154,
            0.10785467283627392,
            0.7639116572989244,
            Color.RED
        );
        AffineTransformation transformation = new AffineTransformation(coefficient);
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(0.1597177030617234, 4.793135699114114));
    }

    @Test
    @DisplayName("Тестирование DiskTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformDisk() {
        DiskTransformation transformation = new DiskTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(-0.05535396184412049, -0.24379487055342172));
    }

    @Test
    @DisplayName("Тестирование HeartTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformHeart() {
        HeartTransformation transformation = new HeartTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(-4.7132755554838255, -5.2711510638643855));
    }

    @Test
    @DisplayName("Тестирование ExponentialTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformExponentially() {
        ExponentialTransformation transformation = new ExponentialTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(-54.598150033144236, 3.343172483872852E-14));
    }

    @Test
    @DisplayName("Тестирование HyperbolicTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformHyperbolic() {
        HyperbolicTransformation transformation = new HyperbolicTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(0.09999999999999999, 5.000000000000001));
    }

    @Test
    @DisplayName("Тестирование PolarTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformPolar() {
        PolarTransformation transformation = new PolarTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(0.25, 6.0710678118654755));
    }

    @Test
    @DisplayName("Тестирование SineTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformSine() {
        SineTransformation transformation = new SineTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(-0.9589242746631385, -0.9589242746631385));
    }

    @Test
    @DisplayName("Тестирование SphereTransformation#apply - преобразование пикселя")
    public void apply_shouldTransformSphere() {
        SphereTransformation transformation = new SphereTransformation();
        Point given = new Point(5, 5);
        Point actual = transformation.apply(given);
        Assertions.assertThat(actual).isEqualTo(new Point(0.1, 0.1));
    }
}
