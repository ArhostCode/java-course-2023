package edu.project1;

import edu.project1.config.GameConfiguration;
import edu.project1.config.InMemoryGameConfiguration;
import edu.project1.game.ConsoleGameLoop;
import edu.project1.game.GameLoop;

public class HangmanGame {

    private final GameLoop gameLoop;

    public HangmanGame() {
        GameConfiguration configuration = new InMemoryGameConfiguration();
        gameLoop = new ConsoleGameLoop(configuration);
    }

    public void run() {
        gameLoop.prepare();
        gameLoop.run();
    }

    // Main method for running console game
    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        new HangmanGame().run();
    }

}
