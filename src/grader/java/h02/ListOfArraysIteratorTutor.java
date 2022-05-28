package h02;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Represents an iterator over a ListOfArrays object.
 *
 * @param <T> The generic type of the list that this iterator iterates on.
 */
public class ListOfArraysIteratorTutor<T> {
    public ListOfArraysItem<T> current;
    public int currentElement;
    public int currentIndex;

    public HashSet<ListOfArraysItem<T>> visited = new HashSet<>();

    /**
     * A constructor to construct a ListOfArraysIteratorTutor object.
     *
     * @param head The head of the list to iterate over.
     */
    public ListOfArraysIteratorTutor(ListOfArraysItem<T> head) {
        current = head;
        currentIndex = 0;
        currentElement = 0;
    }

    /**
     * Returns whether there is another element to be iterated on.
     *
     * @return True iff there is another element.
     */
    public boolean hasNext() {
        //Returns false iff the given sequence was null or the index has reached the maximum of the current element and the next element does not exist or there are no more elements
        return !(current == null || (!hasMoreEntries(current.array, currentIndex) && hasNoMoreElements(current, new HashSet<>(), currentIndex)));
    }

    /**
     * Returns the next element of this iterator and moves the iterator one element forward.
     *
     * @return The next element.
     * @throws NoSuchElementException If there is no next element.
     */
    public T next() throws NoSuchElementException {
        //No more elements?
        if (!hasNext())
            throw new NoSuchElementException();
        //Does the iterator have to move forward?
        while (!hasMoreEntries(current.array, currentIndex)) {
            assertFalse(visited.contains(current), "List contains duplicate item. Aborting to prevent infinite loop.");
            visited.add(current);
            current = current.next;
            currentIndex = 0;
        }
        assertFalse(visited.contains(current), "List contains duplicate item. Aborting to prevent infinite loop.");
        //Return the next element
        while (current.array[currentIndex] == null) {
            currentIndex++;
        }
        return current.array[currentIndex++];
    }

    /**
     * Returns whether the given array contains further non-null elements.
     *
     * @param array The array to check.
     * @param index The index to start checking from.
     * @return True if the given array contains further non-null elements.
     */
    public boolean hasMoreEntries(T[] array, int index) {
        if (index >= array.length) {
            return false;
        }
        for (int i = index; i < array.length; i++) {
            if (array[i] != null) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns whether the list (from the given item onwards) has another element.
     *
     * @param item The item of the list to start searching from.
     * @return True iff there are no more elements.
     */
    public boolean hasNoMoreElements(ListOfArraysItem<T> item, HashSet<ListOfArraysItem<T>> visitedRecursive, int index) {
        assertFalse(visited.contains(item) || visitedRecursive.contains(item), "List contains duplicate item. Aborting to prevent infinite loop.");
        if (item == null)
            return true;
        if (hasMoreEntries(item.array, index)) {
            return false;
        }
        visitedRecursive.add(item);
        return hasNoMoreElements(item.next, visitedRecursive, 0);
    }
}
