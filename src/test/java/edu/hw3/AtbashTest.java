package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AtbashTest {

    static List<Arguments> strings() {
        return List.of(
            Arguments.of("ABC", "ZYX"),
            Arguments.of("abc", "zyx"),
            Arguments.of("AbC", "ZyX"),
            Arguments.of("Hello world!", "Svool dliow!"),
            Arguments.of("Hello мир", "Svool мир"),
            Arguments.of(
                "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler",
                "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
            )
        );
    }

    @ParameterizedTest
    @MethodSource("strings")
    void variousStringsTest(String input, String expected) {
        var result = Task1.atbash(input);
        assertThat(result).isEqualTo(expected);
    }
}
