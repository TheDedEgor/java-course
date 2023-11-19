package edu.project2;

import edu.project2.classes.Cell;
import edu.project2.classes.Coordinate;
import edu.project2.classes.Maze;
import edu.project2.interfaces.Renderer;
import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class RenderMage implements Renderer {
    @Override
    public String render(Maze maze) {
        var cells = maze.getGrid();
        var result = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                var cell = getCellToString(cells[i][j]);
                result.append(cell);
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        int idx = 0;
        var cells = maze.getGrid();
        var result = new StringBuilder();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (path.contains(new Coordinate(i, j))) {
                    result.append(getPartPathToString());
                    idx++;
                } else {
                    result.append(getCellToString(cells[i][j]));
                }
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String getPartPathToString() {
        return Colors.GREEN.value + "*" + Colors.RESET.value + " ";
    }

    private String getCellToString(Cell cell) {
        var result = "";
        if (cell.type() == Cell.Type.WALL) {
            result += "■";
        } else {
            result += " ";
        }
        result += " ";
        return result;
    }
}
