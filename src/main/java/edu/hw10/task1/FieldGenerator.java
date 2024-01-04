package edu.hw10.task1;

import java.lang.annotation.Annotation;
import java.util.Random;

public interface FieldGenerator<T> {
    T generate(Random random, Annotation[] annotations);
}
