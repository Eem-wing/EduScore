# EduScore - Student Grade Analytics

An automated grading and analytics console tool that safely handles user input, processes student exam data, and computes grade averages.

## Key Features
* **Custom Exception Handling:** Defines and throws `InvalidGradeException` to validate numerical grade boundaries ($0.0 - 100.0$) without crashing runtime execution.
* **Collections Processing:** Leverages `Map<String, List<Double>>` structures for storing, querying, and aggregating multi-grade student performance metrics.
* **Fault-Tolerant Console I/O:** Uses robust `try-catch` blocks to gracefully catch input mismatches and missing records.

## Tech Stack
* **Language:** Java (JDK 17+)
* **Concepts:** Java Collections Framework, Custom Exceptions, Console I/O
