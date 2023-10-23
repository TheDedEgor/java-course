package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParseContactsTest {

    @Test
    @DisplayName("Сортировка по возрастанию 1")
    void Test1() {
        var result = Task5.parseContacts(List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"), "ASC");
        var expected = List.of("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по убыванию 1")
    void Test2() {
        var result = Task5.parseContacts(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"), "DESC");
        var expected = List.of("Carl Gauss", "Leonhard Euler", "Paul Erdos");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой массив")
    void Test3() {
        var result = Task5.parseContacts(List.of(), "DESC");
        var expected = List.of();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Нет массива")
    void Test4() {
        var result = Task5.parseContacts(null, "DESC");
        var expected = List.of();
        assertThat(result).isEqualTo(expected);
    }
}
