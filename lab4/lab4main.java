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
                case 0 -> running = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("Program finished.");
    }
}
