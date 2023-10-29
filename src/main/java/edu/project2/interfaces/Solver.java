package edu.project2.interfaces;

import edu.project2.classes.Coordinate;
import edu.project2.classes.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) throws Exception;
}
