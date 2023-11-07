package edu.hw3;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task8<T> implements Iterator<T> {

    private int currentIndex;
    private final List<T> list;

    public Task8(List<T> list) {
        this.list = list;
        currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex > -1;
    }

    @Override
    public T next() {
        if (currentIndex < 0) {
            throw new NoSuchElementException();
        }
        var item = list.get(currentIndex);
        currentIndex--;
        return item;
    }
}
