package h02;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static h02.ListAssertions.assertListsEquals;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("UseBulkOperation")
public class ListRandomTest {

    // Because its over 9000.
    // Might crash you IDE.
    private static final int N = 9001;

    private static final int MAX_LIST_SIZE = 0xff * 10;

    // Consider using a seed while debugging.
    private static final Random random = new Random();

    private static Stream<Arguments> provideWithRandomList() {
        return Stream
            .generate(ListRandomTest::randomList)
            .map(Arguments::of)
            .limit(N);
    }

    private static <T> List<Integer> randomList() {
        var size = random.nextInt(MAX_LIST_SIZE);
        return Stream
            .generate(random::nextInt)
            .limit(size)
            .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideForContainsWhenInList() {
        return Stream
            .generate(ListRandomTest::randomList)
            .map(ListRandomTest::withContainingElement)
            .limit(N);
    }

    private static Arguments withContainingElement(List<Integer> list) {
        if (list.isEmpty()) {
            list.add(random.nextInt());
        }
        var index = random.nextInt(list.size());
        return Arguments.of(list, list.get(index));
    }

    private static Stream<Arguments> provideForContainsWhenInNotList() {
        return Stream
            .generate(ListRandomTest::randomList)
            .map(ListRandomTest::withNotContainingElement)
            .limit(N);
    }

    private static Arguments withNotContainingElement(List<Integer> list) {
        list.replaceAll(Math::abs);
        return Arguments.of(list, getToFind(list));
    }

    private static int getToFind(List<Integer> list) {
        if (list.isEmpty()) {
            return random.nextInt();
        }
        var index = random.nextInt(list.size());
        return -Math.abs(list.get(index));
    }

    public static Stream<Arguments> provideForAddAll() {
        return Stream
            .generate(() -> {
                var list = randomList();
                var index = random.nextInt(list.size()+1);
                return Arguments.of(list, randomList(), index);
            })
            .limit(N);
    }

    @ParameterizedTest
    @MethodSource("provideWithRandomList")
    void testThat_ConstructorWorksWithIterator(List<Integer> list) {
        var listToTest = makeListToTest(list);
        assertListsEquals(list, listToTest);
    }

    @ParameterizedTest
    @MethodSource("provideWithRandomList")
    void testThat_addWorksWithIterator(List<Integer> list) {
        var listToTest = makeListToTest();

        for (Integer integer : list) {
            listToTest.add(integer);
        }

        assertListsEquals(list, listToTest);
    }

    @ParameterizedTest
    @MethodSource("provideForAddAll")
    void testThat_addAllWorksWithIterator(List<Integer> list, List<Integer> toAdd, int ignore) {
        var listToTest = makeListToTest(list);

        list.addAll(toAdd);
        listToTest.addAll(toAdd);

        assertListsEquals(list, listToTest);
    }

    @ParameterizedTest
    @MethodSource("provideForAddAll")
    void testThat_addAllWithIndexWorksWithIterator(List<Integer> list, List<Integer> toAdd, int index) {
        var listToTest = makeListToTest(list);

        list.addAll(index, toAdd);
        listToTest.addAll(index, toAdd);

        assertListsEquals(list, listToTest);
    }

    @ParameterizedTest
    @MethodSource("provideForContainsWhenInList")
    void testThat_containsWorksWhenInList(List<Integer> list, int toFind) {
        var listToTest = makeListToTest();
        listToTest.addAll(list);
        assertTrue(listToTest.contains(toFind));
    }

    @ParameterizedTest
    @MethodSource("provideForContainsWhenInNotList")
    void testThat_containsWorksWhenInNotList(List<Integer> list, int toFind) {
        var listToTest = makeListToTest();
        listToTest.addAll(list);
        assertFalse(listToTest.contains(toFind));
    }

    @ParameterizedTest
    @MethodSource("provideWithRandomList")
    void testThat_iteratorWorks(List<Integer> list) {
        var listToTest = makeListToTest();
        listToTest.addAll(list);
        var iter = listToTest.iterator();

        for (Integer expected : list) {
            assertTrue(iter.hasNext());
            assertEquals(expected, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    private List<Integer> makeListToTest(List<Integer> list) {
        return new ListOfArraysWrapper<>(list.toArray(Integer[]::new));
    }

    private List<Integer> makeListToTest() {
        return makeListToTest(List.of());
    }
}
