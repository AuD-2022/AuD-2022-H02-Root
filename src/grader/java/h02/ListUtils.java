package h02;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListUtils {
    public static <T> ListOfArraysIteratorTutor<T> getIterator(ListOfArrays<T> list) {
        return new ListOfArraysIteratorTutor<>(list.head);
    }

    /**
     * returns a java.util.List representation of the given list
     *
     * @param list The list to iterate over.
     * @param <T>  The type of the list.
     * @return A java.util.List representation of the list.
     */
    public static <T> List<T> toJavaList(ListOfArrays<T> list) {
        // iterate over list
        var iterator = getIterator(list);
        var javaList = new ArrayList<T>();
        while (iterator.hasNext()) {
            javaList.add(iterator.next());
        }
        return javaList;
    }

    /**
     * returns a string representation of the given list
     *
     * @param list The list to iterate over.
     * @return A String representation of the list.
     */
    public static String stringRepresentation(ListOfArrays<?> list) {
        var sb = new StringBuilder("(");
        var iterator = getIterator(list);
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext())
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Asserts that the given List is Build Correctly.
     * <br>
     * A List is build correctly if it fulfills the following conditions:  <ul>
     * <li>The first element is the head of the list.</li>
     * <li>The last element is the tail of the list.</li>
     * <li>the currentNumber of each element is the number of the elements in it's array.</li>
     * <li>there are exactly currentNumber elements in the array of each element.</li>
     * <li>there are no gaps in the array of each element.</li>
     * </ul>
     * <br>
     *
     * @param list The List to check.
     */
    public static void assertListIsBuildCorrectly(ListOfArrays<?> list) {
        if (list.head == null) {
            assertNull(list.tail, "Head is null, but tail is not null");  // if head is null, then tail must be null
        } else {
            assertNotNull(list.tail); // tail must not be null
        }
        var head = list.head;
        var tail = list.tail;
        for (int i = 0; head != null; head = head.next, i++) {  // iterate over list
            assertItemArrayIsBuildCorrectly(head);
            if (head.next == null) {
                assertEquals(list.tail, head, "Tail is not the last element");  // check if tail is the same as head
            }
        }
    }

    private static void assertItemArrayIsBuildCorrectly(ListOfArraysItem<?> element) {
        assertNotNull(element.array);
        // check that currentNumber is the number of elements in the array
        int actualNumberOfElements = 0;
        for (int i = 0; i < element.array.length; i++) {
            if (element.array[i] != null) {
                actualNumberOfElements++;
            } else {
                // check that there are no gaps in the array
                for (int j = i + 1; j < element.array.length; j++) {
                    assertNull(element.array[j], "There is a gap in the array of element " + element.currentNumber + " at index " + i);
                }
                break;
            }
        }
        assertEquals(element.currentNumber, actualNumberOfElements, "CurrentNumber is not the correct number of elements in the array");
    }

    /**
     * Asserts that the Elements of the actual Lists are the same as the Elements of the expected Lists.
     *
     * @param expected The expected List.
     * @param actual   The actual Lists.
     * @param <T>      The type of the Lists.
     */
    public static <T> void assertSameElements(List<T> expected, List<T>... actual) {
        var expectedList = new ArrayList<T>(expected);
        for (var actualList : actual) {
            // assert size
            assertEquals(expectedList.size(), actualList.size(), "Lists are not of equal size");
            for (int i = 0; i < expectedList.size(); i++) {
                T expectedElement;
                T actualElement;
                if ((expectedElement = expectedList.get(i)) != (actualElement = actualList.get(i))) {
                    assertSame(expectedElement, actualElement);
                }
            }
        }
    }
}
