package edu.hw1.task4;

public final class StringFixer {

    private StringFixer() {
    }

    public static String fix(String original) {
        if (original == null) {
            return "";
        }

        StringBuilder fixBuilder = new StringBuilder();
        char[] originalChars = original.toCharArray();
        for (int i = 0; i < originalChars.length; i++) {
            if (i % 2 == 1) {
                fixBuilder.append(originalChars[i]).append(originalChars[i - 1]);
            } else if (i == originalChars.length - 1) {
                fixBuilder.append(originalChars[i]);
            }
        }
        return fixBuilder.toString();
    }

}
