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
}
