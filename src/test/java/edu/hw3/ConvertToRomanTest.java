package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConvertToRomanTest {

    static List<Arguments> numbers() {
        return List.of(
            Arguments.of(2, "II"),
            Arguments.of(12, "XII"),
            Arguments.of(16, "XVI"),
            Arguments.of(51, "LI"),
            Arguments.of(237, "CCXXXVII")
        );
    }

    @ParameterizedTest
    @MethodSource("numbers")
    void variousNumbersTest(Integer number, String expected) {
        var result = Task4.convertToRoman(number);
        assertThat(result).isEqualTo(expected);
    }
}
