package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FixStringTest {

    @Test
    @DisplayName("Четная строка из цифр")
    void evenLineNumbersTest() {
        var result = Main.fixString("123456");
        assertThat(result).isEqualTo("214365");
    }

    @Test
    @DisplayName("Четная строка из букв")
    void evenLineLettersTest() {
        var result = Main.fixString("hTsii  s aimex dpus rtni.g");
        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Нечетная строка")
    void oddLineTest() {
        var result = Main.fixString("badce");
        assertThat(result).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Строка из 1 символа")
    void oneSymbolOfLineTest() {
        var result = Main.fixString("a");
        assertThat(result).isEqualTo("a");
    }
}
