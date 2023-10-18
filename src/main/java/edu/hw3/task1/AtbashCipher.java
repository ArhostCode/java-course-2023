package edu.hw3.task1;

public final class AtbashCipher {

    private AtbashCipher() {
    }

    public static String encode(String original) {
        if (original == null) {
            throw new NullPointerException("Original string must be not null");
        }
        StringBuilder atbashStringBuilder = new StringBuilder();
        for (char c : original.toCharArray()) {
            if (isLatinLetter(c)) {
                atbashStringBuilder.append(getMirrorLetter(c));
            } else {
                atbashStringBuilder.append(c);
            }
        }
        return atbashStringBuilder.toString();
    }

    private static boolean isLatinLetter(char letter) {
        return (letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z');
    }

    private static char getMirrorLetter(char letter) {
        if (Character.isUpperCase(letter)) {
            return (char) ('Z' - (letter - 'A'));
        }
        return (char) ('z' - (letter - 'a'));
    }

}
