package edu.hw8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {

    @Test
    @DisplayName("Вычисление первых 10 чисел Фибоначи")
    void calculateFibonacciTest() throws Exception {
        var results = new int[] {0, 1, 1, 2, 3, 5, 8, 13, 21, 34 };
        try (ThreadPool threadPool = FixedThreadPool.create(3)) {
            threadPool.start();
            for (int i = 0; i < 10; i++) {
                var val = i;
                threadPool.execute(() -> {
                    long result = calculateFibonacci(val);
                    assertThat(result).isEqualTo(results[val]);
                });
            }
        }
    }

    private static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}
