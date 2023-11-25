package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    static List<Arguments> strings1() {
        return List.of(
            Arguments.of("1",  true),
            Arguments.of("00",  false),
            Arguments.of("111",  true),
            Arguments.of("1100",  false),
            Arguments.of("110011",  false)
        );
    }

    static List<Arguments> strings2() {
        return List.of(
            Arguments.of("11",  true),
            Arguments.of("0",  true),
            Arguments.of("111",  false),
            Arguments.of("0111",  false),
            Arguments.of("110010",  true),
            Arguments.of("01001",  true)
        );
    }


    static List<Arguments> strings3() {
        return List.of(
            Arguments.of("00",  false),
            Arguments.of("000",  true),
            Arguments.of("101010",  true),
            Arguments.of("100101000",  true),
            Arguments.of("11001001",  false)
        );
    }

    static List<Arguments> strings4() {
        return List.of(
            Arguments.of("",  true),
            Arguments.of("11",  false),
            Arguments.of("111",  false),
            Arguments.of("110",  true),
            Arguments.of("10",  true),
            Arguments.of("1",  true)
        );
    }

    @ParameterizedTest
    @MethodSource("strings1")
    void listStringsTask81Test(String input,  Boolean expected) {
        var result = HomeWork5.task81(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("strings2")
    void listStringsTask82Test(String input,  Boolean expected) {
        var result = HomeWork5.task82(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("strings3")
    void listStringsTask83Test(String input,  Boolean expected) {
        var result = HomeWork5.task83(input);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("strings4")
    void listStringsTask84Test(String input,  Boolean expected) {
        var result = HomeWork5.task84(input);
        assertThat(result).isEqualTo(expected);
    }
}
