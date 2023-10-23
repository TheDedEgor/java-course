package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConvertToRomanTest {

    @Test
    @DisplayName("Конвертация числа 2")
    void Test1() {
        var result = Task4.convertToRoman(2);
        assertThat(result).isEqualTo("II");
    }

    @Test
    @DisplayName("Конвертация числа 12")
    void Test2() {
        var result = Task4.convertToRoman(12);
        assertThat(result).isEqualTo("XII");
    }

    @Test
    @DisplayName("Конвертация числа 16")
    void Test3() {
        var result = Task4.convertToRoman(16);
        assertThat(result).isEqualTo("XVI");
    }


    @Test
    @DisplayName("Конвертация числа 51")
    void Test4() {
        var result = Task4.convertToRoman(51);
        assertThat(result).isEqualTo("LI");
    }

    @Test
    @DisplayName("Конвертация числа 237")
    void Test5() {
        var result = Task4.convertToRoman(237);
        assertThat(result).isEqualTo("CCXXXVII");
    }
}
