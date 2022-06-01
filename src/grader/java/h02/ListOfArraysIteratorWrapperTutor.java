package h02;

import h02.ListOfArrays;
import h02.ListOfArraysIterator;

import java.util.Iterator;

import static h02.TestConstants.EXCEPTION_ON_EXECUTION;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfArraysIteratorWrapperTutor<T> implements Iterator<T> {

    private final ListOfArraysIterator<T> iterator;

    public ListOfArraysIteratorWrapperTutor(ListOfArrays<T> listOfArrays) {
        iterator = assertDoesNotThrow(() -> listOfArrays.iterator(), EXCEPTION_ON_EXECUTION);
    }

    @Override
    public boolean hasNext() {
        return assertDoesNotThrow(() -> iterator.hasNext(), EXCEPTION_ON_EXECUTION);
    }

    @Override
    public T next() {
        return assertDoesNotThrow(() -> iterator.next(), EXCEPTION_ON_EXECUTION);
    }
}
