package h02;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class ListAssertions {

    private ListAssertions() {};

    public static <T> void assertListsEquals(List<T> expected, List<T> actual) {
        assertEquals(
            expected.size(),
            actual.size(),
            "Lists differ in size");

        assertIterableEquals(expected, actual,
            "List elements differ");
    }
}
