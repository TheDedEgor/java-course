package edu.project2;

import edu.project2.classes.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MazeTest {

    @Test
    @DisplayName("Проверка на размеры лабиринта")
    void Test1() {
        try {
            var algorithm = new RecursiveBacktracker();
            algorithm.generate(-1, -3);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(BadSizeMazeException.class);
        }
    }

    @Test
    @DisplayName("Находит путь в лабиринте")
    void Test2() throws Exception {
        var algorithm = new RecursiveBacktracker();
        var maze = algorithm.generate(9, 9);
        var start = new Coordinate(1, 1);
        var end = new Coordinate(7, 7);
        var resolver = new SearchDepth();
        var path = resolver.solve(maze, start, end);
        var result = path.get(path.size() - 1);
        assertThat(result).isEqualTo(end);
    }

    @Test
    @DisplayName("Нет пути в лабиринте")
    void Test3() throws Exception {
        var algorithm = new RecursiveBacktracker();
        var maze = algorithm.generate(9, 9);
        var start = new Coordinate(1, 1);
        var end = new Coordinate(8, 8);
        var resolver = new SearchDepth();
        try {
            resolver.solve(maze, start, end);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(NotFoundPathException.class);
        }
    }

    @Test
    @DisplayName("Ожидаемый вывод лабиринта")
    void Test4() throws Exception {
        var alg = new RecursiveBacktracker();
        var maze = alg.generate(3, 7);
        var renderMage = new RenderMage();
        var result = renderMage.render(maze);
        var expected = """
            ■ ■ ■ ■ ■ ■ ■\s
            ■           ■\s
            ■ ■ ■ ■ ■ ■ ■ \n""";
        assertThat(result).isEqualTo(expected);
    }
}
