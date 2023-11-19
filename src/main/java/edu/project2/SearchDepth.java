package edu.project2;

import edu.project2.classes.Cell;
import edu.project2.classes.Coordinate;
import edu.project2.classes.Maze;
import edu.project2.interfaces.Solver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class SearchDepth implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) throws Exception {
        var rand = new Random();
        var visited = new boolean[maze.getHeight()][maze.getWidth()];
        var stack = new Stack<Coordinate>();
        var currentCoordinate = start;
        visited[start.row()][start.col()] = true;
        List<Coordinate> path = new ArrayList<>();
        path.add(currentCoordinate);
        while (!currentCoordinate.equals(end)) {
            var neighbors = getNeighbors(maze, visited, currentCoordinate);
            if (!neighbors.isEmpty()) {
                stack.push(currentCoordinate);
                var idx = rand.nextInt(neighbors.size());
                var randomNeighbor = neighbors.get(idx);
                currentCoordinate = randomNeighbor;
                visited[currentCoordinate.row()][currentCoordinate.col()] = true;
                path.add(currentCoordinate);
            } else if (!stack.isEmpty()) {
                currentCoordinate = stack.pop();
            } else {
                throw new NotFoundPathException("Выхода в лабиринте нет");
            }
        }
        return path;
    }

    private List<Coordinate> getNeighbors(Maze maze, boolean[][] visited, Coordinate currentCoordinate) {
        var cells = maze.getGrid();
        List<Coordinate> neighbors = new ArrayList<>();
        int i = currentCoordinate.row();
        int j = currentCoordinate.col();
        addNeighbor(neighbors, cells, visited, i - 1, j);
        addNeighbor(neighbors, cells, visited, i, j - 1);
        addNeighbor(neighbors, cells, visited, i, j + 1);
        addNeighbor(neighbors, cells, visited, i + 1, j);
        return neighbors;
    }

    private void addNeighbor(List<Coordinate> neighbors, Cell[][] cells, boolean[][] visited, int i, int j) {
        try {
            if (!visited[i][j] && cells[i][j].type() == Cell.Type.PASSAGE) {
                neighbors.add(new Coordinate(i, j));
            }
        } catch (Exception ignored) {
        }
    }
}
