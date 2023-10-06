package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountDigitsTest {

    @Test
    @DisplayName("Положительное число 1")
    void positiveNumberTest1() {
        var result = Main.countDigits(4666);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Положительное число 2")
    void positiveNumberTest2() {
        var result = Main.countDigits(544);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Проверка для 0")
    void zeroNumberTest() {
        var result = Main.countDigits(0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное число 1")
    void negativeNumberTest1() {
        var result = Main.countDigits(-23);
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("Отрицательное число 2")
    void negativeNumberTest2() {
        var result = Main.countDigits(-5611212);
        assertThat(result).isEqualTo(7);
    }
}
