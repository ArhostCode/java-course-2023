package edu.project1.game;

import edu.project1.config.GameConfiguration;
import edu.project1.result.GuessResult;
import edu.project1.source.WordSource;

public abstract class GameLoop {

    protected Session session;
    protected GameConfiguration configuration;
    protected GameStatus status = GameStatus.STOPPED;

    public GameLoop(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    public void prepare() {
        if (configuration == null || configuration.getWordSource() == null) {
            throw new IllegalStateException("Illegal configuration");
        }
        WordSource source = configuration.getWordSource();
        String randomWord = source.randomWord();
        int attemptsCount = configuration.getMaxAttemptsCount();
        checkSessionDataIsCorrect(randomWord, attemptsCount);
        session = new Session(randomWord, 0, configuration.getMaxAttemptsCount());
    }

    private void checkSessionDataIsCorrect(String word, int maxAttemptsCount) {
        if (word == null || word.isBlank() || !containsOnlyLetters(word)) {
            throw new IllegalStateException("Wrong word from WordSource");
        }
        if (maxAttemptsCount <= 0) {
            throw new IllegalStateException("Wrong max attempts count");
        }
    }

    private boolean containsOnlyLetters(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void run() {
        status = GameStatus.RUNNING;
        processGameLoop();
    }

    private void processGameLoop() {
        print("Guess the letter");
        while (status == GameStatus.RUNNING) {
            InputData inputData = processInput();
            GuessResult result;
            if (inputData.isInputStopped()) {
                result = session.giveUp();
            } else {
                result = session.guess(inputData.getInput());
            }
            printGuessResult(result);
            if (result instanceof GuessResult.Defeat || result instanceof GuessResult.Win) {
                stop();
            }
        }
    }

    private void printGuessResult(GuessResult guessResult) {
        print(guessResult.message());
        if (guessResult instanceof GuessResult.Defeat) {
            print(String.format("Actual word: %s", guessResult.state().getActualWord()));
        } else {
            print(String.format("The word: %s", guessResult.state()));
        }
    }

    public void stop() {
        status = GameStatus.STOPPED;
    }

    public abstract InputData processInput();

    public abstract void print(String output);
}
