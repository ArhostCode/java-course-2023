package edu.project1.game;

import edu.project1.result.GuessResult;
import org.jetbrains.annotations.NotNull;

public class Session {

    private final int maxAttempts;
    private int attempts;
    private final GuessingWord guessingWord;

    public Session(String currentWord, int attempts, int maxAttempts) {
        this.guessingWord = new GuessingWord(currentWord);
        this.attempts = attempts;
        this.maxAttempts = maxAttempts;
    }

    @NotNull public GuessResult guess(String guess) {
        if (!isCorrectGuess(guess)) {
            return new GuessResult.IncorrectInputGuess(guessingWord, attempts, maxAttempts);
        }
        char guessLetter = guess.charAt(0);
        GuessResult result = tryOpenCorrectLetter(guessLetter);

        if (guessingWord.isUserWordOpened()) {
            result = winGuess();
        } else {
            if (attempts >= maxAttempts) {
                result = defeatGuess();
            }
        }
        return result;
    }

    private GuessResult tryOpenCorrectLetter(char guess) {
        GuessResult result;

        if (guessingWord.containsActualWord(guess)) {
            if (!guessingWord.containsUserWord(guess)) {
                guessingWord.openLetter(guess);
                result = successfulGuess();
            } else {
                result = alreadyOpenedGuess();
            }
        } else {
            result = failGuess();
            attempts++;
        }
        return result;
    }

    private boolean isCorrectGuess(String guess) {
        return guess.length() == 1 && Character.isLetter(guess.charAt(0));
    }

    @NotNull public GuessResult giveUp() {
        return defeatGuess();
    }

    private GuessResult successfulGuess() {
        return new GuessResult.SuccessfulGuess(guessingWord, attempts, maxAttempts);
    }

    private GuessResult winGuess() {
        return new GuessResult.Win(guessingWord, attempts, maxAttempts);
    }

    private GuessResult failGuess() {
        return new GuessResult.FailedGuess(guessingWord, attempts, maxAttempts);
    }

    private GuessResult alreadyOpenedGuess() {
        return new GuessResult.AlreadyOpenedGuess(guessingWord, attempts, maxAttempts);
    }

    private GuessResult defeatGuess() {
        return new GuessResult.Defeat(guessingWord, attempts, maxAttempts);
    }

}
