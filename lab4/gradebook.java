package lab4;

import java.util.*;
import org.json.JSONObject;

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

    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("averageGrade", avergrade);
    
        for (int i = 0; i < sessions.size(); i++) {
            JSONObject session = new JSONObject();
            for (Exams e : sessions.get(i)) {
                JSONObject exam = new JSONObject();
                exam.put("subject", e.subject);
                exam.put("grade", e.grade);
                exam.put("teacher", e.teacher);
                session.append("exams", exam);
            }
            json.append("sessions", session);
        }
    
        return json.toString(4);
    }

    public void writeToJSONFile(String filename) {
        try (java.io.FileWriter file = new java.io.FileWriter(filename)) {
            file.write(this.toJSON());
            System.out.println("Data successfully written to " + filename);
        } catch (java.io.IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveAllStudentsToJSON(Vector<GradeBook> students, String filename) {
        try (java.io.FileWriter file = new java.io.FileWriter(filename)) {
            org.json.JSONArray jsonArray = new org.json.JSONArray();
            for (GradeBook gb : students) {
                jsonArray.put(new org.json.JSONObject(gb.toJSON()));
            }
            file.write(jsonArray.toString(4));
            System.out.println("All students successfully saved to " + filename);
        } catch (java.io.IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public void readFromJSONString(String jsonString) {
        try {
            org.json.JSONObject json = new org.json.JSONObject(jsonString);
            this.name = json.getString("name");
            this.avergrade = json.getDouble("averageGrade");
    
            this.sessions.clear();
            org.json.JSONArray jsonSessions = json.getJSONArray("sessions");
            for (int i = 0; i < jsonSessions.length(); i++) {
                org.json.JSONObject jsonSession = jsonSessions.getJSONObject(i);
                org.json.JSONArray jsonExams = jsonSession.getJSONArray("exams");
    
                Vector<Exams> session = new Vector<>();
                for (int j = 0; j < jsonExams.length(); j++) {
                    org.json.JSONObject jsonExam = jsonExams.getJSONObject(j);
                    String subject = jsonExam.getString("subject");
                    int grade = jsonExam.getInt("grade");
                    String teacher = jsonExam.getString("teacher");
                    session.add(new Exams(subject, grade, teacher));
                }
                this.sessions.add(session);
            }
        } catch (Exception e) {
            System.out.println("Error reading from JSON string: " + e.getMessage());
        }
    }

    public static Vector<GradeBook> loadAllStudentsFromJSON(String filename) {
        Vector<GradeBook> students = new Vector<>();
        try (java.util.Scanner scanner = new java.util.Scanner(new java.io.File(filename))) {
            StringBuilder jsonContent = new StringBuilder();
            while (scanner.hasNextLine()) {
                jsonContent.append(scanner.nextLine());
            }
    
            org.json.JSONArray jsonArray = new org.json.JSONArray(jsonContent.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
                GradeBook gb = new GradeBook();
                gb.readFromJSONString(jsonObject.toString());
                students.add(gb);
            }
    
            System.out.println("All students successfully loaded from " + filename);
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return students;
    }

}
