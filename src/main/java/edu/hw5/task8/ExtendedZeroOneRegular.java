package edu.hw5.task8;

public final class ExtendedZeroOneRegular {

    private ExtendedZeroOneRegular() {
    }

    /**
     * Нечетной длины
     */
    public static boolean isOddLength(String string) {
        return string.matches("[01]([01][01])*");
    }

    /**
     * Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
     */
    public static boolean isZeroOddOrOneEven(String string) {
        return string.matches("(0([01][01])*|1[01]([01][01])*)");
    }

    /**
     * Количество 0 кратно 3
     * PS Считаю, что ноль кратен 3
     */
    public static boolean isZeroCountMultiplyThree(String string) {
        return string.matches("1*(1*01*01*0)*");
    }

    /**
     * Любая строка, кроме 11 или 111
     */
    public static boolean isNotThreeOrTwoOnes(String string) {
        return string.matches("(?!111$|11$)[01]*");
    }

    /**
     * Каждый нечетный символ равен 1
     */
    public static boolean isEveryOddSymbolIsOne(String string) {
        return string.matches("(1[01])*1?");
    }

    /**
     * Содержит не менее двух 0 и не более одной 1
     */
    public static boolean isMoreTwoZerosAndLessOneOnes(String string) {
        return string.matches("(1?0{2,}|0+1?0+|0{2,}1?)");
    }

    /**
     * Нет последовательных 1
     */
    public static boolean isNotContainSerialOnes(String string) {
        return string.matches("1|(1?01?)*");
    }

}
