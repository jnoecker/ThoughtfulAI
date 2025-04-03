# Thoughtful Automation: Package Sorting System

This project is designed to implement a robust package sorting system for Thoughtful's robotic automation factory. The purpose of the system is to classify packages into different stacks (STANDARD, SPECIAL, or REJECTED) based on their dimensions and weight.

## Objective

The main module contains a function `sort(width, height, length, mass)` that determines the appropriate stack for a package according to the following criteria:

1. **STANDARD**: Packages that are neither bulky nor heavy.
2. **SPECIAL**: Packages that are either bulky or heavy, but not both.
3. **REJECTED**: Packages that are both bulky and heavy, or have invalid dimensions/mass.

### Definitions
- A package is **bulky** if:
    - Its volume (Width × Height × Length) is ≥ 1,000,000 cm³, or
    - Any of its dimensions is ≥ 150 cm.
- A package is **heavy** if its mass is ≥ 20 kg.

---

## Folder Structure
├── src/ \
│ ├── main/\
│ │ └── kotlin/org/example/ThoughtfulAutomation.kt\
│ └── test/ \
│ └── kotlin/org/example/ThoughtfulAutomationTest.kt \
├── README.md \
└── build.gradle.kts


- **Main Module**: The implementation of the sorting logic is in `src/main/kotlin/org/example/ThoughtfulAutomation.kt`.
- **Test Suite**: Unit tests are available in `src/test/kotlin/org/example/ThoughtfulAutomationTest.kt`.

---

## Instructions to Run the Project

### Prerequisites

Ensure you have the following installed:
- **JDK**: Version 11 or higher
- **Gradle**: Version 7.0 or higher
- Any preferred IDE such as IntelliJ IDEA.

### Running the Main Application

1. **Clone the repository**:
   ```bash
   git clone https://github.com/jnoecker/ThoughtfulAI.git
   cd PackageSorter
   ```

2. **Build the project** (optional step to verify the build system works):
   ```bash
   ./gradlew build
   ```

3. **Run the main function**:
    - This can be done by executing the `main` application directly from your IDE.
    - Alternatively, use the `run` task:
      ```bash
      ./gradlew run
      ```
---

## Running the Test Suite

Unit tests are included to verify the correctness of the sorting logic. Follow these steps to execute the test suite:

1. **Run tests using Gradle**:
   ```bash
   ./gradlew test
   ```

---

## Example Usage

Here’s how the `sort()` function works with sample inputs:

```kotlin
val stack1 = ThoughtfulAutomation.sort(100, 100, 100, 5)    // Output: "STANDARD"
val stack2 = ThoughtfulAutomation.sort(200, 100, 100, 5)    // Output: "SPECIAL"
val stack3 = ThoughtfulAutomation.sort(200, 100, 100, 25)   // Output: "REJECTED"
```

---

