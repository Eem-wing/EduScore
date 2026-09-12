import java.util.*;

public class EduScoreSystem {
    private final Map<String, List<Double>> studentGrades = new HashMap<>();

    public void addGrade(String studentName, double grade) throws InvalidGradeException {
        if (grade < 0.0 || grade > 100.0) {
            throw new InvalidGradeException("Grade " + grade + " is invalid. Must be between 0.0 and 100.0.");
        }
        studentGrades.computeIfAbsent(studentName, k -> new ArrayList<>()).add(grade);
    }

    public double getStudentAverage(String studentName) {
        List<Double> grades = studentGrades.get(studentName);
        if (grades == null || grades.isEmpty()) {
            throw new NoSuchElementException("Student " + studentName + " not found or has no grades.");
        }
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.size();
    }

    public double getStudentHighest(String studentName) {
        List<Double> grades = studentGrades.get(studentName);
        if (grades == null || grades.isEmpty()) {
            throw new NoSuchElementException("Student " + studentName + " not found or has no grades.");
        }
        return Collections.max(grades);
    }

    public Map<String, List<Double>> getStudentGrades() {
        return studentGrades;
    }

    public static void main(String[] args) {
        EduScoreSystem system = new EduScoreSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== EduScore Grade Management ===");
        while (true) {
            System.out.print("\nEnter student name (or type 'exit' to finish): ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("exit")) break;

            System.out.print("Enter grade (0-100): ");
            try {
                double grade = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                system.addGrade(name, grade);
                System.out.println("Grade added successfully.");
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid numeric input.");
                scanner.nextLine(); // clear buffer
            } catch (InvalidGradeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n=== Final Analytics Report ===");
        for (String student : system.getStudentGrades().keySet()) {
            try {
                double avg = system.getStudentAverage(student);
                double high = system.getStudentHighest(student);
                System.out.printf("Student: %-15s | Highest: %-6.2f | Average: %-6.2f%n", student, high, avg);
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}