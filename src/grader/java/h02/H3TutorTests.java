package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static h02.ListUtils.*;
import static h02.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h02")
public class H3TutorTests {


    // test insert with empty list
    @Test
    public void testInsertEmptyList() {
        var expected = mainList(3);
        var list = toList(new ArrayList<String>());
        assertDoesNotThrow(() -> list.insert(expected, 0), EXCEPTION_ON_EXECUTION);
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
        assertDoesNotThrow(() -> list.insert(toInsert, index), EXCEPTION_ON_EXECUTION);
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
            toJavaList(list),
            LIST_DIFFERS
        );
    }

    // test insert at the end of the list
    @Test
    public void testInsertAtEnd() {
        testInsert(insertionList(4), mainList(3), 3, false);
    }

    // test insert at the end of the list with more than 256 elements
    @Test
    public void testInsertAtEndWithMoreThan256Elements() {
        testInsert(insertionList(257), mainList(250), 250, false);
    }

    // test insert in the middle of the list
    @Test
    public void testInsertInMiddle() {
        testInsert(insertionList(5), mainList(3), 2, false);
    }

    // test insert in the middle of the list with more than 256 elements
    @Test
    public void testInsertInMiddleWithMoreThan256Elements() {
        testInsert(insertionList(257), mainList(LONG_TEST_LENGTH), 255, false);
    }
}
