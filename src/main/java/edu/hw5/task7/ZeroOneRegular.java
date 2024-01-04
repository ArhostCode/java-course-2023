package edu.hw5.task7;

public final class ZeroOneRegular {

    private ZeroOneRegular() {
    }

    /**
     * Содержит не менее 3 символов, причем третий символ равен 0
     */
    public static boolean containsAtLeastThreeSymbolsAndThirdIsZero(String string) {
        return string.matches("[01]{2}0[01]*");
    }

    /**
     * Начинается и заканчивается одним и тем же символом
     */
    public static boolean isFirstSymbolEqualsLast(String string) {
        return string.matches("(0[01]*0|1[01]*1|[01])");
    }

    /**
     * Длина не менее 1 и не более 3
     */
    public static boolean isLengthMoreOneAndLessThree(String string) {
        return string.matches("[01]{1,3}");
    }

}
