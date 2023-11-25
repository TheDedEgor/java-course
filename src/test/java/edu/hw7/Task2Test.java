package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Факториал 5")
    void test1() {
        var result = Task2.calculateFactorial(5);
        assertThat(result).isEqualTo(120);
    }

    @Test
    @DisplayName("Факториал 10")
    void test2() {
        var result = Task2.calculateFactorial(10);
        assertThat(result).isEqualTo(3628800);
    }

    @Test
    @DisplayName("Факториал 15")
    void test3() {
        var result = Task2.calculateFactorial(15);
        assertThat(result).isEqualTo(1307674368000L);
    }
}
