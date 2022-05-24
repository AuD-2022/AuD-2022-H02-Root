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
                                        () -> H1TutorTests.class.getDeclaredMethod("testListOfArraysConstructor_CorrectList"))
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
