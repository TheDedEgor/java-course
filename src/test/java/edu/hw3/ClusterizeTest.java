package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClusterizeTest {
    static List<Arguments> clusters() {
        return List.of(
            Arguments.of("()()()", List.of("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))")),
            Arguments.of("(Hello world!)(Hi)(Test)", List.of("(Hello world!)", "(Hi)", "(Test)"))
        );
    }

    @ParameterizedTest
    @MethodSource("clusters")
    void variousClustersTest(String input, List<String> expected) {
        var result = Task2.clusterize(input);
        assertThat(result).isEqualTo(expected);
    }
}
