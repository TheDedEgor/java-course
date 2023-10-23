package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClusterizeTest {

    @Test
    @DisplayName("3 простых кластера")
    void Test1() {
        var result = Task2.clusterize("()()()");
        var expected = Arrays.asList("()", "()", "()");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("1 кластер")
    void Test2() {
        var result = Task2.clusterize("((()))");
        var expected = Arrays.asList("((()))");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("5 кластеров")
    void Test3() {
        var result = Task2.clusterize("((()))(())()()(()())");
        var expected = Arrays.asList("((()))", "(())", "()", "()", "(()())");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("2 больших кластера кластера")
    void Test4() {
        var result = Task2.clusterize("((())())(()(()()))");
        var expected = Arrays.asList("((())())", "(()(()()))");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Кластеры с различными символами")
    void Test5() {
        var result = Task2.clusterize("(Hello world!)(Hi)(Test)");
        var expected = Arrays.asList("(Hello world!)", "(Hi)", "(Test)");
        assertThat(result).isEqualTo(expected);
    }
}
