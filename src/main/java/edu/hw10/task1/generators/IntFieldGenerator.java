package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class IntFieldGenerator implements FieldGenerator<Integer> {
    @Override
    public Integer generate(Random random, Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = (int) ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = (int) ((Max) annotation).value();
            }
        }
        return random.nextInt(min, max + 1);
    }
}
