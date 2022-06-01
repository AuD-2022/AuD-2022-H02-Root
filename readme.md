# AuD-2022-H02-Root
Das Lösungsrepository zu Hausübung 02 der AuD im Sommersemester 2022

## Grading
### den Grader Bauen:
- Unix-Basierende Systeme:
```sh
./gradlew graderAll
```
- Windows:
```bat
./gradlew.bat graderAll
```
### Tests im IDE ausführen
Wenn die Abgaben mit dem Autograder exportiert wurden, muss die `build.gradle.kts` im Abgabeordner entsprechend angepasst werden:
```kt
plugins {
  java
}
allprojects {
  apply(plugin = "java")
  apply(plugin = "application")
  repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
  }
  dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.sourcegrade:jagr-launcher:0.4.0")
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    implementation("org.mockito:mockito-core:4.5.1")
  }
  java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  tasks {
    create<Jar>("prepareSubmission") {
      group = "submit"
      from(sourceSets.main.get().allSource)
      archiveFileName.set("${project.name}-submission.jar")
    }
    test {
      useJUnitPlatform()
    }
  }
}
```
Zudem muss an allen Stellen `private` durch `public` ersetz werden, und `final` entfernt werden. (Während dem Bewertungsvorgang wird dies durch Bytecode-Transformation erzielt)
### Hinweise zum Bewertungsschema und den Tests
- Wenn elemente in die Liste eingefügt werden sollen, haben diese einen `#` als prefix, um sie von den Elementen der Ursprungsliste unterscheiden zu können.
- Wenn Listen erzeugt werden, wird zunächst versucht, die Liste über den Konstruktor der Studiimplentierung zu nutzen. Falls das einen Fehler wirft, oder die Liste nicht die gewünschten Elemente enthält wird der Konstruktor gestubt und die Liste wird durch die Referenzlösung gebaut und dann in head und Tail übertragen, um nur die relevante Methode zu testen.
- Der Iterator ist so gebaut, dass auch Lücken zwischen den Elementen nicht zu einem Abbruch führen, und die currentNumber ignoriert wird. Das kann in Einzeäfällen zu unfairen Bewertungen führen, und kann bei der Nachkorrektur berücksichtigt werden
