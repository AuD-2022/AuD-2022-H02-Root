package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static h02.ListUtils.*;
import static h02.TestConstants.EXCEPTION_ON_EXECUTION;
import static org.junit.jupiter.api.Assertions.*;
@TestForSubmission("h02")
public class H2TutorTests {


    // method hasNext returns true if there is a next element
    @Test
    public void test_hasNext_emptyList_returnsFalse() {
        var iterator = assertDoesNotThrow(() -> new ListOfArraysIterator<>(null), EXCEPTION_ON_EXECUTION);
        assertFalse(assertDoesNotThrow(() -> iterator.hasNext(), EXCEPTION_ON_EXECUTION), "hasNext() should return false when called on an empty list");
        var javaList = List.of("A", "B", "C");
        var iterator2 = assertDoesNotThrow(() -> new ListOfArraysIterator<>(toListHead(javaList)), EXCEPTION_ON_EXECUTION);
        assertTrue(assertDoesNotThrow(() -> iterator2.hasNext(), EXCEPTION_ON_EXECUTION), "hasNext() should return true when called on the first element of a non-empty list");
    }

    // method next returns the next element
    @Test
    public void test_next_emptyList_throwsNoSuchElementException() {
        var iterator = assertDoesNotThrow(() -> new ListOfArraysIterator<>(null), EXCEPTION_ON_EXECUTION);
        assertThrows(NoSuchElementException.class, () -> assertDoesNotThrow(() -> iterator.next(), EXCEPTION_ON_EXECUTION), "next() should throw NoSuchElementException when called on an empty list");
        var javaList = stringList(257);
        var iterator2 = assertDoesNotThrow(() -> new ListOfArraysIterator<>(toListHead(javaList)), EXCEPTION_ON_EXECUTION);
        for (int i = 0; i < javaList.size(); i++) {
            assertEquals(javaList.get(i), assertDoesNotThrow(() -> iterator2.next(), EXCEPTION_ON_EXECUTION), "Lists differ at index " + i);
        }
    }

    // combined test
    @Test
    public void test_iterator_combined() {
        var javaList = stringList(4096);
        var list = toList(javaList);
        // Test the iterator method
        var iterator = assertDoesNotThrow(() -> list.iterator(), EXCEPTION_ON_EXECUTION);
        for (int i = 0; i < javaList.size(); i++) {
            assertSame(javaList.get(i), assertDoesNotThrow(() -> iterator.next(), EXCEPTION_ON_EXECUTION));
            if (i < javaList.size() - 1) {
                assertTrue(assertDoesNotThrow(() -> iterator.hasNext(), EXCEPTION_ON_EXECUTION));
            } else {
                assertFalse(assertDoesNotThrow(() -> iterator.hasNext(), EXCEPTION_ON_EXECUTION));
            }
        }
    }
}
