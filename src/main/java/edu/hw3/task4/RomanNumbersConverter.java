package edu.hw3.task4;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("checkstyle:MagicNumber") // Needed because not reasonable extract all roman numbers in final fields
public final class RomanNumbersConverter {

    private static final List<RomanLiteral> ROMAN_LITERALS;

    static {
        ROMAN_LITERALS = new ArrayList<>();
        ROMAN_LITERALS.add(new RomanLiteral("M", 1000));
        ROMAN_LITERALS.add(new RomanLiteral("CM", 900));
        ROMAN_LITERALS.add(new RomanLiteral("D", 500));
        ROMAN_LITERALS.add(new RomanLiteral("CD", 400));
        ROMAN_LITERALS.add(new RomanLiteral("C", 100));
        ROMAN_LITERALS.add(new RomanLiteral("XC", 90));
        ROMAN_LITERALS.add(new RomanLiteral("L", 50));
        ROMAN_LITERALS.add(new RomanLiteral("XL", 40));
        ROMAN_LITERALS.add(new RomanLiteral("X", 10));
        ROMAN_LITERALS.add(new RomanLiteral("IX", 9));
        ROMAN_LITERALS.add(new RomanLiteral("V", 5));
        ROMAN_LITERALS.add(new RomanLiteral("IV", 4));
        ROMAN_LITERALS.add(new RomanLiteral("I", 1));
    }

    private RomanNumbersConverter() {
    }

    public static String convertToRomanNumber(int value) {
        if (value <= 0 || value >= 4000) {
            throw new IllegalArgumentException("Value must be between 1 and 3999");
        }
        int number = value;
        StringBuilder romanNumber = new StringBuilder();
        int lastIndex = 0;
        while (number > 0) {
            for (int i = lastIndex; i < ROMAN_LITERALS.size(); i++) {
                RomanLiteral romanLiteral = ROMAN_LITERALS.get(i);
                if (number >= romanLiteral.num) {
                    number -= romanLiteral.num;
                    romanNumber.append(romanLiteral.literal);
                    lastIndex = i;
                    break;
                }
            }
        }
        return romanNumber.toString();
    }

    private record RomanLiteral(String literal, int num) {
    }
}
