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

        listStart.insert(List.of(1, 2), 0);
        listEmpty.insert(List.of(1,2,3,4,5), 0);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listStart));
        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listEmpty));

        listStart.insert(List.of(), 1);

        assertIterableEquals(List.of(1, 2, 3, 4, 5), () -> new ListOfArraysIteratorWrapper<>(listStart));
    }
}
