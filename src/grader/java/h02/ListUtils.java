package h02;

import org.mockito.Answers;
import org.opentest4j.AssertionFailedError;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Util Methods for ListOfArrays
 *
 * @author Ruben Deisenroth
 */
public class ListUtils {

    /**
     * Record Class to store head and tail of a list
     *
     * @param head head of the list
     * @param tail tail of the list
     * @param <T>  type of the list
     */
    public record HeadAndTail<T>(ListOfArraysItem<T> head, ListOfArraysItem<T> tail) {
    }

    /**
     * Returns the head of the list.
     *
     * @param list The list to get the head of.
     * @param <T>  The type of the list.
     * @return The head of the list.
     */
    @SuppressWarnings("unchecked")
    public static <T> ListOfArraysItem<T> head(ListOfArrays<T> list) {
//        var headField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("head"), "cannot access List head. Available fields: " + Arrays.toString(list.getClass().getDeclaredFields()));
//        headField.setAccessible(true);
//        return assertDoesNotThrow(() -> (ListOfArraysItem<T>) headField.get(list), "cannot access List head. Available fields: " + Arrays.toString(list.getClass().getDeclaredFields()));
        return list.head;
    }

    /**
     * Returns the tail of the list.
     *
     * @param list The list to get the tail of.
     * @param <T>  The type of the list.
     * @return The tail of the list.
     */
    @SuppressWarnings("unchecked")
    public static <T> ListOfArraysItem<T> tail(ListOfArrays<T> list) {
//        var tailField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("tail"), "cannot access List tail");
//        tailField.setAccessible(true);
//        return assertDoesNotThrow(() -> (ListOfArraysItem<T>) tailField.get(list), "cannot access List tail");
        return list.tail;
    }

    /**
     * get the  ARRAY_LENGTH Field from ListOfArrays
     *
     * @param list the list to get the field from
     * @return the max length of each array
     */
    public static int getArrayLength(ListOfArrays<?> list) {
//        var arrayLengthField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("ARRAY_LENGTH"), "cannot access Field ARRAY_LENGTH");
//        arrayLengthField.setAccessible(true);
//        return assertDoesNotThrow(() -> (int) arrayLengthField.get(list), "cannot access Field ARRAY_LENGTH");
        return ListOfArrays.ARRAY_LENGTH;
    }

    /**
     * sets the head of the list to the given value
     *
     * @param list The list to set the head of.
     * @param head The value to set the head to.
     * @param <T>  The type of the list.
     * @return The new head of the list.
     */
    public static <T> ListOfArraysItem<T> setHead(ListOfArrays<T> list, ListOfArraysItem<T> head) {
//        var headField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("head"), "cannot access List head. Available fields: " + Arrays.toString(list.getClass().getDeclaredFields()));
//        headField.setAccessible(true);
//        assertDoesNotThrow(() -> headField.set(list, head), "cannot set List head");
        list.head = head;
        return head;
    }

    /**
     * sets the tail of the list to the given value
     *
     * @param list The list to set the tail of.
     * @param tail The value to set the tail to.
     * @param <T>  The type of the list.
     * @return The new tail of the list.
     */
    public static <T> ListOfArraysItem<T> setTail(ListOfArrays<T> list, ListOfArraysItem<T> tail) {
//        var tailField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("tail"), "cannot access List tail");
//        tailField.setAccessible(true);
//        assertDoesNotThrow(() -> tailField.set(list, tail), "cannot set List tail");
        list.tail = tail;
        return tail;
    }

    /**
     * sets the head and tail of the list to the given value
     *
     * @param list The list to set the head and tail of.
     * @param ht   The value to set the head and tail to.
     * @param <T>  The type of the list.
     * @return The new head and tail of the list.
     */
    public static <T> HeadAndTail<T> setHeadAndTail(ListOfArrays<T> list, HeadAndTail<T> ht) {
        setHead(list, ht.head);
        setTail(list, ht.tail);
        return ht;
    }

    /**
     * sets the ARRAY_LENGTH Field of the list to the given value
     *
     * @param list        the list to set the field of
     * @param arrayLength the value to set the field to
     * @return the new value of the field
     */
    public static int setArrayLength(ListOfArrays<?> list, int arrayLength) {
//        var arrayLengthField = assertDoesNotThrow(() -> list.getClass().getDeclaredField("ARRAY_LENGTH"), "cannot access Field ARRAY_LENGTH");
////        arrayLengthField.setAccessible(true);
//        // get modifiers to make field non-final
////        FieldHelper.makeNonFinal(arrayLengthField);
//        assertDoesNotThrow(() -> FieldHelper.setFinalStatic(arrayLengthField, arrayLength), "cannot overwrite Field ARRAY_LENGTH");
        ListOfArrays.ARRAY_LENGTH = arrayLength;
        return arrayLength;
    }

    /**
     * Creates a new ElementWithIndex with the given index and value. Optionally stubbing the constructor and getters.
     *
     * @param element the value of the new ElementWithIndex
     * @param index   the index of the new ElementWithIndex
     * @param stub    Whether to stub the constructor and getters.
     * @param <T>     The type of the ElementWithIndex.
     * @return The new ElementWithIndex.
     */
    @SuppressWarnings("unchecked")
    public static <T> ElementWithIndex<T> makeElementWithIndex(T element, int index, boolean stub) {
        if (!stub) {
            return assertDoesNotThrow(() -> new ElementWithIndex<>(element, index), "Cannot create ElementWithIndex.");
        }
        ElementWithIndex<T> elementWithIndex = spy(mock(ElementWithIndex.class, Answers.CALLS_REAL_METHODS));
        doReturn(index).when(elementWithIndex).getIndex();
        doReturn(element).when(elementWithIndex).getElement();
        return elementWithIndex;
    }

    /**
     * Creates a new ElementWithIndex with the given index and value.
     *
     * @param element the value of the new ElementWithIndex
     * @param index   the index of the new ElementWithIndex
     * @param <T>     The type of the ElementWithIndex.
     * @return The new ElementWithIndex.
     */
    public static <T> ElementWithIndex<T> makeElementWithIndex(T element, int index) {
        return makeElementWithIndex(element, index, true);
    }

    /**
     * create ListOfArrays from List
     *
     * @param list the list to create the ListOfArrays from
     * @param <T>  the type of the list
     * @return the created ListOfArrays
     */
    @SuppressWarnings("unchecked")
    public static <T> ListOfArrays<T> toList(List<T> list, boolean stub) {
        ListOfArrays<T> result;
        if (stub) {
            result = spy(mock(ListOfArrays.class, Answers.CALLS_REAL_METHODS));
            setArrayLength(result, 256);
            var ht = toHeadTail(list);
            //Set head and tail
            setHead(result, ht.head);
            setTail(result, ht.tail);
        } else {
            result = new ListOfArrays<T>((T[]) list.toArray());
        }
        return result;
    }

    /**
     * create ListOfArrays from List
     *
     * @param list the list to create the ListOfArrays from
     * @param <T>  the type of the list
     * @return the created ListOfArrays
     */
    @SuppressWarnings("unchecked")
    public static <T> ListOfArrays<T> toList(List<T> list) {
        // Try without stubbing
        try {
            var result = toList(list, false);
            assertListIsIterable(result);
            assertEquals(toJavaList(result), list);
            return result;
        } catch (Exception e) {
            return toList(list, true);
        }
    }


    /**
     * create a ListOfArrays from List and returns the head and tail of the list
     *
     * @param list the list to create the ListOfArrays from
     * @param <T>  the type of the list
     * @return the head and tail of the created ListOfArrays
     */
    @SuppressWarnings("unchecked")
    public static <T> HeadAndTail<T> toHeadTail(List<T> list) {
        //Empty sequence or null -> Only null references
        if (list == null || list.isEmpty()) {
            return new HeadAndTail<T>(null, null);
        }
        var ARRAY_LENGTH = 256;
        ListOfArraysItem<T> head = null;
        ListOfArraysItem<T> tail = null;

        //Input elements
        for (int i = 0; i < list.size(); i++) {
            //New ListOfArraysItem object necessary?
            if (i % ARRAY_LENGTH == 0) {
                //First ever element?
                if (head == null)
                    head = tail = new ListOfArraysItem<>();
                else {
                    tail.next = new ListOfArraysItem<>();
                    tail = tail.next;
                }
                tail.array = (T[]) new Object[ARRAY_LENGTH];
                tail.currentNumber = 0;
            }
            //Add element and increment currentNumber
            tail.array[tail.currentNumber++] = list.get(i);
        }

        return new HeadAndTail<T>(head, tail);
    }

    /**
     * create a ListOfArrays from List and returns the head of the list
     *
     * @param list the list to create the ListOfArrays from
     * @param <T>  the type of the list
     * @return the head of the created ListOfArrays
     */
    public static <T> ListOfArraysItem<T> toListHead(List<T> list) {
        return toHeadTail(list).head;
    }

    /**
     * Creates a Failsafe Tutor Iterator for a given ListOfArrays.
     *
     * @param list the ListOfArrays to create the Failsafe Tutor Iterator for
     * @param <T>  the type of the ListOfArrays
     * @return the created Failsafe Tutor Iterator
     */
    public static <T> ListOfArraysIteratorTutor<T> getIterator(ListOfArrays<T> list) {
        return new ListOfArraysIteratorTutor<>(head(list));
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
     * @param list               The List to check.
     * @param checkCurrentNumber if true, the currentNumber of each element is checked.
     * @param checkGaps          if true, the gaps in the array of each element are checked.
     * @param checkTail          if true, the tail of the list is checked.
     */
    public static void assertListIsBuildCorrectly(ListOfArrays<?> list, boolean checkCurrentNumber, boolean checkGaps, boolean checkTail) {
        var head = head(list);
        var tail = tail(list);
        if (head == null) {
            assertNull(tail, "Head is null, but tail is not null.");  // if head is null, then tail must be null
        } else {
            assertNotNull(tail); // tail must not be null
        }
        var visited = new HashSet<ListOfArraysItem<?>>();
        for (int i = 0; head != null; head = head.next, i++) {  // iterate over list
            assertFalse(visited.contains(head), "List contains duplicate item. Aborting to prevent infinite loop.");
            assertItemArrayIsBuildCorrectly(head, checkCurrentNumber, checkGaps);
            if (head.next == null && checkTail) {
                assertEquals(tail, head, "Tail is not the last element.");  // check if tail is the same as head
            }
            visited.add(head);
        }
//        assertTrue(visited.contains(tail), "Detached List Tail");  // check if tail is in the list
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
        assertListIsBuildCorrectly(list, true, true, true);
    }

    /**
     * Asserts that the given List is Iterable
     *
     * @param list The List to check.
     */
    public static void assertListIsIterable(ListOfArrays<?> list) {
        assertListIsBuildCorrectly(list, false, false, false);

    }

    /**
     * Asserts that the given ListOfArraysItem is Build Correctly.
     *
     * @param element            The ListOfArraysItem to check.
     * @param checkCurrentNumber if true, the currentNumber of each element is checked.
     * @param checkGaps          if true, the gaps in the array of each element are checked.
     */
    private static void assertItemArrayIsBuildCorrectly(ListOfArraysItem<?> element, boolean checkCurrentNumber, boolean checkGaps) {
        assertNotNull(element.array);
        // check that currentNumber is the number of elements in the array
        int actualNumberOfElements = 0;
        for (int i = 0; i < element.array.length; i++) {
            if (element.array[i] != null) {
                actualNumberOfElements++;
            } else {
                if (checkGaps) {
                    // check that there are no gaps in the array
                    for (int j = i + 1; j < element.array.length; j++) {
                        assertNull(element.array[j], "There is a gap in the array of element " + element.currentNumber + " at index " + i + ".");
                    }
                    break;
                }
            }
        }
        if (checkCurrentNumber) {
            assertEquals(element.currentNumber, actualNumberOfElements, "CurrentNumber is not the correct number of elements in the array.");
        }
    }

    /**
     * Asserts that the Elements of the actual Lists are the same as the Elements of the expected Lists.
     *
     * @param expected The expected List.
     * @param actual   The actual Lists.
     * @param <T>      The type of the Lists.
     */
    @SafeVarargs
    public static <T> void assertSameElements(List<T> expected, List<T>... actual) {
        var expectedList = new ArrayList<T>(expected);
        for (var actualList : actual) {
            // assert size
            assertEquals(expectedList.size(), actualList.size(), "Lists are not of equal size.");
            for (int i = 0; i < expectedList.size(); i++) {
                T expectedElement;
                T actualElement;
                if ((expectedElement = expectedList.get(i)) != (actualElement = actualList.get(i))) {
                    assertSame(expectedElement, actualElement);
                }
            }
        }
    }

    /**
     * Creates a String list where each element is the string representation of a char with its index
     *
     * @param length The length of the list.
     * @return The generated list.
     */
    public static List<String> stringList(int length) {
        return IntStream.range(0, length).mapToObj(i -> Character.toString((char) i)).collect(Collectors.toList());
    }

    /**
     * Creates an Integer list where each element is the index of the element.
     *
     * @param length The length of the list.
     * @return The generated list.
     */
    public static List<Integer> intList(int length) {
        return IntStream.range(0, length).boxed().collect(Collectors.toList());
    }

    /**
     * Asserts that the actual argument isi equal to at least one of the expected arguments.
     *
     * @param expected The expected arguments.
     * @param actual   The actual argument.
     * @param message  The message to display if the assertion fails.
     */
    public static void assertEqualsOneOf(List<Object> expected, Object actual, String message) {
        for (Object expectedElement : expected) {
            if (actual.equals(expectedElement)) {
                return;
            }
        }

        message += String.format(
            "\nExpected one of: [%s] but got: <%s>.",
            expected.stream().map(x -> String.format("<%s>", x)).collect(Collectors.joining(", ")),
            actual
        );
        throw new AssertionFailedError(message);
    }

    /**
     * Asserts that the actual argument isi equal to at least one of the expected arguments.
     *
     * @param expected The expected arguments.
     * @param actual   The actual argument.
     */
    public static void assertEqualsOneOf(List<Object> expected, Object actual) {
        assertEqualsOneOf(expected, actual, (String) null);
    }
}
