package h02;

import java.util.Collection;
import java.util.Iterator;

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
    public static int ARRAY_LENGTH;

    /**
     * The head of this list.
     */
    public ListOfArraysItem<T> head;

    /**
     * The tail of this list.
     */
    public ListOfArraysItem<T> tail;

    static {
        ARRAY_LENGTH = 256;
    }

    /**
     * Constructs a list of ListOfArrayItem objects that represents the elements given in sequence.
     *
     * @param sequence The elements to be added to the list.
     */
    @SuppressWarnings("unchecked")
    public ListOfArrays(T[] sequence) {
        //Empty sequence or null -> Only null references
        if (sequence == null || sequence.length == 0)
            return;
        //Input elements
        for (int i = 0; i < sequence.length; i++) {
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
     * @param collection The collection to add.
     * @param i          The index at which the collection should be inserted.
     * @throws IndexOutOfBoundsException If the given index is not within the bounds of this list.
     */
    @SuppressWarnings("unchecked")
    public void insert(Collection<T> collection, int i) throws IndexOutOfBoundsException {
        //Invalid i?
        if (i < 0 || i > getTotalLength(head))
            throw new IndexOutOfBoundsException(i);
        //Special case: Empty sequence -> create head/tail as new item
        if (head == null) {
            head = tail = new ListOfArraysItem<>();
            head.array = (T[]) new Object[ARRAY_LENGTH];
        }
        //Find item and array index to insert to
        ListOfArraysItem<T> p = head;
        while (i > p.currentNumber) {
            i -= p.currentNumber;
            p = p.next;
        }
        Iterator<T> iterator = collection.iterator();
        //p is now the item to insert into and i is the index to insert into (i is the INDEX of the first element that has to be moved -> i = 1 implies second element in array has to be moved)
        //Special case: Index at start of an array -> Add into both before creating new ones -> Add into current item (p) then advance to second array
        if (i == p.currentNumber) {
            //Fill p until full/end of collection
            while (p.currentNumber != ARRAY_LENGTH && iterator.hasNext()) {
                p.array[p.currentNumber++] = iterator.next();
            }
            if (!iterator.hasNext()) return;
            //Special case: Item is null -> Add new item to insert to later on
            if (p.next == null) {
                p.next = tail = new ListOfArraysItem<>();
                p.next.array = (T[]) new Object[ARRAY_LENGTH];
            }
            //Go to next item
            p = p.next;
            i = 0;
        }
        T[] removed = (T[]) new Object[p.currentNumber - i];
        int rindex = -1;
        //Remove elements of this array to be removed
        while (i < p.currentNumber) {
            removed[++rindex] = p.array[i++];
        }
        p.currentNumber -= removed.length;
        int currentRIndex = 0;
        //Add new items between p and p.next (if p.next != null) until all items have been added
        while (iterator.hasNext() || currentRIndex <= rindex) {
            //If p is full -> add new item between current p and p.next
            if (p.currentNumber == ARRAY_LENGTH) {
                ListOfArraysItem<T> tmp = p.next;
                p.next = new ListOfArraysItem<>();
                p.next.array = (T[]) new Object[ARRAY_LENGTH];
                p.next.next = tmp;
                p = p.next;
            }
            //Fill p until full / end of collection or no more removed elements -> nothing more to add
            while (p.currentNumber != ARRAY_LENGTH && (iterator.hasNext() || currentRIndex <= rindex)) {
                //Add element of collection? If not -> Add element of removed elements
                p.array[p.currentNumber++] =
                    iterator.hasNext() ? iterator.next() : removed[currentRIndex++];
            }
        }
    }

    /**
     * Inserts the elements of given Iterator each with their given offset from the last inserted element (or the first element) into this list.
     *
     * @param iterator The Iterator over the elements (and their offsets) that should be added.
     * @throws IndexOutOfBoundsException If an offset is negative.
     */
    @SuppressWarnings("unchecked")
    public void insert(Iterator<ElementWithIndex<T>> iterator) throws IndexOutOfBoundsException {
        //Start at first element in the sequence
        ListOfArraysItem<T> currentItem = head;
        int currentIndex = 0;
        //List to save removed items that need to be put back in later on
        T[] removed = (T[]) new Object[ARRAY_LENGTH];
        boolean addedSomething = false;
        //Go through all elements of the iterator until there is no next element or elements index exceeds length of sequence
        while (iterator.hasNext()) {
            ElementWithIndex<T> currentElement = iterator.next();
            int offset = currentElement.getIndex();
            //Negative offset?
            if (offset < 0) throw new IndexOutOfBoundsException(offset);
            //Move forward to desired index and add possible removed elements
            while (offset > 0) {
                boolean addRemoved = false;
                //Insert removed item and save item currently at this position
                if (removed[0] != null) {
                    if (currentItem.array[currentIndex] != null) addToArray(removed, currentItem.array[currentIndex]);
                    else currentItem.currentNumber++;
                    currentItem.array[currentIndex] = getFirst(removed);
                    addRemoved = true;
                }
                //Go to next item?
                if (currentIndex >= currentItem.currentNumber - 1 &&
                    ((removed[0] == null && !addRemoved) || currentItem.currentNumber == ARRAY_LENGTH)
                    && (addedSomething || offset != 1)) {
                    //Need to add new item?
                    if (currentItem.next == null) {
                        //No more removed elements? -> Offset out of bounds
                        if (removed[0] == null) return;
                        currentItem.next = new ListOfArraysItem<>();
                        currentItem.next.array = (T[]) new Object[ARRAY_LENGTH];
                    }
                    currentItem = currentItem.next;
                    currentIndex = 0;
                }
                currentIndex++;
                offset--;
            }
            //Special case: List is empty
            if (currentItem == null) {
                currentItem = head = tail = new ListOfArraysItem<>();
                currentItem.array = (T[]) new Object[ARRAY_LENGTH];
            }
            //Add the item at current index
            if (currentItem.array[currentIndex] != null) addToArray(removed, currentItem.array[currentIndex]);
            else currentItem.currentNumber++;
            currentItem.array[currentIndex] = currentElement.getElement();
            //Increment index/Go to next item/Create new item if necessary
            if (currentIndex == ARRAY_LENGTH - 1) {
                if (currentItem.next == null) {
                    currentItem.next = tail = new ListOfArraysItem<>();
                    currentItem.next.array = (T[]) new Object[ARRAY_LENGTH];
                }
                currentItem = currentItem.next;
                currentIndex = 0;
            } else currentIndex++;
            addedSomething = true;
        }
        //Add remaining elements that were removed earlier
        while (removed[0] != null) {
            if (currentItem.array[currentIndex] != null) addToArray(removed, currentItem.array[currentIndex]);
            else currentItem.currentNumber++;
            currentItem.array[currentIndex] = getFirst(removed);
            //Increment index/Go to next item/Create new item if necessary
            if (currentIndex == ARRAY_LENGTH - 1) {
                if (currentItem.next == null) {
                    currentItem.next = tail = new ListOfArraysItem<>();
                    currentItem.next.array = (T[]) new Object[ARRAY_LENGTH];
                }
                currentItem = currentItem.next;
                currentIndex = 0;
            } else currentIndex++;
        }
    }

    /**
     * Extracts a block of elements of this list. The block is defined by the boundary indices i and j (both included in the block that will be extracted) as a ListOfArrays object.
     * This will delete the extracted elements from the list.
     *
     * @param i The lower boundary index.
     * @param j The higher boundary index.
     * @return The extracted elements as a ListOfArrays object.
     * @throws IndexOutOfBoundsException If i is greater than j (the lower bound is greater than the upper bound), 0 is greater than i or j is greater than the total length of this list.
     */
    @SuppressWarnings("unchecked")
    public ListOfArrays<T> extract(int i, int j) throws IndexOutOfBoundsException {
        //Exceptions for i and j
        if (i < 0)
            throw new IndexOutOfBoundsException(i);
        if (j > getTotalLength(head) - 1)
            throw new IndexOutOfBoundsException(j);
        if (i > j)
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
        while (resultIndex < toResult.length) {
            //Jump to next item? Remove gaps to make list consistent again
            if (totalIndex == p.currentNumber) {
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
        //Remove empty items of the list
        removeEmptyItems();
        //Return new ListOfArrays - made with constructor of the composed array
        return new ListOfArrays<>(toResult);
    }

    /**
     * Returns the total number of elements in this list.
     *
     * @param item The current item.
     * @return The total number of elements in this list.
     */
    public int getTotalLength(ListOfArraysItem<T> item) {
        if (item == null)
            return 0;
        return item.currentNumber + getTotalLength(item.next);
    }

    /**
     * Finds gaps in the given list (null elements with non null elements somewhere behind the gap) and closes them by moving the elements to the front.
     *
     * @param item The list to fix.
     */
    public void removeArrayGaps(ListOfArraysItem<T> item) {
        for (int i = 0; i < item.array.length; i++) {
            if (item.array[i] == null) {
                //Get next non null element
                int indexOfNext = -1;
                for (int j = i; j < item.array.length; j++) {
                    if (item.array[j] != null) {
                        indexOfNext = j;
                        break;
                    }
                }
                //Was no next element found?
                if (indexOfNext == -1)
                    return;
                //Move the found element
                item.array[i] = item.array[indexOfNext];
                item.array[indexOfNext] = null;
            }
        }
    }

    /**
     * Removes empty items from the list.
     */
    public void removeEmptyItems() {
        ListOfArraysItem<T> p = head;
        while (p != null) {
            //Does next item exist and have 0 elements? -> Remove it
            if (p.next != null && p.next.currentNumber == 0) {
                p.next = p.next.next;
                continue;
            }
            p = p.next;
        }
        //Redo head
        while (head != null && head.currentNumber == 0) {
            head = head.next;
        }
        //Redo tail
        tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
    }

    /**
     * Adds the given element into the first empty space (null) of the given array.
     *
     * @param array The array to insert into.
     * @param toAdd The element to be added.
     * @throws IndexOutOfBoundsException If the array is already full.
     */
    public void addToArray(T[] array, T toAdd) throws IndexOutOfBoundsException {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
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
     * @return The first element.
     */
    public T getFirst(T[] array) {
        //Element to be returned
        T tmp = array[0];
        for (int i = 0; i < array.length; i++) {
            //Beginning of null-block in array reached? -> Does not matter anymore.
            if (array[i] == null) {
                return tmp;
            }
            //End of array reached? -> Replace with null instead of next element (which does not exist in this case).
            if (i == array.length - 1) {
                array[i] = null;
                return tmp;
            }
            //Standard: Replace current element with next element -> Make all elements move by 1 to the beginning of the array.
            array[i] = array[i + 1];
        }
        return tmp;
    }
}

