package edu.hw1.task7;

public final class CircularShift {

    private CircularShift() {
    }

    public static int rotateLeft(int n, int shift) {
        if (shift < 0 || n < 0) {
            return -1;
        }
        int bitsCount = Integer.toBinaryString(n).length();
        int newShift = shift % bitsCount;
        return (n << newShift | n >> (bitsCount - newShift)) & ((1 << (bitsCount)) - 1);
    }

    public static int rotateRight(int n, int shift) {
        if (shift < 0 || n < 0) {
            return -1;
        }
        int bitsCount = Integer.toBinaryString(n).length();
        int newShift = shift % bitsCount;
        return n >> newShift | n << (bitsCount - newShift) & ((1 << bitsCount) - 1);
    }
}
