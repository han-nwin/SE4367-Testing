# JUnit with Gradle (Neovim)

## Project Structure

```
Testing/
├── build.gradle
├── src/
│   ├── main/java/       # Your source code
│   └── test/java/       # Your test files
```

## build.gradle

```groovy
plugins {
    id 'java'
    id 'jacoco'
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.10.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.10.0'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

test {
    useJUnitPlatform()
    finalizedBy jacocoTestReport
    testLogging {
        events "passed", "skipped", "failed"
        showStandardStreams = true
    }
}

jacocoTestReport {
    dependsOn test
    reports {
        xml.required = true
        html.required = true
    }
}
```

## Commands

| Command                                    | Description                    |
| ------------------------------------------ | ------------------------------ |
| `gradle test`                              | Run all tests                  |
| `gradle test --info`                       | Run tests with detailed output |
| `gradle test --tests "NamesTest"`          | Run specific test class        |
| `gradle test --tests "NamesTest.testSort"` | Run specific test method       |
| `gradle clean test`                        | Clean and rerun tests          |
| `gradle jacocoTestReport`                  | Generate coverage report       |

## Test Results

- Console output: Shows pass/fail summary
- HTML report: `build/reports/tests/test/index.html`
- XML results: `build/test-results/test/`

## Code Coverage (JaCoCo)

- HTML report: `build/reports/jacoco/test/html/index.html`
- XML report: `build/reports/jacoco/test/jacocoTestReport.xml`

Coverage runs automatically after `gradle test`.

---

# Exercise 4 Answer (RIPR Model)

## The Flawed Test

```java
@Test
public void testSort() {
    names.add("Laura");
    names.add("Han");
    names.add("Alex");
    names.add("Ashley");
    names.sort();
    assertTrue("Sort method", names.getFirst().equals("Alex"));
}
```

## The Flaw

The test only checks that `getFirst()` returns "Alex" after sorting. It does not verify the complete ordering of all elements.

## RIPR Analysis

The flaw is a **Propagation** failure.

- **Reachability**: The `sort()` method is called — the fault location is reached.
- **Infection**: If `sort()` has a bug, the internal state becomes incorrect (e.g., `["Alex", "Laura", "Han", "Ashley"]` instead of `["Alex", "Ashley", "Han", "Laura"]`).
- **Propagation**: The infected state does **not propagate** to the assertion because only `getFirst()` is checked. The incorrect ordering of elements 2-4 never reaches an observable output.
- **Reveal**: Since the infection doesn't propagate, the failure is never revealed.

**In short:** The test has a weak oracle — it only observes one element, so faults that produce a correct first element but incorrect subsequent ordering will not be detected.
