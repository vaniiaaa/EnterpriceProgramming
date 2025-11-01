package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class lab4main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Vector<GradeBook> students = new Vector<>();

        try {
            Scanner scan = new Scanner(new File("input_stud.txt"));
            int count_stud = scan.nextInt();
            scan.nextLine();

            for (int i = 0; i < count_stud; i++) {
                GradeBook gb = new GradeBook();
                gb.FileInput(scan);
                students.add(gb);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File 'input_stud.txt' not found.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n===== GradeBook Menu =====");
            System.out.println("1. Sort by name");
            System.out.println("2. Sort by average grade");
            System.out.println("3. Sort by number of exams");
            System.out.println("4. Show all students");
            System.out.println("5. Show excellent students");
            System.out.println("6. Show JSON of all students");
            System.out.println("7. Save students to JSON file");
            System.out.println("8. Load GradeBook from JSON file");
            System.out.println("9. Clear Gradebook data");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1 -> {
                    students.sort(GradeBook.byNameAsc);
                    System.out.println("Sorted by name:");
                    GradeBook.PrintAll(students);
                }
                case 2 -> {
                    students.sort(GradeBook.byAverageDesc);
                    System.out.println("Sorted by average grade:");
                    GradeBook.PrintAll(students);
                }
                case 3 -> {
                    students.sort(GradeBook.byExamCountDesc);
                    System.out.println("Sorted by number of exams:");
                    GradeBook.PrintAll(students);
                }
                case 4 -> {
                    System.out.println("All students:");
                    GradeBook.PrintAll(students);
                }
                case 5 -> GradeBook.PrintExcellentStudents(students);
                case 6 -> {
                    System.out.println("JSON of all students:");
                    for (GradeBook gb : students) {
                        System.out.println(gb.toJSON());
                    }
                }
                case 7 -> {
                    System.out.print("Enter filename to save all students: ");
                    String filename = scanner.next();
                    GradeBook.saveAllStudentsToJSON(students, filename);
                }
                case 8 -> {
                    System.out.print("Enter filename to load all students: ");
                    String filename = scanner.next();
                    students = GradeBook.loadAllStudentsFromJSON(filename);
                    System.out.println("All students loaded successfully.");
                }
                case 9 -> {
                    students.clear();
                }
                case 0 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Program finished.");
    }
}
