package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountKTest {

    @Test
    @DisplayName("Проверка для числа 3524")
    void validTest1() {
        var result = Main.countK(3524, 0);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка для числа 6621")
    void validTest2() {
        var result = Main.countK(6621, 0);
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Проверка для числа 6554")
    void validTest3() {
        var result = Main.countK(6554, 0);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Проверка для числа 1234")
    void validTest4() {
        var result = Main.countK(1234, 0);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка для числа 6174 ")
    void validTest5() {
        var result = Main.countK(6174, 0);
        assertThat(result).isEqualTo(0);
    }
}
