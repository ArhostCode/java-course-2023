package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class LongFieldGenerator implements FieldGenerator<Long> {
    @Override
    public Long generate(Random random, Annotation[] annotations) {
        long min = Long.MIN_VALUE;
        long max = Long.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min) {
                min = ((Min) annotation).value();
            }
            if (annotation instanceof Max) {
                max = ((Max) annotation).value();
            }
        }
        return random.nextLong(min, max + 1);
    }

}
