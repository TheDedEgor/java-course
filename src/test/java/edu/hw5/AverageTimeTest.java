package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AverageTimeTest {

    static List<Arguments> times() {
        return List.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"), "3ч 40м"),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-03-01, 11:00 - 2022-04-02, 01:20"), "380ч 55м"),
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 20:20", "2022-03-12, 20:20 - 2022-03-12, 20:20"), "0ч 0м")
        );
    }

    @ParameterizedTest
    @MethodSource("times")
    void listTimesTest(List<String> input, String expected) {
        var result = HomeWork5.averageTime(input);
        assertThat(result).isEqualTo(expected);
    }
}
