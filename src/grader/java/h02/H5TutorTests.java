package h02;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        var list = toList(intList(0));
        var toInsert = List.of(
            makeElementWithIndex(42, -1),
            makeElementWithIndex(42, 0),
            makeElementWithIndex(42, 1)
        );
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(toInsert.iterator()));
        assertEqualsOneOf(List.of("Index out of range: -1", "-1"), ex1.getMessage(), "Exception message is not correct");
    }

    // test insert exception when inserting more than 256 elements
    @Test
    public void testInsertExceptionWithTooManyElements() {
        var toInsert = IntStream.range(0, 1024).mapToObj(x -> makeElementWithIndex(42, x == 0 ? 0 : 1)).toList();
        var listContent = intList(4096);
        var list = toList(listContent);
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.insert(toInsert.iterator()));
        assertEquals("array could not hold elements to be moved", ex1.getMessage(), "Exception message is not correct");
    }

    public <T> void testInsert(List<T> listContent, List<ElementWithIndex<T>> toInsert) {
        var list = toList(listContent);
        var expected = listContent;
        var offset = 0;
        for (var e : toInsert) {
            if (offset + e.getIndex() > expected.size()) {
                break;
            }
            expected.add(offset += e.getIndex(), e.getElement());
            offset++;
        }
        list.insert(toInsert.iterator());
        assertEquals(expected, toJavaList(list));
        assertListIsBuildCorrectly(list);
    }

    // Test insert at the end of the list
    @Test
    public void testInsertAtEnd() {
        testInsert(intList(5),
            List.of(
                makeElementWithIndex(42, 5)
            )
        );
    }

    // Test insert at the beginning of the list
    @Test
    public void testInsertAtBeginning() {
        testInsert(intList(5),
            List.of(
                makeElementWithIndex(42, 0)
            )
        );
    }

    // Test insert in the middle of the list
    @Test
    public void testInsertInMiddle() {
        testInsert(intList(5),
            List.of(
                makeElementWithIndex(42, 2)
            )
        );
    }

    // Test insert multiple elements
    @Test
    public void testInsertMultipleAsBlock() {
        testInsert(intList(14),
            List.of(
                makeElementWithIndex(43, 2),
                makeElementWithIndex(44, 1),
                makeElementWithIndex(45, 1),
                makeElementWithIndex(46, 1),
                makeElementWithIndex(47, 1)
            )
        );
    }

    // Test insert multiple elements spread across the list
    @Test
    public void testInsertMultipleAsSpread() {
        testInsert(intList(0 + 2 + 5 + 5 + 6),
            List.of(
                makeElementWithIndex(43, 0),
                makeElementWithIndex(44, 2),
                makeElementWithIndex(45, 5),
                makeElementWithIndex(46, 5),
                makeElementWithIndex(47, 6)
            )
        );
    }

    // Test stop conditions
    @Test
    public void testInsertStopConditions() {
        testInsert(intList(3),
            List.of(
                makeElementWithIndex(42, 2),
                makeElementWithIndex(43, 0),
                makeElementWithIndex(44, 0),
                makeElementWithIndex(46, 0),
                makeElementWithIndex(47, 0)
            )
        );
    }
}
