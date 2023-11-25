package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FindFriday13Test {

    static List<Arguments> years() {
        return List.of(
            Arguments.of(1925, List.of("1925-02-13", "1925-03-13", "1925-11-13")),
            Arguments.of(2024, List.of("2024-09-13", "2024-12-13")),
            Arguments.of(2003, List.of("2003-06-13"))
        );
    }

    static List<Arguments> dates() {
        return List.of(
            Arguments.of(LocalDate.of(2024,9,15), "2024-12-13"),
            Arguments.of(LocalDate.of(2003,10,1), "2004-02-13")
        );
    }

    @ParameterizedTest
    @MethodSource("years")
    void listYearsTest(Integer year, List<String> expected) {
        var result = HomeWork5.findAllFriday13(year);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("dates")
    void listDatesTest(LocalDate date, String expected) {
        var result = HomeWork5.nextFriday13(date);
        assertThat(result).isEqualTo(expected);
    }
}
