package h02;

import java.util.List;

import static h02.ListUtils.*;
import static h02.TestConstants.*;
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

    /**
     * Test the constructor of ListOfArraysTutor with a given list size
     *
     * @param listSize the size of the list to be created
     */
    public void testListOfArraysConstructor(int listSize, boolean checkListIsBuildCorrectly) {
        List<String> expected = mainList(listSize);
        var list = assertDoesNotThrow(() -> new ListOfArrays<>(expected.toArray(String[]::new)), EXCEPTION_ON_EXECUTION);
        assertListIsBuildCorrectly(list, checkListIsBuildCorrectly, checkListIsBuildCorrectly, checkListIsBuildCorrectly);
        assertEquals(expected, toJavaList(list), LIST_DIFFERS);
    }


    // Test the constructor of ListOfArraysTutor with an empty string array
    @Test
    public void testListOfArraysConstructor_EmptyList() {
        testListOfArraysConstructor(0, false);
    }

    // single element list
    @Test
    public void testListOfArraysConstructor_SingleElementList() {
        testListOfArraysConstructor(1, false);
    }

    // Test that the Elements of the list are the same as the elements of the argument array
    @Test
    public void testListOfArraysConstructor_SameElements() {
        var listArray = List.of(1, 2, 3, 4, 5).toArray(Integer[]::new);
        ListOfArrays<Integer> list = assertDoesNotThrow(() -> new ListOfArrays<>(listArray), EXCEPTION_ON_EXECUTION);
        assertListIsIterable(list);
        assertSameElements(List.of(listArray), toJavaList(list));
    }

    // array with more than 256 elements
    @Test
    public void testListOfArraysConstructor_LongList() {
        testListOfArraysConstructor(257, false);
    }

    // Harder test checking that the list is build correctly
    @Test
    public void testListOfArraysConstructor_CorrectList() {
        testListOfArraysConstructor(513, true);

    }

}
