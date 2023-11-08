package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FreqDictTest {

    static List<Arguments> dictionaries() {
        return List.of(
            Arguments.of(List.of("a", "bb", "a", "bb"), Map.of("a", 2L, "bb", 2L)),
            Arguments.of(List.of("this", "and", "that", "and"), Map.of("this", 1L,"and", 2L,"that", 1L)),
            Arguments.of(List.of("код", "код", "код", "bug"), Map.of("код", 3L, "bug", 1L)),
            Arguments.of(List.of(1L, 1L, 2L, 2L), Map.of(1L, 2L, 2L, 2L))
        );
    }

    @ParameterizedTest
    @MethodSource("dictionaries")
    void variousDictionariesTest(List<Object> input, Map<Object, Integer> expected) {
        var result = Task3.freqDict(input);
        assertThat(result).isEqualTo(expected);
    }
}
