package h02;

import java.util.List;

import static h02.ListUtils.assertListIsBuildCorrectly;
import static h02.ListUtils.assertSameElements;
import static h02.ListUtils.head;
import static h02.ListUtils.tail;
import static h02.ListUtils.toJavaList;
import static h02.TestConstants.DIFFERS_HEAD_NULL;
import static h02.TestConstants.DIFFERS_TAIL_NULL;
import static h02.TestConstants.EXCEPTION_ON_EXECUTION;
import static h02.TestConstants.LIST_DIFFERS;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission("h02")
public class H1TutorTests {

    // Test the constructor of ListOfArraysTutor with null as argument
    @Test
    public void testListOfArraysConstructor_Null() {
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(null), EXCEPTION_ON_EXECUTION);
        assertNull(head(list), DIFFERS_HEAD_NULL);
        assertNull(tail(list), DIFFERS_TAIL_NULL);
    }

    // Test the constructor of ListOfArraysTutor with an empty string array
    @Test
    public void testListOfArraysConstructor_EmptyList() {
        var listArray = new String[] {};
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertEquals(List.of(), toJavaList(list), LIST_DIFFERS);
    }

    // single element list
    @Test
    public void testListOfArraysConstructor_SingleElementList() {
        var listArray = new String[] {"A"};
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertEquals(List.of(listArray), toJavaList(list), LIST_DIFFERS);
    }

    // Test that the Elements of the list are the same as the elements of the argument array
    @Test
    public void testListOfArraysConstructor_SameElements() {
        var listArray = new String[] {"H", "E", "L", "L", "O"};
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertSameElements(List.of(listArray), toJavaList(list));
    }

    // array with more than 256 elements
    @Test
    public void testListOfArraysConstructor_LongList() {
        var listArray = new String[257];
        for (int i = 0; i < 257; i++) {
            listArray[i] = Character.toString((char) i);
        }
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertEquals(List.of(listArray), toJavaList(list), LIST_DIFFERS);
    }

    // Harder test checking that the list is build correctly
    @Test
    public void testListOfArraysConstructor_CorrectList() {
        var listArray = new String[4096];
        for (int i = 0; i < 4096; i++) {
            listArray[i] = Character.toString((char) i);
        }
        ListOfArrays<String> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertEquals(List.of(listArray), toJavaList(list));
        assertListIsBuildCorrectly(list);
    }

}
