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
                        .shortDescription("Die Liste bleibt unverändert, wenn eine leere Collection übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertEmptyList")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Eine entsprechende Exception wird geworfen, wenn der übergebene Index ungültig ist.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertExceptions")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Einfügen ans Ende der Liste (ohne dass Elemente verschoben werden müssen) funktioniert korrekt mit wenigen Elementen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertAtEnd")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Einfügen ans Ende der Liste (ohne dass Elemente verschoben werden müssen) funktioniert korrekt mit > 256 Elementen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertAtEndWithMoreThan256Elements")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Einfügen in der Mitte der Liste funktioniert korrekt, wenn keine neuen Arrays erzeugt werden müssen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertInMiddle")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen in der Mitte der Liste funktioniert korrekt, wenn neue Arrays erzeugt werden müssen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertInMiddleWithMoreThan256Elements")
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
                .shortDescription("H4: Methode zum Extrahieren ganzer Blöcke")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Entsprechende Exceptions werden geworfen, wenn i oder j ungültig sind.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithEmptyList")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Löschen eines einzelnen Elements funktioniert korrekt am Ende der Liste.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithOneElement")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Löschen mehrerer Elemente am Ende der Liste funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithMultipleElements")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Löschen aller Elemente der Liste funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithAllElements")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Löschen in der Mitte der Liste funktioniert korrekt mit kleinen Listen.")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H4TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Löschen in der Mitte der Liste funktioniert korrekt mit großen Listen. (>256 Elemente)")
                        .grader(
                            Grader.testAwareBuilder()
//                                .requirePass(
//                                    JUnitTestRef.ofMethod(
//                                        () -> H4TutorTests.class.getDeclaredMethod("")
//                                        )
//                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Rückgabeliste ist korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractReturnValue")
                                    )
                                )
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Methode extract() ist vollständig korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractCombined")
                                    )
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
                                .shortDescription("Die Klasse ElementWithIndex ist vollständig korrekt.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testElementWithIndex")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Eine Exception wird geworfen, wenn ein Element mit einem ungültigen Index eingefügt werden soll.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithNegativeIndex")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Eine Exception wird geworfen, wenn so viele Elemente hintereinander eingefügt werden sollen, dass das Array zum Zwischenspeichern überfüllt ist.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithTooManyElements")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Das einfügen eines einzelnen Elements am Ende funktioniert.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertAtEnd")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Das einfügen eines einzelnen Elements am Anfang funktioniert.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertAtBeginning")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Das einfügen eines einzelnen Elements in der Mitte funktioniert.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertInMiddle")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Das einfügen eines zusammenhängenden Blocks funktioniert korrekt.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsBlock")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Das Einfügen mehrerer verstreuter Elemente funktioniert korrekt.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsSpread")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Die Abbruchbedingungen funktionieren korrekt.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.ofMethod(
                                                () -> H5TutorTests.class.getDeclaredMethod("testInsertStopConditions")
                                            )
                                        )
                                        .build()
                                )
                                .build(),
                            Criterion.builder()
                                .shortDescription("Die Methode insert() ist vollständig korrekt.")
                                .grader(
                                    Grader.testAwareBuilder()
                                        .requirePass(
                                            JUnitTestRef.and(
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testElementWithIndex")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithNegativeIndex")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithTooManyElements")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertAtEnd")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertAtBeginning")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertInMiddle")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsBlock")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsSpread")
                                                ),
                                                JUnitTestRef.ofMethod(
                                                    () -> H5TutorTests.class.getDeclaredMethod("testInsertStopConditions")
                                                )
                                            )
                                        )
                                        .build()
                                )
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
