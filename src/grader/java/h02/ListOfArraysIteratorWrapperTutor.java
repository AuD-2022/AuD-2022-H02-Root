package h02;

import h02.ListOfArrays;
import h02.ListOfArraysIterator;

import java.util.Iterator;

public class ListOfArraysIteratorWrapperTutor<T> implements Iterator<T> {

    private final ListOfArraysIterator<T> iterator;

    public ListOfArraysIteratorWrapperTutor(ListOfArrays<T> listOfArrays) {
        iterator = listOfArrays.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        return iterator.next();
    }
}
