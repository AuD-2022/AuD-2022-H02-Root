package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.*;
@TestForSubmission("h02")
public class H2TutorTests {


    // method hasNext returns true if there is a next element
    @Test
    public void test_hasNext_emptyList_returnsFalse() {
        var iterator = new ListOfArraysIterator<>(null);
        assertFalse(iterator.hasNext(), "hasNext() should return false when called on an empty list");
        var javaList = List.of("A", "B", "C");
        var iterator2 = new ListOfArraysIterator<>(toListHead(javaList));
        assertTrue(iterator2.hasNext(), "hasNext() should return true when called on the first element of a non-empty list");
    }

    // method next returns the next element
    @Test
    public void test_next_emptyList_throwsNoSuchElementException() {
        var iterator = new ListOfArraysIterator<>(null);
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "next() should throw NoSuchElementException when called on an empty list");
        var javaList = stringList(257);
        var iterator2 = new ListOfArraysIterator<>(toListHead(javaList));
        for (int i = 0; i < javaList.size(); i++) {
            assertEquals(javaList.get(i), iterator2.next(), "Lists differ at index " + i);
        }
    }

    // combined test
    @Test
    public void test_iterator_combined() {
        var javaList = stringList(4096);
        var list = toList(javaList);
        // Test the iterator method
        var iterator = list.iterator();
        for (int i = 0; i < javaList.size(); i++) {
            assertSame(javaList.get(i), iterator.next());
            if (i < javaList.size() - 1) {
                assertTrue(iterator.hasNext());
            } else {
                assertFalse(iterator.hasNext());
            }
        }
    }
}
