package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CircularShiftTest {

    @Test
    @DisplayName("Правый сдвиг числа 8 на 1")
    void validTest1() {
        var result = Main.rotateRight(8, 1);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Правый сдвиг числа 8 на 6")
    void validTest2() {
        var result = Main.rotateRight(8, 6);
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Левый сдвиг числа 16 на 1")
    void validTest3() {
        var result = Main.rotateLeft(16, 1);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Левый сдвиг числа 17 на 2")
    void validTest4() {
        var result = Main.rotateLeft(17, 2);
        assertThat(result).isEqualTo(6);
    }
}
