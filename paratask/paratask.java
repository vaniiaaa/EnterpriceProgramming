package paratask;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class paratask {
    public static void main(String[] args) 
    {
        ArrayList<Student> list1 = new ArrayList<>();
        ArrayList<Student> list2 = new ArrayList<>();
        
        try 
        {
            Scanner sc = new Scanner(new File("student1.txt"));
            while(sc.hasNext()) 
            {
                
                Student student = new Student();
                student.Input(sc);
                list1.add(student);
            }
            sc.close();

            sc = new Scanner(new File("student2.txt"));
            while(sc.hasNext()) 
            {
                Student student = new Student();
                student.Input(sc);
                list2.add(student);
            }
            sc.close();

            StudentProcessor.sort(list1);
            StudentProcessor.sort(list2);

            ArrayList<Student> intersectionResult = StudentProcessor.intersection(list1, list2);

            System.out.println("Sorted 1:");
            for (Student s : list1) 
                System.out.println(s.Getname() + " " + s.Getnum() + " " + s.Getgroup() + " " + s.Getgrade() + " ");
            

            System.out.println("\nSorted 2:");
            for (Student s : list2) 
                System.out.println(s.Getname() + " " + s.Getnum() + " " + s.Getgroup() + " " + s.Getgrade() + " ");
            

            System.out.println("\nIntersection:");
            for (Student s : intersectionResult) 
                System.out.println(s.Getname() + " " + s.Getnum() + " " + s.Getgroup() + " " + s.Getgrade() + " ");
            

        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}