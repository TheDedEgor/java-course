package edu.project2;

import edu.project2.classes.Cell;
import edu.project2.classes.Coordinate;
import edu.project2.classes.Maze;
import edu.project2.interfaces.Generator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktracker implements Generator {
    @Override
    public Maze generate(int height, int width) throws Exception {
        if (height < 2 || width < 2) {
            throw new BadSizeMazeException("Неверные размеры лабиринта");
        }
        var maze = createMaze(height, width);
        var visited = new boolean[height][width];
        var stack = new Stack<Coordinate>();
        var startCell = new Coordinate(1, 1);
        stack.push(startCell);
        visited[1][1] = true;
        var rand = new Random();
        while (!stack.empty()) {
            var currentCoordinate = stack.pop();
            var neighbors = getNeighbors(maze, visited, currentCoordinate);
            if (!neighbors.isEmpty()) {
                stack.push(currentCoordinate);
                var idx = rand.nextInt(neighbors.size());
                var randomNeighbor = neighbors.get(idx);
                deleteWall(maze, visited, currentCoordinate, randomNeighbor);
                visited[randomNeighbor.row()][randomNeighbor.col()] = true;
                stack.push(randomNeighbor);
            }
        }

        return maze;
    }

    private Maze createMaze(int height, int width) {
        var cells = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0) && (i < height - 1 && j < width - 1)) {
                    cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    cells[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }
        return new Maze(height, width, cells);
    }

    private List<Coordinate> getNeighbors(Maze maze, boolean[][] visited, Coordinate currentCoordinate) {
        var cells = maze.getGrid();
        List<Coordinate> neighbors = new ArrayList<>();
        int i = currentCoordinate.row();
        int j = currentCoordinate.col();
        addNeighbor(neighbors, cells, visited, i - 2, j);
        addNeighbor(neighbors, cells, visited, i, j - 2);
        addNeighbor(neighbors, cells, visited, i, j + 2);
        addNeighbor(neighbors, cells, visited, i + 2, j);
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

    private void deleteWall(Maze maze, boolean[][] visited, Coordinate current, Coordinate selected) {
        var cells = maze.getGrid();
        int i;
        int j;
        if (current.row() == selected.row()) {
            i = current.row();
            j = Math.max(current.col(), selected.col()) - 1;
        } else {
            i = Math.max(current.row(), selected.row()) - 1;
            j = current.col();
        }
        cells[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
        visited[i][j] = true;
    }
}

