package h02;

public class ListUtils {
    public static <T> ListOfArraysIteratorTutor<T> getIterator(ListOfArrays<T> list) {
        return new ListOfArraysIteratorTutor<>(list.head);
    }

    public static String[] toStringArray(ListOfArrays<String> list) {
        // iterate over list
        // list.head
        return null;
    }

    /**
     * returns a string representation of the given list
     *
     * @param list The list to iterate over.
     * @param <T>  The generic type of the list.
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
}
