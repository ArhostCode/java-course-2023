package edu.project2.generate;

import edu.project2.model.Maze;

public interface Generator {
    Maze generate(int width, int height);
}
