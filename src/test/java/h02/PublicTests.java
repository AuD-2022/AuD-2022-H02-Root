package h02;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PublicTests {

    @Test
    void testConstructor() {
        var list = new ListOfArrays<>(new Object[] {1, 2, 3, 4, 5});

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () ->
            new ListOfArraysIteratorWrapper<>(list));
    }

    @Test
    void testInsert() {
        var list = new ListOfArrays<>(new Object[] {1, 2, 5});

        list.insert(List.of(3, 4), 2);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () ->
            new ListOfArraysIteratorWrapper<>(list));
    }

    @Test
    void testWithIterator() {
        var list = new ListOfArrays<>(new Object[] {1, 3, 6});

        list.insert(List.of(
            new ElementWithIndex<Object>(2, 1),
            new ElementWithIndex<Object>(4, 1),
            new ElementWithIndex<Object>(5, 0)
        ).iterator());

        assertIterableEquals(List.of(1, 2, 3, 4, 5, 6), () ->
            new ListOfArraysIteratorWrapper<>(list));
    }

    @Test
    void testExtract() {
        var list = new ListOfArrays<>(new Object[] {1, 2, 3, 4, 5});
        var extract = list.extract(2, 3);

        assertIterableEquals(List.of(3, 4), () ->
            new ListOfArraysIteratorWrapper<>(extract));

        assertIterableEquals(List.of(1, 2, 5), () ->
            new ListOfArraysIteratorWrapper<>(list));
    }

    @Test
    void testConstructorSpecials() {
        var listNull = new ListOfArrays<>(null);
        var listEmpty = new ListOfArrays<>(new Object[] {});

        var listOne = new ListOfArrays<>(new Object[] {1});
        Object[] objects = new Object[257];
        for(int i = 0; i < 257; i++) objects[i] = i;
        var listMore = new ListOfArrays<>(objects);

        assertFalse(listNull.iterator().hasNext());
        assertFalse(listEmpty.iterator().hasNext());

        assertIterableEquals(List.of(1), () -> new ListOfArraysIteratorWrapper<>(listOne));
        assertIterableEquals(List.of(objects), () -> new ListOfArraysIteratorWrapper<>(listMore));
    }

    @Test
    void testInsertSeqSpecials() {
        var listStart = new ListOfArrays<>(new Object[] {3, 4, 5});
        var listEmpty = new ListOfArrays<>(null);

        assertThrows(IndexOutOfBoundsException.class, () -> listEmpty.insert(List.of(1, 2), 1));
        assertThrows(IndexOutOfBoundsException.class, () -> listEmpty.insert(List.of(1, 2), -1));

        listStart.insert(List.of(1, 2), 0);
        listEmpty.insert(List.of(1, 2, 3, 4, 5), 0);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listStart));
        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listEmpty));

        listStart.insert(List.of(), 1);
        listEmpty.insert(List.of(6, 7, 8), 5);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listStart));
        assertIterableEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), () -> new ListOfArraysIteratorWrapper<>(listEmpty));

    }

    @Test
    void testExtractSpecials() {
        var listStart = new ListOfArrays<>(new Object[] {3, 4, 5});
        var listEmpty = new ListOfArrays<>(null);

        assertThrows(IndexOutOfBoundsException.class, () -> listStart.extract(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> listEmpty.extract(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> listStart.extract(0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> listEmpty.extract(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> listStart.extract(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> listEmpty.extract(1, -1));

        var extractStart = listStart.extract(0, 2);
        listEmpty.insert(List.of(1, 2, 3, 4, 5), 0);
        var extractEmpty = listEmpty.extract(0, 3);

        assertIterableEquals(List.of(), () -> new ListOfArraysIteratorWrapper<>(listStart));
        assertIterableEquals(List.of(3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(extractStart));
        assertIterableEquals(List.of(5), () -> new ListOfArraysIteratorWrapper<>(listEmpty));
        assertIterableEquals(List.of(1, 2, 3, 4), () -> new ListOfArraysIteratorWrapper<>(extractEmpty));

        listStart.insert(List.of(1, 2, 3, 4, 5), 0);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listStart));
    }

    @Test
    void testInsertItSpecials() {
        var listStart = new ListOfArrays<>(new Object[] {4, 5});
        var listEmpty = new ListOfArrays<>(null);

        listStart.insert(List.of(
            new ElementWithIndex<Object>(1, 0),
            new ElementWithIndex<Object>(2, 0),
            new ElementWithIndex<Object>(3, 0),
            new ElementWithIndex<Object>(6, 2)
        ).iterator());

        listEmpty.insert(List.of(
            new ElementWithIndex<Object>(1, 0),
            new ElementWithIndex<Object>(2, 0),
            new ElementWithIndex<Object>(3, 1),
            new ElementWithIndex<Object>(6, 2)
        ).iterator());

        assertIterableEquals(List.of(1, 2, 3, 4, 5, 6), () ->
            new ListOfArraysIteratorWrapper<>(listStart));
        assertIterableEquals(List.of(1, 2), () ->
            new ListOfArraysIteratorWrapper<>(listEmpty));

        listEmpty.insert(List.of(
            new ElementWithIndex<Object>(0, 0),
            new ElementWithIndex<Object>(3, 3)
        ).iterator());

        assertIterableEquals(List.of(0, 1, 2), () ->
            new ListOfArraysIteratorWrapper<>(listEmpty));

        listStart.insert(List.of(
            new ElementWithIndex<Object>(0, 0),
            new ElementWithIndex<Object>(7, 6)
        ).iterator());

        assertIterableEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7), () ->
            new ListOfArraysIteratorWrapper<>(listStart));

        listStart.insert(List.of(
            new ElementWithIndex<Object>(0, 0),
            new ElementWithIndex<Object>(8, 6),
            new ElementWithIndex<Object>(9, 0),
            new ElementWithIndex<Object>(10, 0)
        ).iterator());

        assertIterableEquals(List.of(0, 0, 1, 2, 3, 4, 5, 8, 9, 10, 6, 7), () ->
            new ListOfArraysIteratorWrapper<>(listStart));

        listStart.insert(List.of(
            new ElementWithIndex<Object>(11, 12)
        ).iterator());

        assertIterableEquals(List.of(0, 0, 1, 2, 3, 4, 5, 8, 9, 10, 6, 7, 11), () ->
            new ListOfArraysIteratorWrapper<>(listStart));

        listStart.insert(List.of(
            new ElementWithIndex<Object>(12, 14)
        ).iterator());

        assertIterableEquals(List.of(0, 0, 1, 2, 3, 4, 5, 8, 9, 10, 6, 7, 11), () ->
            new ListOfArraysIteratorWrapper<>(listStart));
    }
}
