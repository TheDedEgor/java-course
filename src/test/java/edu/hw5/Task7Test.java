package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    static List<Arguments> strings1() {
        return List.of(
            Arguments.of("abc",  false),
            Arguments.of("00",  false),
            Arguments.of("111",  false),
            Arguments.of("110",  true),
            Arguments.of("110011",  true)
        );
    }

    static List<Arguments> strings2() {
        return List.of(
            Arguments.of("1",  true),
            Arguments.of("00",  true),
            Arguments.of("111",  true),
            Arguments.of("110",  false),
            Arguments.of("110010",  false)
        );
    }


    static List<Arguments> strings3() {
        return List.of(
            Arguments.of("",  false),
            Arguments.of("0",  true),
            Arguments.of("11",  true),
            Arguments.of("110",  true),
            Arguments.of("110011",  false)
        );
    }

    @ParameterizedTest
    @MethodSource("strings1")
    void listStringsTask71Test(String input,  Boolean expected) {
        var result = HomeWork5.task71(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("strings2")
    void listStringsTask72Test(String input,  Boolean expected) {
        var result = HomeWork5.task72(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("strings3")
    void listStringsTask73Test(String input,  Boolean expected) {
        var result = HomeWork5.task73(input);
        assertThat(result).isEqualTo(expected);
    }
}
