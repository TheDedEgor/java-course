package edu.hw2;

import edu.hw2.task2.Rectangle;
import edu.hw2.task2.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SquareAndRectangleTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle(20, 10)),
            Arguments.of(new Square(10))
        };
    }

    @Test
    @DisplayName("Проверка прямоугольника")
    void rectangleTest() {
        var rectangle = new Rectangle(20, 10);
        var result = rectangle.area();
        assertThat(result).isEqualTo(200);
    }

    @Test
    @DisplayName("Проверка квадрата")
    void squareTest() {
        var rectangle = new Square(10);
        var result = rectangle.area();
        assertThat(result).isEqualTo(100);
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Проверка прямоугольника и квадрата")
    void rectangleArea(Rectangle rect) {
        var expected = rect.getHeight() * rect.getWidth();
        var result = rect.area();
        assertThat(result).isEqualTo(expected);
    }
}
