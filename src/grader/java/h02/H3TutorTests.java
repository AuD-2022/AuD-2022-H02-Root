package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h02")
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
        assertEqualsOneOf(List.of("Index out of range: -1", "-1"), ex1.getMessage(), "Exception message is not correct");
        var ex2 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(List.of("A"), 4), "Inserting at index 4 should throw IndexOutOfBoundsException when the list has 3 elements");
        assertEqualsOneOf(List.of("Index out of range: 4", "4"), ex2.getMessage(), "Exception message is not correct");
    }

    public <T> void testInsert(List<T> toInsert, List<T> listContent, int index, boolean checkIfListIsBuiltCorrectly) {
        var list = toList(listContent);
        list.insert(toInsert, index);
        assertListIsBuildCorrectly(list, checkIfListIsBuiltCorrectly, checkIfListIsBuiltCorrectly, checkIfListIsBuiltCorrectly);
        assertEquals(
            Stream.of(
                    listContent.stream().limit(index),
                    toInsert.stream(),
                    listContent.stream().skip(index)
                )
                .reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .toList(),
            toJavaList(list)
        );
    }

    // test insert at the end of the list
    @Test
    public void testInsertAtEnd() {
        testInsert(List.of("A", "B", "C", "D"), List.of("A", "B", "C"), 3, false);
    }

    // test insert at the end of the list with more than 256 elements
    @Test
    public void testInsertAtEndWithMoreThan256Elements() {
        testInsert(intList(257), intList(250), 250, false);
    }

    // test insert in the middle of the list
    @Test
    public void testInsertInMiddle() {
        testInsert(intList(5), intList(3), 2, false);
    }

    // test insert in the middle of the list with more than 256 elements
    @Test
    public void testInsertInMiddleWithMoreThan256Elements() {
        testInsert(intList(257), intList(2550), 255, false);
    }
}
