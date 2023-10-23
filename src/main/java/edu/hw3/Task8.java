package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Task8<T> implements Iterator<T> {

    private ListIterator<T> iterator;

    public Task8(List<T> list) {
        iterator = list.listIterator(list.size());
    }

    @Override
    public boolean hasNext() {
        return iterator.hasPrevious();
    }

    @Override
    public T next() {
        return iterator.previous();
    }
}
