package edu.project2.interfaces;

import edu.project2.classes.Maze;

public interface Generator {
    Maze generate(int height, int width) throws Exception;
}
