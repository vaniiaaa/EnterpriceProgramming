package paratask;

import java.util.*;

class Student 
{
    public void Input(Scanner sc) 
    {
        String line = sc.nextLine().trim();
        if (line.isEmpty()) 
        {
            return;
        }
        
        String[] parts = line.split("\\s+");
        
        name = parts[0] + " " + parts[1];
        
        
        num = Long.parseLong(parts[2]);
        group = Integer.parseInt(parts[3]);
        grade = Double.parseDouble(parts[4]);
    }
    
    
    public String Getname() { return name; }
    public int Getgroup() { return group; }
    public long Getnum() { return num; }
    public double Getgrade() { return grade; }
    long num;
    String name;
    int group;
    double grade;
}


class StudentProcessor {
    private StudentProcessor() {}
    
    public static final Comparator<Student> COMPARATOR = new Comparator<Student>() 
    {
        @Override
        public int compare(Student s1, Student s2) 
        {
            int nameCompare = s1.name.compareTo(s2.name);
            if (nameCompare != 0) {
                return nameCompare;
            }
            
            int groupCompare = Integer.compare(s1.group, s2.group);
            if (groupCompare != 0) {
                return groupCompare;
            }

            return Double.compare(s2.grade, s1.grade);
        }
    };
    
    public static void sort(ArrayList<Student> list) 
    {
        Collections.sort(list, COMPARATOR);
    }
    
    public static ArrayList<Student> intersection(ArrayList<Student> set1, ArrayList<Student> set2) 
    {
        
        ArrayList<Student> result = new ArrayList<>();
        int i = 0, j = 0;
        
        while (i < set1.size() && j < set2.size()) 
        {
            Student s1 = set1.get(i);
            Student s2 = set2.get(j);
            
            int comparison = COMPARATOR.compare(s1, s2);
            
            if (comparison < 0) 
            {
                i++;
            } 
            else if (comparison > 0) 
            {
                j++;
            } 
            else 
            {
                result.add(s1);
                i++;
                j++;
            }
        }
        
        return result;
    }
}