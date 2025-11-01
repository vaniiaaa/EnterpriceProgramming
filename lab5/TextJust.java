package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.io.PrintWriter;

public class TextJust {
    public static String StringInput() 
    {
        StringBuilder text = new StringBuilder();
        try 
        {
            Scanner scan = new Scanner(new File("text.txt"));
            while (scan.hasNextLine()) 
            {
                String line = scan.nextLine();
                text.append(line);//.append("\n");
            }
            scan.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println("File not found");
        }
        return text.toString();
    }

    public static void StringOutput(List<String> text) 
    {
        try 
        {
            PrintWriter writer = new PrintWriter(new File("TextEdited.txt"));
            for (String str : text) {
                writer.println(str); 
            }
            writer.close();
        } 
        catch (Exception e) 
        {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) 
    {
        String text = StringInput();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size to format text");
        int size = sc.nextInt();
        List<String> editedtext = TextEditor.EditText(text, size);
        StringOutput(editedtext);
        sc.close();
    }
}