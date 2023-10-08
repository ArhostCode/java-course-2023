package edu.project1.config;

import edu.project1.source.InMemoryWordSource;
import edu.project1.source.WordSource;

public class InMemoryGameConfiguration implements GameConfiguration {

    private static final int DEFAULT_ATTEMPTS_COUNT = 5;
    private final int maxAttemptsCount;
    private final WordSource wordSource;

    public InMemoryGameConfiguration() {
        maxAttemptsCount = DEFAULT_ATTEMPTS_COUNT;
        wordSource = new InMemoryWordSource();
    }

    public InMemoryGameConfiguration(int maxAttemptsCount) {
        this.maxAttemptsCount = maxAttemptsCount;
        wordSource = new InMemoryWordSource();
    }

    @Override
    public int getMaxAttemptsCount() {
        return maxAttemptsCount;
    }

    @Override
    public WordSource getWordSource() {
        return wordSource;
    }
}
