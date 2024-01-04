package edu.hw10.task1.generators;

import edu.hw10.task1.FieldGenerator;
import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.Random;

public class StringFieldGenerator implements FieldGenerator<String> {

    private static final char[] ALPHABET =
        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final int DEFAULT_MIN_LENGTH = 10;
    private static final int DEFAULT_MAX_LENGTH = 100;

    @Override
    public String generate(Random random, Annotation[] annotations) {
        int minLength = DEFAULT_MIN_LENGTH;
        int maxLength = DEFAULT_MAX_LENGTH;

        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                minLength = (int) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                maxLength = (int) maxAnnotation.value();
            }
        }
        int length = random.nextInt(minLength, maxLength + 1);
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomStringBuilder.append(ALPHABET[random.nextInt(0, ALPHABET.length)]);
        }
        return randomStringBuilder.toString();
    }
}
