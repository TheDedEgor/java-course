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
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    @DisplayName("Проверка подстановки Лисков")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20).setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }
    @Test
    @DisplayName("Проверка квадрата")
    void squareTest() {
        Rectangle rect = new Square();
        rect = rect.setWidth(10).setHeight(10);

        assertThat(rect.area()).isEqualTo(100.0);
    }
}
