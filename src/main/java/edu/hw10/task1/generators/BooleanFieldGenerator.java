package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import java.lang.annotation.Annotation;
import java.util.Random;

public class BooleanFieldGenerator implements FieldGenerator<Boolean> {
    @Override
    public Boolean generate(Random random, Annotation[] annotations) {
        return random.nextBoolean();
    }
}
