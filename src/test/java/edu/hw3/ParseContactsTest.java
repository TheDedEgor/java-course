package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParseContactsTest {

    @Test
    @DisplayName("Сортировка по возрастанию")
    void sortAscTest() {
        var result = Task5.parseContacts(
            List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
            SortDirection.ASC
        );
        var expected = List.of(
            new Person("Thomas Aquinas"),
            new Person("Rene Descartes"),
            new Person("David Hume"),
            new Person("John Locke")
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по убыванию")
    void sortDescTest() {
        var result = Task5.parseContacts(List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"), SortDirection.DESC);
        var expected = List.of(
            new Person("Carl Gauss"),
            new Person("Leonhard Euler"),
            new Person("Paul Erdos")
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой массив")
    void emptyArrayTest() {
        var result = Task5.parseContacts(List.of(), SortDirection.ASC);
        var expected = List.of();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Нет массива")
    void nullArrayTest() {
        var result = Task5.parseContacts(null, SortDirection.DESC);
        var expected = List.of();
        assertThat(result).isEqualTo(expected);
    }
}
