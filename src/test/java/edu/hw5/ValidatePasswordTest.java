package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidatePasswordTest {

    static List<Arguments> passwords() {
        return List.of(
            Arguments.of("", false),
            Arguments.of(" ", false),
            Arguments.of("~", true),
            Arguments.of("123pass@123", true),
            Arguments.of("asas!353*", true)
        );
    }

    @ParameterizedTest
    @MethodSource("passwords")
    void listPasswordsTest(String input, Boolean expected) {
        var result = HomeWork5.validatePassword(input);
        assertThat(result).isEqualTo(expected);
    }
}
