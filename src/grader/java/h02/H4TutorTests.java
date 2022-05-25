package h02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class H4TutorTests {
    // test exceptions
    @Test
    public void testExtractWithEmptyList() {
        var listContent = intList(14);
        var list = toList(listContent);
        var ex1 = assertThrows(IndexOutOfBoundsException.class, () -> list.extract(-1, 1));
        assertEqualsOneOf(List.of("Index out of range: -1", "-1"), ex1.getMessage(), "Exception message is not correct");
        var ex2 = assertThrows(IndexOutOfBoundsException.class, () -> list.extract(3, 2));
        assertEquals("3 is greater than 2", ex2.getMessage(), "Exception message is not correct");
    }

    public void testExtract(int i, int j, int listLength, boolean checkList, boolean checkResult) {
        var listContent = intList(listLength);
        var list = toList(listContent);
        var result = list.extract(i, j);
        if (checkList) {
            assertListIsBuildCorrectly(list);
            assertEquals(listContent.stream().limit(j + 1).skip(i).toList(), toJavaList(result), "Extracted list is not correct");
        }
        if (checkResult) {
            assertListIsBuildCorrectly(result);
            assertEquals(Stream.concat(listContent.stream().limit(i), listContent.stream().skip(j + 1)).toList(), toJavaList(list), "Modified list is not correct");
        }
    }

    // test extract with one element
    @Test
    public void testExtractWithOneElement() {
        testExtract(13, 13, 14, true, false);
    }


    // test extract with multiple elements
    @Test
    public void testExtractWithMultipleElements() {
        testExtract(3, 13, 14, true, false);
    }

    // test extract with all elements
    @Test
    public void testExtractWithAllElements() {
        testExtract(0, 13, 14, true, false);
    }

    // test extract with multiple elements in the middle
    @Test
    public void testExtractWithMultipleElementsInTheMiddle() {
        testExtract(3, 7, 14, true, false);
    }

    // test extract with more than 256 elements in the middle
    @Test
    public void testExtractWithMoreThan256ElementsInTheMiddle() {
        testExtract(3, 7, 257, true, false);
    }

    // test return value
    @Test
    public void testExtractReturnValue() {
        testExtract(3, 7, 14, false, false);
    }

    // combined tests
    @Test
    public void testExtractCombined() {
        // one element
        testExtract(13, 13, 14, true, true);
        // multiple elements
        testExtract(3, 13, 14, true, true);
        // middle
        testExtract(3, 7, 14, true, true);
        // all
        testExtract(0, 1024, 1025, true, true);
    }
}
