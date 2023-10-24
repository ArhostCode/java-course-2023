package edu.project2;

import edu.project2.lifecycle.ConsoleMazeTaskLifeCycle;

public final class MazeTask {

    private MazeTask() {
    }

    @SuppressWarnings("checkstyle:UncommentedMain")
    public static void main(String[] args) {
        new ConsoleMazeTaskLifeCycle().run();
    }
}
