package h02;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

import static h02.ListUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@TestForSubmission("h02")
public class H1TutorTests {

    // Test the constructor of ListOfArraysTutor with null as argument
    @Test
    public void testListOfArraysConstructor_Null() {
        ListOfArrays<String> list = new ListOfArrays<>(null);
        assertNull(head(list));
        assertNull(tail(list));
    }

    // Test the constructor of ListOfArraysTutor with an empty string array
    @Test
    public void testListOfArraysConstructor_EmptyList() {
        var listArray = new String[] {};
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        assertEquals(List.of(), toJavaList(list));
    }

    // single element list
    @Test
    public void testListOfArraysConstructor_SingleElementList() {
        var listArray = new String[] { "A" };
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        assertEquals(List.of(listArray), toJavaList(list));
    }

    // Test that the Elements of the list are the same as the elements of the argument array
    @Test
    public void testListOfArraysConstructor_SameElements() {
        var listArray = new String[] { "H", "E", "L", "L", "O" };
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        assertSameElements(List.of(listArray), toJavaList(list));
    }

    // array with more than 256 elements
    @Test
    public void testListOfArraysConstructor_LongList() {
        var listArray = new String[257];
        for (int i = 0; i < 257; i++) {
            listArray[i] = Character.toString((char) i);
        }
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        assertEquals(List.of(listArray), toJavaList(list));
    }

    // Harder test checking that the list is build correctly
    @Test
    public void testListOfArraysConstructor_CorrectList() {
        var listArray = new String[4096];
        for (int i = 0; i < 4096; i++) {
            listArray[i] = Character.toString((char) i);
        }
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        assertEquals(List.of(listArray), toJavaList(list));
        assertListIsBuildCorrectly(list);
    }
}
