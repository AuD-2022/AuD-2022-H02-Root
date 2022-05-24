package h02;

import org.sourcegrade.jagr.api.rubric.*;

@RubricForSubmission("h02")
public class H02_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H02")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H1: Liste von Arrays")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Der Konstruktor der Klasse ListOfArrays erzeugt eine leere Liste, wenn ein leeres Array oder null als Argument übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.and(
                                        JUnitTestRef.ofMethod(
                                            () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_Null")
                                        ),
                                        JUnitTestRef.ofMethod(
                                            () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_EmptyList")
                                        )
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Erzeugte Liste enthält die gewünschten Einträge, wenn ein Array mit einem Element übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_SingleElementList")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Erzeugte Liste enthält die gewünschten Einträge, wenn ein Array mit mehr als 256 Elementen übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_LongList")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die erzeugte Liste enthält die selben Elemente wie das übergebene Array.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_SameElements")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Liste wird vollständig korrekt gebildet.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_CorrectList")
                                    )
                                )
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H2: Iterator-Klasse")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Die Methode hasNext() funktioniert korrekt (für kleine Listen).")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H2TutorTests.class.getDeclaredMethod("test_hasNext_emptyList_returnsFalse")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Methode next() funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H2TutorTests.class.getDeclaredMethod("test_next_emptyList_throwsNoSuchElementException")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Klasse ListOfArraysIterator sowie die Methode iterator() der Klasse ListOfArrays sind vollständig korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.and(
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod("test_hasNext_emptyList_returnsFalse")
                                        ),
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod("test_next_emptyList_throwsNoSuchElementException")
                                        ),
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod("test_iterator_combined")
                                        )
                                    )
                                )
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H3: Methode zum Einfügen einer Sequenz im Block in eine Arrayliste")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Punkt 1")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 2")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 3")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 4")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 5")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 6")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H4: Methode zum Extrahieren ganzer Blöcke")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Punkt 1")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 2")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 3")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 4")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 5")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 6")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 7")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 8")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 9")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 10")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H5: Methode zum Einfügen mehrerer einzelner neuer Elemente")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Punkt 1")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 2")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 3")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 4")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 5")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 6")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 7")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Punkt 8")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H1TutorTests.class.getDeclaredMethod(""))
//                                )
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
