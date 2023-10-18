package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {

    private final List<T> list;
    private int cursor;

    public BackwardIterator(Collection<T> collection) {
        this.list = new ArrayList<>(collection);
        cursor = collection.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return cursor >= 0;
    }

    @Override
    public T next() {
        T element = list.get(cursor);
        cursor--;
        return element;
    }
}
