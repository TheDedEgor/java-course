package edu.hw3;

import java.util.List;

public class Task5 {

    private Task5() {
    }

    public static List<Object> parseContacts(List<String> list, String sortDirection) {
        if (list == null) {
            return List.of();
        }
        var comparator = new ContactsComparator(sortDirection);
        return list.stream().sorted(comparator).map(item -> (Object) item).toList();
    }
}
