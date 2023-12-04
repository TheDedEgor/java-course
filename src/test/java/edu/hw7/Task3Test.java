package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    static List<Person> getPersons() {
        var list = new ArrayList<Person>();
        list.add(new Person(1, "Petya", "Moscow", "+79990001111"));
        list.add(new Person(2, "Kirill", "Tomsk", "+78003332211"));
        list.add(new Person(3, "Dima", null, "+71235550326"));
        return list;
    }

    @Test
    @DisplayName("Поиск по имени")
    void findByNameTest() {
        var task3 = new Task3();
        var persons = getPersons();
        for (var person: persons) {
            task3.add(person);
        }
        assertThat(task3.findByName("Kirill")).isEqualTo(List.of(persons.get(1)));
    }

    @Test
    @DisplayName("Поиск по телефону, где у человека не указан адрес")
    void findByNotExistArgumentTest() {
        var task3 = new Task3();
        var persons = getPersons();
        for (var person: persons) {
            task3.add(person);
        }
        assertThat(task3.findByPhone("+71235550326")).isEqualTo(List.of());
    }

    @Test
    @DisplayName("Поиск по адресу")
    void findByAddressTest() {
        var task3 = new Task3();
        var persons = getPersons();
        for (var person: persons) {
            task3.add(person);
        }
        assertThat(task3.findByAddress("Moscow")).isEqualTo(List.of(persons.get(0)));
    }
}
