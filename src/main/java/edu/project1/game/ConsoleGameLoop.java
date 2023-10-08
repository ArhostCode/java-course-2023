package edu.project1.game;

import edu.project1.config.GameConfiguration;
import java.util.Scanner;

public class ConsoleGameLoop extends GameLoop {

    private final Scanner input;

    public ConsoleGameLoop(GameConfiguration configuration) {
        super(configuration);
        input = new Scanner(System.in);
    }

    @Override
    public InputData processInput() {
        if (!input.hasNext()) {
            return InputData.closed();
        }
        return InputData.input(input.nextLine());
    }

    // System.out is used for output, not logging. Using a logger here seems unreasoning.
    @SuppressWarnings("checkstyle:RegexpSinglelineJava") @Override
    public void print(String output) {
        System.out.println(output);
    }

}
