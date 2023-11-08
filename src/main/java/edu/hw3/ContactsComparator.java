package edu.hw3;

import java.util.Comparator;

public class ContactsComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        if (!p1.getSurname().isEmpty() && !p2.getSurname().isEmpty()) {
            return p1.getSurname().compareTo(p2.getSurname());
        }
        return p1.getName().compareTo(p2.getName());
    }
}
