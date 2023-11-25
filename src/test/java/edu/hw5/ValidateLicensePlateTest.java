package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidateLicensePlateTest {

    static List<Arguments> licensePlates() {
        return List.of(
            Arguments.of("А123ВЕ777", true),
            Arguments.of("О777ОО177", true),
            Arguments.of("123АВЕ777", false),
            Arguments.of("А123ВГ77", false),
            Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @MethodSource("licensePlates")
    void listLicensePlatesTest(String input, Boolean expected) {
        var result = HomeWork5.validateLicensePlate(input);
        assertThat(result).isEqualTo(expected);
    }
}
