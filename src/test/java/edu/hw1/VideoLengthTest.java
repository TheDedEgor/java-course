package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VideoLengthTest {

    @Test
    @DisplayName("Верный подсчет секунд 1")
    void validTest1() {
        var result = Main.minutesToSeconds("01:00");
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("Верный подсчет секунд 2")
    void validTest2() {
        var result = Main.minutesToSeconds("13:56");
        assertThat(result).isEqualTo(836);
    }

    @Test
    @DisplayName("Нулевая длина видео")
    void zeroNumbersTest() {
        var result = Main.minutesToSeconds("00:00");
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Неверное количество секунд")
    void invalidTest1() {
        var result = Main.minutesToSeconds("10:60");
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательные минуты")
    void negativeNumbersTest1() {
        var result = Main.minutesToSeconds("-10:03");
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательные секунды")
    void negativeNumbesrTest2() {
        var result = Main.minutesToSeconds("9:-05");
        assertThat(result).isEqualTo(-1);
    }
}
