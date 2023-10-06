package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsNestableTest {

    @Test
    @DisplayName("Массив вкладывается 1")
    void validTest1() {
        var result = Main.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6});
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Массив вкладывается 2")
    void validTest2() {
        var result = Main.isNestable(new int[] {3, 1}, new int[] {4, 0});
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Массив не вкладывается 1")
    void invalidTest1() {
        var result = Main.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Массив не вкладывается 2")
    void invalidTest2() {
        var result = Main.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3});
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Первый массив из 1 элемента")
    void oneElementTest1() {
        var result = Main.isNestable(new int[] {1}, new int[] {0, 2});
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Второй массив из 1 элемента")
    void oneElementTest2() {
        var result = Main.isNestable(new int[] {1, 2}, new int[] {3});
        assertThat(result).isEqualTo(false);
    }
}
