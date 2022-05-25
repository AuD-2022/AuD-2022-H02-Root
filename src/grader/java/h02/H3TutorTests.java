package h02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class H3TutorTests {


    // test insert with empty list
    @Test
    public void testInsertEmptyList() {
        var expected = List.of("A", "B", "C");
        var list = toList(new ArrayList<String>());
        list.insert(expected, 0);
        assertEquals(expected, toJavaList(list));
    }

    // test insert exceptions and make sure that the message is correct
    @Test
    public void testInsertExceptions() {
        var list = toList(List.of("A", "B", "C"));
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(List.of("A"), -1), "Inserting at negative index should throw IndexOutOfBoundsException");
        assertEquals("Index out of range: -1", ex1.getMessage(), "Exception message is not correct");
        var ex2 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(List.of("A"), 4), "Inserting at index 4 should throw IndexOutOfBoundsException when the list has 3 elements");
        assertEquals("Index out of range: 4", ex2.getMessage(), "Exception message is not correct");
    }

    // test insert at the end of the list
    @Test
    public void testInsertAtEnd() {
        var toInsert = List.of("A", "B", "C", "D");
        var listContent = List.of("A", "B", "C");
        var list = toList(listContent);
        list.insert(toInsert, 3);
        assertListIsBuildCorrectly(list);
        assertEquals(Stream.concat(listContent.stream(), toInsert.stream()).toList(), toJavaList(list));
    }

    // test insert at the end of the list with more than 256 elements
    @Test
    public void testInsertAtEndWithMoreThan256Elements() {
        var toInsert = intList(257);
        var listContent = intList(250);
        var list = toList(listContent);
        list.insert(toInsert, 250);
        assertListIsBuildCorrectly(list);
        assertEquals(Stream.concat(listContent.stream(), toInsert.stream()).toList(), toJavaList(list));
    }

    // test insert in the middle of the list
    @Test
    public void testInsertInMiddle() {
        var toInsert = stringList(45);
        var listContent = stringList(30);
        var list = toList(listContent);
        list.insert(toInsert, 30);
        assertEquals(
            Stream.of(
                    listContent.stream().limit(30),
                    toInsert.stream(),
                    listContent.stream().skip(30)
                )
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .toList(),
            toJavaList(list)
        );
        assertListIsBuildCorrectly(list);
    }

    // test insert in the middle of the list with more than 256 elements
    @Test
    public void testInsertInMiddleWithMoreThan256Elements() {
        var toInsert = stringList(257);
        var listContent = stringList(2550);
        var list = toList(listContent);
        list.insert(toInsert, 255);
        assertEquals(
            Stream.of(
                    listContent.stream().limit(255),
                    toInsert.stream(),
                    listContent.stream().skip(255)
                )
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .toList(),
            toJavaList(list)
        );
        assertListIsBuildCorrectly(list);
    }
}
