package h02;

import java.util.List;
import java.util.stream.IntStream;

public final class TestConstants {
    public static final int ARRAY_LENGTH = 256;
    public static final int LONG_TEST_LENGTH = 513;
    public static final int NORMAL_LIST_LENGTH = 15;

    public static final String EXCEPTION_ON_EXECUTION = "an exception was thrown unexpectedly";
    public static final String LIST_DIFFERS = "actual list differs from expected list";
    public static final String DIFFERS_HEAD_NULL = LIST_DIFFERS + " (head is null)";
    public static final String DIFFERS_TAIL_NULL = LIST_DIFFERS + " (tail is null)";

    // List used for insertion
    public static final String[] CIRCLED_DIGITS = {"⓪", "➀", "➁", "➂", "➃", "➄", "➅", "➆", "➇", "➈"};
    public static final String[] FILLED_DIGITS = {"🄌", "➊", "➋", "➌", "➍", "➎", "➏", "➐", "➑", "➒"};

    // List used as the main list using circle digits
    public static List<String> mainList(int length) {
        return IntStream.range(0, length).mapToObj(String::valueOf).collect(java.util.stream.Collectors.toList());
//        return IntStream
//            .range(0, length)
//            .boxed()
//            .map(
//                x -> x
//                    .toString()
//                    .chars()
//                    .mapToObj(y -> CIRCLED_DIGITS[Integer.parseInt(Character.toString((char) y))])
//                    .collect(
//                        StringBuilder::new,
//                        StringBuilder::append,
//                        StringBuilder::append
//                    )
//                    .toString()
//            )
//            .collect(java.util.stream.Collectors.toList());
    }

    // List used for insertion using filled digits
    public static List<String> insertionList(int length) {
        return IntStream.range(0, length).mapToObj(x -> String.format("#%d", x)).collect(java.util.stream.Collectors.toList());
//        return IntStream
//            .range(0, length)
//            .boxed()
//            .map(
//                x -> x
//                    .toString()
//                    .chars()
//                    .mapToObj(y -> FILLED_DIGITS[y])
//                    .collect(
//                        StringBuilder::new,
//                        StringBuilder::append,
//                        StringBuilder::append
//                    )
//                    .toString()
//            )
//            .collect(java.util.stream.Collectors.toList());
    }
}
