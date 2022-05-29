package h02;

public final class TestConstants {
    public static final int ARRAY_LENGTH = 256;
    public static final int LONG_TEST_LENGTH = 513;
    public static final int NORMAL_LIST_LENGTH = 15;

    public static final String EXCEPTION_ON_EXECUTION = "an exception was thrown unexpectedly";
    public static final String LIST_DIFFERS = "actual list differs from expected list";
    public static final String DIFFERS_HEAD_NULL = LIST_DIFFERS + " (head is null)";
    public static final String DIFFERS_TAIL_NULL = LIST_DIFFERS + " (tail is null)";


    // List used as the main list
//    public static List<String> testList(int length){
//       return IntStream.range(0,length).boxed().map(x -> x.toString().chars().map(y -> {
//           switch (y){
//               case 0:
//                   return "🄋";
//               case 1:
//                   return "➀";
//           }
//       }))
//    }
    // List used for insertion
}
