package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Количество итераций в каждом потоке 10")
    void test1() {
        var task1 = new Task1(10);
        assertThat(task1.getCount()).isEqualTo(20);
    }

    @Test
    @DisplayName("Количество итераций в каждом потоке 50")
    void test2() {
        var task1 = new Task1(50);
        assertThat(task1.getCount()).isEqualTo(100);
    }
}
