package h02;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricForSubmission;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.sourcegrade.jagr.api.testing.RubricConfiguration;

@RubricForSubmission("h02")
public class H02_RubricProvider implements RubricProvider {

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H02 | Liste mit Hilfe von Arrays")
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H1 | Liste von Arrays")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription(
                            "Der Konstruktor der Klasse ListOfArrays erzeugt eine leere Liste, wenn ein leeres Array oder [[[null]]] als Argument übergeben wird.")
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
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Die erzeugte Liste enthält die gewünschten Einträge, wenn ein Array mit einem Element übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod(
                                            "testListOfArraysConstructor_SingleElementList")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Die erzeugte Liste enthält die gewünschten Einträge, wenn ein Array mit mehr als 256 Elementen übergeben wird.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_LongList")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
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
                                .pointsFailedMin()
                                .pointsPassedMax()
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
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H2 | Iterator-Klasse")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Die Methode [[[hasNext]]] funktioniert für Listen mit weniger gleich [[[ARRAY_SIZE]]] Elementen korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H2TutorTests.class.getDeclaredMethod("test_hasNext_emptyList_returnsFalse")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Methode [[[next]]] funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H2TutorTests.class.getDeclaredMethod(
                                            "test_next_emptyList_throwsNoSuchElementException")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Die Klasse [[[ListOfArraysIterator]]] sowie die Methode [[[iterator]]] der Klasse ListOfArrays sind vollständig korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.and(
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod("test_hasNext_emptyList_returnsFalse")
                                        ),
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod(
                                                "test_next_emptyList_throwsNoSuchElementException")
                                        ),
                                        JUnitTestRef.ofMethod(
                                            () -> H2TutorTests.class.getDeclaredMethod("test_iterator_combined")
                                        )
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H3 | Methode zum Einfügen einer Sequenz im Block in eine Arrayliste")
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
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Wenn der gegebene Index ungültig ist, wird eine entsprechende Exception geworfen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertExceptions")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Das Einfügen an das Ende einer Liste funktioniert mit wenigen gleich [[[ARRAY_SIZE]]] Elementen korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertAtEnd")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Das Einfügen an das Ende einer Liste funktioniert mit mehr als [[[ARRAY_SIZE]]] Elementen korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertAtEndWithMoreThan256Elements")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Das Einfügen in die Mitte einer Liste funktioniert korrekt, wenn keine neuen Arrays erzeugt werden müssen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertInMiddle")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Das Einfügen in der Mitte der Liste funktioniert korrekt, wenn neue Arrays erzeugt werden müssen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H3TutorTests.class.getDeclaredMethod("testInsertInMiddleWithMoreThan256Elements")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H4 | Methode zum Extrahieren ganzer Blöcke")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Wenn [[[i]]] oder [[[j]]] ungültig sind, wird eine entsprechende Exception geworfen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithEmptyList")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Löschen eines einzelnen Elements funktioniert korrekt, wenn sich das Element am Ende der Liste befindet.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithOneElement")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Löschen mehrerer Elemente funktioniert korrekt, wenn sich die Elemente am Ende der Liste befinden.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithMultipleElements")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Löschen aller Elemente der Liste funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithAllElements")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Löschen von Elementen inmitten einer Liste mit weniger gleich [[[ARRAY_SIZE]]] Elementen funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractWithMultipleElementsInTheMiddle")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Das Löschen von Elementen inmitten einer Liste mit mehr als [[[ARRAY_SIZE]]] Elementen funktioniert korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod(
                                            "testExtractWithMoreThan256ElementsInTheMiddle")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Rückgabeliste ist stets korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractReturnValue")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Methode [[[extract]]] ist vollständig korrekt implementiert.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H4TutorTests.class.getDeclaredMethod("testExtractCombined")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build()
                )
                .build()
        )
        .addChildCriteria(
            Criterion.builder()
                .shortDescription("H5 | Methode zum Einfügen mehrerer einzelner neuer Elemente")
                .addChildCriteria(
                    Criterion.builder()
                        .shortDescription("Die Klasse ElementWithIndex ist vollständig korrekt implementiert.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testElementWithIndex")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Wenn ein Element an einem ungültigen Index eingefügt werden soll, wird eine entsprechende Exception geworfen.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithNegativeIndex")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription(
                            "Wenn so viele Elemente, dass das Array zum Zwischenspeichern überfüllt, eingefügt werden sollen, wird eine entsprechende Exception geworfen")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertExceptionWithTooManyElements")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen eines einzelnen Elements an das Ende einer Liste funktioniert immer korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertAtEnd")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen eines einzelnen Elements an den Anfang einer Liste funktioniert immer korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertAtBeginning")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen eines einzelnen Elements inmitten Liste funktioniert immer korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertInMiddle")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen aufeinanderfolgender Elemente funktioniert immer korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsBlock")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Das Einfügen nicht-aufeinanderfolgender Elemente funktioniert immer korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertMultipleAsSpread")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die drei Abbruchbedingungen funktionieren korrekt.")
                        .grader(
                            Grader.testAwareBuilder()
                                .requirePass(
                                    JUnitTestRef.ofMethod(
                                        () -> H5TutorTests.class.getDeclaredMethod("testInsertStopConditions")
                                    )
                                )
                                .pointsFailedMin()
                                .pointsPassedMax()
                                .build()
                        )
                        .build(),
                    Criterion.builder()
                        .shortDescription("Die Methode [[[insert]]] ist vollständig korrekt implementiert.")
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
                                .pointsFailedMin()
                                .pointsPassedMax()
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

    @Override
    public void configure(RubricConfiguration configuration) {
        configuration.addTransformer(new AccessTransformer());
    }
}
