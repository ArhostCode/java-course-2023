package edu.project1.result;

import edu.project1.game.GuessingWord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public sealed interface GuessResult {
    @Nullable GuessingWord state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record Win(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You won";
        }
    }

    record SuccessfulGuess(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "Hit!";
        }
    }

    record FailedGuess(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return String.format("Missed, mistake %d out of %d.", attempt() + 1, maxAttempts());
        }
    }

    record IncorrectInputGuess(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "You have entered the wrong letter or several letters at a time. Please re-enter.";
        }
    }

    record AlreadyOpenedGuess(GuessingWord state, int attempt, int maxAttempts) implements GuessResult {
        @Override
        public @NotNull String message() {
            return "The letter is already open.";
        }
    }
}
