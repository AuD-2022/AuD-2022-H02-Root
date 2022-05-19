package h02;

import org.junit.jupiter.api.Test;
import static h02.ListUtils.*;

public class H1TutorTests {
    @Test
    // Test for constructor of ListOfArraysTutor
    public void testListOfArrays() {
        var listArray = new String[] { "H", "E", "L", "L", "O" };
        ListOfArrays<String> list = new ListOfArrays<>(listArray);
        System.out.println(stringRepresentation(list));
    }
}
