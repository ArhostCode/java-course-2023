package edu.hw5.task4;

public final class PasswordValidator {

    private PasswordValidator() {
    }

    public static boolean isValid(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }

}
