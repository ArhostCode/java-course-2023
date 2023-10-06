package edu.hw1.task8;

import java.util.Objects;

public final class HorsesOnBoard {

    private HorsesOnBoard() {
    }

    private static final int BOARD_SIZE = 8;
    private static final int LONG_STEP = 2;
    private static final int SHORT_STEP = 1;

    public static boolean isSafeSituation(int[][] board) {
        Objects.requireNonNull(board);
        if (board.length != BOARD_SIZE) {
            throw new IllegalArgumentException("Wrong board width");
        }
        for (int[] ints : board) {
            if (ints.length != BOARD_SIZE) {
                throw new IllegalArgumentException("Wrong board length");
            }
        }

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (board[y][x] == 1 && canKill(x, y, board)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean canKill(int x, int y, int[][] board) {
        return inBoundAndOne(x + LONG_STEP, y + SHORT_STEP, board)
            || inBoundAndOne(x + LONG_STEP, y - SHORT_STEP, board)
            || inBoundAndOne(x - LONG_STEP, y + SHORT_STEP, board)
            || inBoundAndOne(x - LONG_STEP, y - SHORT_STEP, board)

            || inBoundAndOne(x + SHORT_STEP, y - LONG_STEP, board)
            || inBoundAndOne(x + SHORT_STEP, y + LONG_STEP, board)
            || inBoundAndOne(x - SHORT_STEP, y - LONG_STEP, board)
            || inBoundAndOne(x - SHORT_STEP, y + LONG_STEP, board);
    }

    private static boolean inBoundAndOne(int x, int y, int[][] board) {
        return x >= 0 && y >= 0 && x < BOARD_SIZE && y < BOARD_SIZE && board[y][x] == 1;
    }

}
