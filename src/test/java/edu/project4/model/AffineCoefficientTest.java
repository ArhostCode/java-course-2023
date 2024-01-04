package edu.project4.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AffineCoefficientTest {

    @Test
    @DisplayName("Тестирование AffineCoefficient#generateRandom")
    public void generateRandom_shouldReturnCorrectCoefficient() {
        AffineCoefficient actual = AffineCoefficient.generateRandom(new Random());
        Assertions.assertThat(isAffine(actual.a(), actual.b(), actual.c(), actual.d(), actual.e(), actual.f()))
            .isTrue();
    }

    private static boolean isAffine(double a, double b, double c, double d, double e, double f) {
        return ((a * a + d * d) < 1) && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }
}
