package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsPalindromeDescendantTest {

    @Test
    @DisplayName("3 потомок числа является палиндромом")
    void validTest1() {
        var result = Main.isPalindromeDescendant(11211230);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("2 потомок числа является палиндромом")
    void validTest2() {
        var result = Main.isPalindromeDescendant(13001120);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("1 потомок числа является палиндромом")
    void validTest3() {
        var result = Main.isPalindromeDescendant(23336014);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Изначальное число является палиндромом")
    void validTest4() {
        var result = Main.isPalindromeDescendant(11);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Число не является палиндромом")
    void invalidTest1() {
        var result = Main.isPalindromeDescendant(23);
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("2 потомок не является палиндромом")
    void invalidTest2() {
        var result = Main.isPalindromeDescendant(2344);
        assertThat(result).isEqualTo(false);
    }
}
