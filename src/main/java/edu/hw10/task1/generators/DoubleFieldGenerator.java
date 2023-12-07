package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class DoubleFieldGenerator implements FieldGenerator<Double> {
    @Override
    public Double generate(Random random, Annotation[] annotations) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            }
        }
        return random.nextDouble(min, max + 1);
    }
}
