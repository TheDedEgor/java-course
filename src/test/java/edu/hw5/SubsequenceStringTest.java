package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SubsequenceStringTest {

    static List<Arguments> strings() {
        return List.of(
            Arguments.of("abc", "achfdbaabgabcaabg", true),
            Arguments.of("", "achfdbaabgabcaabg", true),
            Arguments.of("abch", "achfdbaabgabcaabg", false),
            Arguments.of(" ", "achfdbaabgabcaabg", false)
        );
    }

    @ParameterizedTest
    @MethodSource("strings")
    void listStringsTest(String substr, String input, Boolean expected) {
        var result = HomeWork5.subsequenceString(substr, input);
        assertThat(result).isEqualTo(expected);
    }
}
