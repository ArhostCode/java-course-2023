package edu.hw5.task5;

public final class RussianCarNumbersValidator {
    private RussianCarNumbersValidator() {
    }

    public static boolean isValid(String carNumber) {
        return carNumber.matches("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
    }

}
