package h02;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A list of ListOfArraysItem objects.
 * Contains the length of the arrays of this list and a reference to the first and last item (or null as an empty list).
 *
 * @param <T> The generic type of this list
 */
public class ListOfArrays<T> {
    /**
     * The length of the arrays of the items of this list.
     */
    private static final int ARRAY_LENGTH;

    /**
     * The head of this list.
     */
    private ListOfArraysItem<T> head;

    /**
     * The tail of this list.
     */
    private ListOfArraysItem<T> tail;

    static {
        ARRAY_LENGTH = 256;
    }

    /**
     * Constructs a list of ListOfArrayItem objects that represents the elements given in sequence.
     *
     * @param sequence The elements to be added to the list.
     */
    public ListOfArrays(T[] sequence) {
        //Empty sequence or null -> Only null references
        if(sequence == null || sequence.length == 0)
            return;
        //Input elements
        for(int i = 0; i < sequence.length; i++) {
            //New ListOfArraysItem object necessary?
            if(i % ARRAY_LENGTH == 0) {
                //First ever element?
                if(head == null)
                    head = tail = new ListOfArraysItem<>();
                else {
                    tail.next = new ListOfArraysItem<>();
                    tail = tail.next;
                }
                tail.array = (T[]) new Object[ARRAY_LENGTH];
                tail.currentNumber = 0;
            }
            //Add element and increment currentNumber
            tail.array[tail.currentNumber++] = sequence[i];
        }
    }

    /**
     * Returns an iterator over this list.
     *
     * @return The iterator of type ListOfArraysIterator.
     */
    public ListOfArraysIterator<T> iterator() {
        return new ListOfArraysIterator<>(head);
    }

    /**
     * Inserts a given collection into this list at index i
     * (elements in front of index i will stay and elements at index i and after will be pushed behind the added collection).
     *
     * @param collection                    The collection to add.
     * @param i                             The index at which the collection should be inserted.
     * @throws IndexOutOfBoundsException    If the given index is not within the bounds of this list.
     */
    public void insert(Collection<T> collection, int i) throws IndexOutOfBoundsException {
        //Invalid i?
        if(i < 0 || i > getTotalLength(head))
            throw new IndexOutOfBoundsException(i);
        //Special case: Empty sequence -> create head/tail as new item
        if(head == null) {
            head = new ListOfArraysItem<>();
            head.array = (T[]) new Object[ARRAY_LENGTH];
            tail = head;
        }
        //Find item and array index to insert to
        ListOfArraysItem<T> p = head;
        while(i > p.currentNumber) {
            i -= p.currentNumber;
            p = p.next;
        }
        Iterator<T> iterator = collection.iterator();
        //p is now the item to insert into and i is the index to insert into (i is the INDEX of the first element that has to be moved -> i = 1 implies second element in array has to be moved)
        //Special case: Index at start of an array -> Add into both before creating new ones -> Add into current item (p) then advance to second array
        if(i == p.currentNumber) {
            //Fill p until full/end of collection
            while(p.currentNumber != ARRAY_LENGTH && iterator.hasNext()) {
                p.array[p.currentNumber++] = iterator.next();
            }
            if(!iterator.hasNext()) return;
            //Go to next item
            p = p.next;
            i = 0;
            //Special case: Item is null -> Add new item to insert to later on
            if(p == null) {
                p = new ListOfArraysItem<>();
                p.array = (T[]) new Object[ARRAY_LENGTH];
                tail = p;
            }
        }
        T[] removed = (T[]) new Object[p.currentNumber - i];
        int rindex = 0;
        //Remove elements of this array to be removed
        while(i < p.currentNumber) {
            removed[rindex++] = p.array[i++];
        }
        p.currentNumber -= removed.length;
        //Add new items between p and p.next (if p.next != null) until all items have been added
        while(iterator.hasNext() || rindex >= 0) {
            //If p is full -> add new item between current p and p.next
            if(p.currentNumber == ARRAY_LENGTH) {
                ListOfArraysItem<T> tmp = p.next;
                p.next = new ListOfArraysItem<>();
                p.next.array = (T[]) new Object[ARRAY_LENGTH];
                p.next.next = tmp;
                p = p.next;
            }
            //Fill p until full / end of collection or no more removed elements -> nothing more to add
            while (p.currentNumber != ARRAY_LENGTH && (iterator.hasNext() || rindex >= 0)) {
                //Add element of collection? If not -> Add element of removed elements
                p.array[p.currentNumber++] = iterator.hasNext() ? iterator.next() : removed[rindex--];
            }
        }

        /*
        //Special case: next item is null -> Create the item as tail (could also be head due to empty sequence)
        if(p.next == null) {
            p.next = new ListOfArraysItem<>();
            p.next.array = (T[]) new Object[ARRAY_LENGTH];
            p = p.next;
            tail = p.next;
        }
        */
    }

    /**
     * Inserts the elements of given Iterator each with their given offset from the last inserted element (or the first element) into this list.
     *
     * @param iterator                      The Iterator over the elements (and their offsets) that should be added.
     * @throws IndexOutOfBoundsException    If an offset is negative.
     */
    public void insert(Iterator<ElementWithIndex<T>> iterator) throws IndexOutOfBoundsException {
        //Start at first element in the sequence
        ListOfArraysItem<T> currentItem = head;
        int currentIndex = 0;
        //List to save evicted items that need to be put back in
        T[] removed = (T[]) new Object[ARRAY_LENGTH];
        //Go through all elements of the iterator until there is no next element or elements index exceeds length of sequence
        while(iterator.hasNext()) {
            ElementWithIndex<T> currentElement = iterator.next();
            int offset = currentElement.getIndex();
            //Negative offset?
            if(offset < 0)
                throw new IndexOutOfBoundsException(offset);
            //Go to the next element / item or reach the end of the sequence
            while(offset > 0) {
                //If there are removed elements that need to be added: Remove current element and insert first removed element to fix sequence again
                if(removed[0] != null) {
                    //Only add non-null element
                    if(currentItem.array[currentIndex] != null)
                        addToArray(removed, currentItem.array[currentIndex]);
                    currentItem.array[currentIndex] = getFirst(removed);
                }
                //end of current item?
                if(currentItem.currentNumber <= currentIndex + 1) {
                    //Is there possible space in this item left? -> Add removed element at end of current item or into new item
                    if(currentItem.currentNumber < ARRAY_LENGTH) {
                        //Removed elements to add? If not, simply jump to next item
                        if(removed[0] != null)
                            currentItem.array[currentItem.currentNumber++] = getFirst(removed);
                        else {
                            currentItem = currentItem.next;
                            currentIndex = 0;
                        }
                    } else {
                        //End of sequence reached?
                        if (currentItem.next == null) {
                            //If there is another element that was removed: Add it to the sequence and move on to it
                            if (removed[0] != null) {
                                currentItem.next = new ListOfArraysItem<>();
                                currentItem.next.currentNumber = 1;
                                currentItem.next.array = (T[]) new Object[ARRAY_LENGTH];
                                currentItem.next.array[0] = getFirst(removed);
                            } else
                                //Index of current element would exceed list -> End method
                                return;
                        }
                        currentItem = currentItem.next;
                        currentIndex = 0;
                    }
                } else
                    currentIndex++;
                offset--;
            }
            //New element should be inserted at current index of current item -> Remove current element at given position and add new element
            addToArray(removed, currentItem.array[currentIndex]);
            currentItem.array[currentIndex] = currentElement.getElement();
            currentItem.currentNumber++;
        }
        //Add all remaining removed elements to complete the insert-operation
        while(removed[0] != null) {
            //Does current item have free space?
            if(currentItem.currentNumber < ARRAY_LENGTH)
                currentItem.array[currentItem.currentNumber++] = getFirst(removed);
            else {
                //insert new item and add element to it
                ListOfArraysItem<T> tmp = currentItem.next;
                currentItem.next = new ListOfArraysItem<>();
                currentItem.next.currentNumber = 1;
                currentItem.next.array = (T[]) new Object[ARRAY_LENGTH];
                currentItem.next.array[0] = getFirst(removed);
                currentItem = currentItem.next;
                currentItem.next = tmp;
            }
        }
    }

    /**
     * Extracts a block of elements of this list. The block is defined by the boundary indices i and j (both included in the block that will be extracted) as a ListOfArrays object.
     * This will delete the extracted elements from the list.
     *
     * @param i                             The lower boundary index.
     * @param j                             The higher boundary index.
     * @return                              The extracted elements as a ListOfArrays object.
     * @throws IndexOutOfBoundsException    If i is greater than j (the lower bound is greater than the upper bound), 0 is greater than i or j is greater than the total length of this list.
     */
    public ListOfArrays<T> extract(int i, int j) throws IndexOutOfBoundsException {
        //Exceptions for i and j
        if(i < 0)
            throw new IndexOutOfBoundsException(i);
        if(j > getTotalLength(head) - 1)
            throw new IndexOutOfBoundsException(j);
        if(i > j)
            throw new IndexOutOfBoundsException(i + " is greater than " + j);
        //Array that will later on be given to the constructor to produce the result
        T[] toResult = (T[]) new Object[j - i + 1];
        int resultIndex = 0;
        int totalIndex = 0;
        //Go through elements in this list and count their index until reaching index i
        ListOfArraysItem<T> p = head;
        while (totalIndex + p.currentNumber < i + 1) {
            totalIndex += p.currentNumber;
            p = p.next;
        }
        //Add elements from the element at index i and onwards until reaching index j
        totalIndex = i - totalIndex;
        while(resultIndex < toResult.length) {
            //Jump to next item? Remove gaps to make list consistent again
            if(totalIndex == p.currentNumber) {
                removeArrayGaps(p);
                p = p.next;
                totalIndex = 0;
            }
            toResult[resultIndex++] = p.array[totalIndex];
            p.currentNumber--;
            p.array[totalIndex++] = null;
        }
        //Remove gaps to make list consistent again
        removeArrayGaps(p);
        //Return new ListOfArrays - made with constructor of the composed array
        return new ListOfArrays(toResult);
    }

    /**
     * Returns the total number of elements in this list.
     *
     * @param item  The current item.
     * @return      The total number of elements in this list.
     */
    private int getTotalLength(ListOfArraysItem<T> item) {
        if(item == null)
            return 0;
        return item.currentNumber + getTotalLength(item.next);
    }

    /**
     * Returns the length of given collection.
     *
     * @param collection    The collection.
     * @return              The length of the collection.
     */
    private int getTotalLengthOfSequence(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        int result = 0;
        while(it.hasNext()) {
            result++;
            it.next();
        }
        return result;
    }

    /**
     * Moves the elements of given item and at given start index by given distance (further up in the array).
     * The array is expected to be big enough to hold the change and have enough free spaces further up the array.
     *
     * @param item          The item that will be changed.
     * @param startIndex    That starting index where changes will begin.
     * @param distance      The distance by which the elements will be moved.
     *
     * @return              The elements that were overwritten
     */
    private T[] moveArrayElementsBy(ListOfArraysItem<T> item, int startIndex, int distance) {
        T[] result = (T[]) new Object[distance];
        for(int i = 0; i < distance; i++) {
            result[i] = item.array[startIndex + i + distance];
            item.array[startIndex + i + distance] = item.array[startIndex + i];
        }
        return result;
    }

    /**
     * Moves all elements of a given item by a given distance further up the array.
     * The array is expected to be big enough to hold the change and have enough free spaces further up the array.
     *
     * @param item      The item that will be changed.
     * @param distance  The distance by which the elements will be pushed.
     */
    private void pushArrayElementsBy(ListOfArraysItem<T> item, int distance) {
        for(int i = 0; i < distance; i++)
            for(int x = item.array.length - 1; x > 0; x++)
                item.array[x] = item.array[x-1];
    }

    /**
     * Finds gaps in the given list (null elements with non null elements somewhere behind the gap) and closes them by moving the elements to the front.
     *
     * @param item The list to fix.
     */
    private void removeArrayGaps(ListOfArraysItem<T> item) {
        for(int i = 0; i < item.array.length; i++) {
            if(item.array[i] == null) {
                //Get next non null element
                int indexOfNext = -1;
                for(int j = i; j < item.array.length; j++) {
                    if(item.array[j] != null) {
                        indexOfNext = j;
                        break;
                    }
                }
                //Was no next element found?
                if(indexOfNext == -1)
                    return;
                //Move the found element
                item.array[i] = item.array[indexOfNext];
                item.array[indexOfNext] = null;
            }
        }
    }

    /**
     * Adds the given element into the first empty space (null) of the given array.
     *
     * @param array                         The array to insert into.
     * @param toAdd                         The element to be added.
     * @throws IndexOutOfBoundsException    If the array is already full.
     */
    private void addToArray(T[] array, T toAdd) throws IndexOutOfBoundsException {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                array[i] = toAdd;
                return;
            }
        }
        //Array full
        throw new IndexOutOfBoundsException("array could not hold elements to be moved");
    }

    /**
     * Returns the first element of the given array and moves all other elements in the array by 1 towards index 0.
     *
     * @param array The array to change and use.
     * @return      The first element.
     */
    private T getFirst(T[] array) {
        //Element to be returned
        T tmp = array[0];
        for(int i = 0; i < array.length; i++) {
            //Beginning of null-block in array reached? -> Does not matter anymore.
            if(array[i] == null) {
                return tmp;
            }
            //End of array reached? -> Replace with null instead of next element (which does not exist in this case).
            if(i == array.length - 1) {
                array[i] = null;
                return tmp;
            }
            //Standard: Replace current element with next element -> Make all elements move by 1 to the beginning of the array.
            array[i] = array[i + 1];
        }
        return tmp;
    }
}

