package edu.hw3;

import java.util.Comparator;

public class ContactsComparator implements Comparator<String> {

    private final String sortDirection;

    public ContactsComparator(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public int compare(String o1, String o2) {
        var contact1 = o1.split(" ");
        var contact2 = o2.split(" ");
        int result;
        if (contact1.length > 1 && contact2.length > 1) {
            result = contact1[1].compareTo(contact2[1]);
        } else {
            result = contact1[0].compareTo(contact2[0]);
        }
        if (sortDirection.equals("DESC")) {
            result = -result;
        }
        return result;
    }
}
