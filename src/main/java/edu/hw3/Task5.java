package edu.hw3;

import java.util.Comparator;
import java.util.List;

public class Task5 {

    private Task5() {
    }

    public static List<Person> parseContacts(List<String> list, SortDirection sortDirection) {
        if (list == null) {
            return List.of();
        }
        Comparator<Person> comparator = new ContactsComparator();
        if (sortDirection.equals(SortDirection.DESC)) {
            comparator = comparator.reversed();
        }
        return list.stream().map(Person::new).sorted(comparator).toList();
    }
}
