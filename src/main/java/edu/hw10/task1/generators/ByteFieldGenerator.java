package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class ByteFieldGenerator implements FieldGenerator<Byte> {

    @Override
    public Byte generate(Random random, Annotation[] annotations) {
        int min = Byte.MIN_VALUE;
        int max = Byte.MAX_VALUE - 1;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (int) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = (int) maxAnnotation.value();
            }
        }
        return (byte) random.nextInt(min, max + 1);
    }
}
