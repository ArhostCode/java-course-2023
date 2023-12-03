package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class FloatFieldGenerator implements FieldGenerator<Float> {
    @Override
    public Float generate(Random random, Annotation[] annotations) {
        float min = Float.MIN_VALUE;
        float max = Float.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        return random.nextFloat(min, max + 1);
    }
}
