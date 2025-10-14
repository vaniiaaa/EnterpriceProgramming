package lab4;

import java.util.*;

class GradeBook {

    class Exams {
        private String subject;
        private int grade;
        private String teacher;

        public Exams(String s, int gr, String t) {
            subject = s;
            grade = gr;
            teacher = t;
        }

        public Exams() {
            subject = teacher = "unknown";
            grade = -1;
        }

        public String Outputex() {
            return subject + " " + grade + " " + teacher;
        }

        public int GetGrade() {
            return grade;
        }
    }

    private String name;
    private double avergrade;
    private Vector<Vector<Exams>> sessions = new Vector<>();

    public GradeBook() {
        name = "unknown";
        avergrade = 0;
    }

    public GradeBook(String n, Vector<Exams> v) {
        name = n;
        sessions.add(v);
        RecalculateAverage();
    }

    public String GetName() {
        return name;
    }

    public double GetAvergrade() {
        return avergrade;
    }

    public int GetTotalExams() {
        int count = 0;
        for (Vector<Exams> s : sessions) count += s.size();
        return count;
    }

    public double FindAverage(Vector<Exams> ex) {
        double sum = 0;
        for (Exams e : ex) sum += e.GetGrade();
        return ex.size() > 0 ? sum / ex.size() : 0;
    }

    public void RecalculateAverage() {
        if (sessions.isEmpty()) {
            avergrade = 0;
            return;
        }
        double total = 0;
        for (Vector<Exams> s : sessions) total += FindAverage(s);
        avergrade = total / sessions.size();
    }

    public void FileInput(Scanner in) {
      
        String nameLine;
        do {
            nameLine = in.nextLine().trim();
        } while (nameLine.isEmpty() && in.hasNextLine());
        
        this.name = nameLine;
        
     
        int sessionCount = in.nextInt();
        in.nextLine();
        
        
        sessions.clear();
        
  
        for (int i = 0; i < sessionCount; i++) {

            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (!line.isEmpty()) {
                    try {
                        int examCount = Integer.parseInt(line);
                        Vector<Exams> sessionExams = new Vector<>();
                        
                 
                        for (int j = 0; j < examCount; j++) {
                            String examLine = in.nextLine().trim();
                        
                            int firstSpace = examLine.indexOf(' ');
                            int secondSpace = examLine.indexOf(' ', firstSpace + 1);
                            
                            if (firstSpace != -1 && secondSpace != -1) {
                                String subject = examLine.substring(0, firstSpace);
                                int grade = Integer.parseInt(examLine.substring(firstSpace + 1, secondSpace));
                                String teacher = examLine.substring(secondSpace + 1);
                                
                                sessionExams.add(new Exams(subject, grade, teacher));
                            }
                        }
                        
                        sessions.add(sessionExams);
                        break;
                    } catch (NumberFormatException e) {
                        // Продолжаем искать число
                        continue;
                    }
                }
            }
        }
        
       
        RecalculateAverage();
    }
    public String Output() {
        RecalculateAverage();
        StringBuilder out = new StringBuilder();
        out.append(name).append("; Total average: ").append(avergrade).append("\n");
        for (int i = 0; i < sessions.size(); i++) {
            double avg = FindAverage(sessions.get(i));
            out.append("Session ").append(i + 1).append("; Average grade: ").append(avg).append("\n");
            for (Exams e : sessions.get(i)) {
                out.append(e.Outputex()).append("\n");
            }
            out.append("\n");
        }
        return out.toString();
    }

    public static Comparator<GradeBook> byAverageDesc = Comparator.comparingDouble(GradeBook::GetAvergrade).reversed();
    public static Comparator<GradeBook> byNameAsc = Comparator.comparing(GradeBook::GetName);
    public static Comparator<GradeBook> byExamCountDesc = Comparator.comparingInt(GradeBook::GetTotalExams).reversed();

    public static void PrintExcellentStudents(Vector<GradeBook> list) {
        System.out.println("=== Excellent students ===");
        for (GradeBook gb : list) {
            gb.RecalculateAverage();
            if (gb.GetAvergrade() >= 9.0) {
                System.out.println(gb.GetName() + " -> " + gb.GetAvergrade());
            }
        }
    }

    public static void PrintAll(Vector<GradeBook> list) {
        for (GradeBook gb : list) {
            System.out.println(gb.Output());
        }
    }
}
