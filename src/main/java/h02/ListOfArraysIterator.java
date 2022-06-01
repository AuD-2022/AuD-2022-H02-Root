package h02;

import java.util.NoSuchElementException;

/**
 * Represents an iterator over a ListOfArrays object.
 *
 * @param <T> The generic type of the list that this iterator iterates on.
 */
public class ListOfArraysIterator<T> {
    /**
     * The list that this iterator iterates on.
     */
    public ListOfArraysItem<T> current;
    /**
     * The index of the current item.
     */
    public int currentIndex;

    /**
     * A constructor to construct a ListOfArraysIterator object.
     *
     * @param head The head of the list to iterate over.
     */
    public ListOfArraysIterator(ListOfArraysItem<T> head) {
        current = head;
        currentIndex = 0;
    }

    /**
     * Returns whether there is another element to be iterated on.
     *
     * @return True iff there is another element.
     */
    public boolean hasNext() {
        //Returns false iff the given sequence was null or the index has reached the maximum of the current element and the next element does not exist or there are no more elements
        return !(current == null || currentIndex == current.currentNumber && hasNoMoreElements(current.next));
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
        while (currentIndex == current.currentNumber) {
            current = current.next;
            currentIndex = 0;
        }
        return current.array[currentIndex++];
    }

    /**
     * Returns whether the list (from the given item onwards) has another element.
     *
     * @param item The item of the list to start searching from.
     * @return True iff there are no more elements.
     */
    public boolean hasNoMoreElements(ListOfArraysItem<T> item) {
        if (item == null)
            return true;
        if (item.currentNumber != 0)
            return false;
        return hasNoMoreElements(item.next);
    }
}
