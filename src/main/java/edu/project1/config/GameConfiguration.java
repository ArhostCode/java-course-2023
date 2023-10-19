package edu.project1.config;

import edu.project1.source.WordSource;

public interface GameConfiguration {

    int getMaxAttemptsCount();

    WordSource getWordSource();

}
