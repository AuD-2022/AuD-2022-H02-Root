package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static h02.ListUtils.*;
import static h02.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@TestForSubmission("h02")
public class H5TutorTests {
    // check Element With Index
    @Test
    public void testElementWithIndex() {
        var e = makeElementWithIndex(42, 1, false);
        assertEquals(1, e.getIndex());
        assertEquals(42, e.getElement());
    }

    // test insert method exceptions
    @Test
    public void testInsertExceptionWithNegativeIndex() {
        var list = toList(mainList(0));
        var toInsert = List.of(
            makeElementWithIndex("#0", -1),
            makeElementWithIndex("#1", 0),
            makeElementWithIndex("#2", 1)
        );
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(toInsert.iterator()));
        assertEqualsOneOf(List.of("Index out of range: -1", "-1"), ex1.getMessage(), "Exception message is not correct");
    }

    // test insert exception when inserting more than 256 elements
    @Test
    public void testInsertExceptionWithTooManyElements() {
        var toInsert = IntStream.range(0, LONG_TEST_LENGTH).mapToObj(x -> makeElementWithIndex("#" + x, x == 0 ? 0 : 1)).toList();
        var listContent = mainList(LONG_TEST_LENGTH);
        var list = toList(listContent);
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(assertDoesNotThrow(toInsert::iterator, EXCEPTION_ON_EXECUTION)));
        assertEquals("array could not hold elements to be moved", ex1.getMessage(), "Exception message is not correct");
    }

    public <T> void testInsert(List<T> listContent, List<ElementWithIndex<T>> toInsert, boolean strict) {
        var list = toList(listContent);
        var expected = new ArrayList<>(listContent);
        var offset = 0;
        for (var e : toInsert) {
            if (offset + e.getIndex() > expected.size()) {
                break;
            }
            expected.add(offset += e.getIndex(), e.getElement());
            offset++;
        }
        assertDoesNotThrow(() -> list.insert(assertDoesNotThrow(toInsert::iterator, EXCEPTION_ON_EXECUTION)), EXCEPTION_ON_EXECUTION);
        assertEquals(expected, toJavaList(list));
    }

    // Test insert at the end of the list
    @Test
    public void testInsertAtEnd() {
        testInsert(mainList(5),
            List.of(
                makeElementWithIndex("#0", 5)
            ),
            false
        );
    }

    // Test insert at the beginning of the list
    @Test
    public void testInsertAtBeginning() {
        testInsert(mainList(5),
            List.of(
                makeElementWithIndex("#0", 0)
            ),
            false
        );
    }

    // Test insert in the middle of the list
    @Test
    public void testInsertInMiddle() {
        testInsert(mainList(5),
            List.of(
                makeElementWithIndex("#0", 2)
            ),
            false
        );
    }

    // Test insert multiple elements
    @Test
    public void testInsertMultipleAsBlock() {
        testInsert(mainList(14),
            List.of(
                makeElementWithIndex("#0", 2),
                makeElementWithIndex("#1", 1),
                makeElementWithIndex("#2", 1),
                makeElementWithIndex("#3", 1),
                makeElementWithIndex("#4", 1)
            ),
            false
        );
    }

    // Test insert multiple elements spread across the list
    @Test
    public void testInsertMultipleAsSpread() {
        testInsert(mainList(0 + 2 + 5 + 5 + 6),
            List.of(
                makeElementWithIndex("#0", 0),
                makeElementWithIndex("#1", 2),
                makeElementWithIndex("#2", 5),
                makeElementWithIndex("#3", 5),
                makeElementWithIndex("#4", 6)
            ),
            false
        );
    }

    // Test stop conditions
    @Test
    public void testInsertStopConditions() {
        testInsert(mainList(3),
            List.of(
                makeElementWithIndex("#0", 2),
                makeElementWithIndex("#1", 0),
                makeElementWithIndex("#2", 0),
                makeElementWithIndex("#3", 0),
                makeElementWithIndex("#4", 0)
            ),
            false
        );
    }

    @Test
    public void testInsertExample() {
        testInsert(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"),
            List.of(
                makeElementWithIndex("r", 1),
                makeElementWithIndex("s", 2),
                makeElementWithIndex("t", 1),
                makeElementWithIndex("u", 2),
                makeElementWithIndex("v", 0),
                makeElementWithIndex("w", 3),
                makeElementWithIndex("x", 4),
                makeElementWithIndex("y", 2),
                makeElementWithIndex("z", 1)
            ),
            true
        );
    }
}
