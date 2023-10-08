package edu.project1.game;

import java.util.Arrays;

public class GuessingWord {

    private static final char NOT_GUESSED_LETTER = '*';

    private final String actualWord;
    private char[] userWord;

    public GuessingWord(String actualWord) {
        this.actualWord = actualWord;
        initializeUserWord();
    }

    public String getActualWord() {
        return actualWord;
    }

    private void initializeUserWord() {
        userWord = new char[actualWord.length()];
        Arrays.fill(userWord, NOT_GUESSED_LETTER);
    }

    public boolean containsActualWord(char guess) {
        return contains(guess, actualWord.toCharArray());
    }

    public boolean containsUserWord(char guess) {
        return contains(guess, userWord);
    }

    private boolean contains(char finding, char[] array) {
        for (char c : array) {
            if (c == finding) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserWordOpened() {
        return !containsUserWord(NOT_GUESSED_LETTER);
    }

    public void openLetter(char guess) {
        char[] actualCharArray = actualWord.toCharArray();
        for (int i = 0; i < actualCharArray.length; i++) {
            if (actualCharArray[i] == guess) {
                userWord[i] = guess;
            }
        }
    }

    @Override
    public String toString() {
        return String.copyValueOf(userWord);
    }
}
