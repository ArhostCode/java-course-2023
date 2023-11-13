package edu.project2.lifecycle;

import edu.project2.output.ConsoleUserWriter;
import edu.project2.reader.ConsoleUserReader;

public class ConsoleMazeTaskLifeCycle extends MazeTaskLifeCycle {
    public ConsoleMazeTaskLifeCycle() {
        super(new ConsoleUserReader(), new ConsoleUserWriter());
    }
}
