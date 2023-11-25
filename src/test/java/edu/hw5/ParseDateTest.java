package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParseDateTest {
    static List<Arguments> dates() {
        return List.of(
            Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020,10,10))),
            Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020,12,2))),
            Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976,3,1))),
            Arguments.of("1/3/20", Optional.of(LocalDate.of(2020,3,1))),
            Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
            Arguments.of("today", Optional.of(LocalDate.now())),
            Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
            Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234))),
            Arguments.of("1|1|19", Optional.empty()),
            Arguments.of("10 day", Optional.empty())
        );
    }

    @ParameterizedTest
    @MethodSource("dates")
    void listDatesTest(String date, Optional<LocalDate> expected) {
        var result = HomeWork5.parseDate(date);
        assertThat(result).isEqualTo(expected);
    }
}
