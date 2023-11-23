package edu.hw5.task6;

public final class SubstringChecker {

    private SubstringChecker() {
    }

    public static boolean isSubstring(String original, String substring) {
        return original.matches(".*" + substring + ".*");
    }
}
