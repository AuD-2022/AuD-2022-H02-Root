package h02;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import static h02.TestConstants.EXCEPTION_ON_EXECUTION;
import static org.junit.jupiter.api.Assertions.*;

public class ListOfArraysWrapper<T> implements List<T> {

    private final ListOfArrays<T> listOfArrays;

    @SafeVarargs
    public ListOfArraysWrapper(T... sequence) {
        listOfArrays = assertDoesNotThrow(() -> new ListOfArrays<>(sequence), EXCEPTION_ON_EXECUTION);
    }

    @Override
    public int size() {
        var size = 0;
        var iter = assertDoesNotThrow(() -> listOfArrays.iterator(), EXCEPTION_ON_EXECUTION);

        while (assertDoesNotThrow(() -> iter.hasNext(), EXCEPTION_ON_EXECUTION)) {
            assertDoesNotThrow(() -> iter.next(), EXCEPTION_ON_EXECUTION);
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return stream().anyMatch(e -> Objects.equals(e, o));
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new ListOfArraysIteratorWrapperTutor<>(listOfArrays);
    }

    @Override
    public Object[] toArray() {
        // noinspection SimplifyStreamApiCallChains
        return stream().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        var size = size();
        if (a.length < size)
            // noinspection unchecked
            a = (T1[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);

        Object[] result = a;

        var index = 0;
        for (T t : this) {
            result[index++] = t;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        assertDoesNotThrow(() -> listOfArrays.insert(List.of(t), size()), EXCEPTION_ON_EXECUTION);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends T> c) {
        return addAll(size(), c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> c) {
        // noinspection unchecked
        assertDoesNotThrow(() -> listOfArrays.insert((Collection<T>) c, index), EXCEPTION_ON_EXECUTION);
        return true;
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        var removed = false;

        for (Object o : c) {
            removed |= remove(o);
        }

        return removed;
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        var oldSize = size();

        var remaining = stream()
                .filter(c::contains)
                .toList();

        clear();
        addAll(remaining);

        return remaining.size() == oldSize;
    }

    @Override
    public void clear() {
        assertDoesNotThrow(() -> listOfArrays.extract(0, size()), EXCEPTION_ON_EXECUTION);
    }

    @Override
    public T get(int index) {
        var t = remove(index);
        add(index, t);
        return t;
    }

    @Override
    public T set(int index, T element) {
        var t = remove(index);
        add(index, element);
        return t;
    }

    @Override
    public void add(int index, T element) {
        assertDoesNotThrow(() -> listOfArrays.insert(List.of(element), index), EXCEPTION_ON_EXECUTION);
    }

    @Override
    public T remove(int index) {
        var a = assertDoesNotThrow(() -> listOfArrays.extract(index, index + 1), EXCEPTION_ON_EXECUTION);
        return assertDoesNotThrow(() -> a.iterator(), EXCEPTION_ON_EXECUTION).next();
    }

    @Override
    public int indexOf(Object o) {
        var index = 0;
        var iter = assertDoesNotThrow(() -> listOfArrays.iterator(), EXCEPTION_ON_EXECUTION);

        while (assertDoesNotThrow(() -> iter.hasNext(), EXCEPTION_ON_EXECUTION)) {
            if (Objects.equals(o, assertDoesNotThrow(() -> iter.next(), EXCEPTION_ON_EXECUTION))) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        var lastIndex = -1;
        var index = 0;
        var iter = assertDoesNotThrow(() -> listOfArrays.iterator(), EXCEPTION_ON_EXECUTION);

        while (assertDoesNotThrow(() -> iter.hasNext(), EXCEPTION_ON_EXECUTION)) {
            if (Objects.equals(o, assertDoesNotThrow(() -> iter.next(), EXCEPTION_ON_EXECUTION))) {
                lastIndex = index;
            }
            index++;
        }

        return lastIndex;
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
