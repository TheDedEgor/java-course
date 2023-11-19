package edu.project2.interfaces;

import edu.project2.classes.Coordinate;
import edu.project2.classes.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);
}
