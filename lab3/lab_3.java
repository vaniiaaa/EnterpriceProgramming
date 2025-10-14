package lab3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class lab_3 
{
    public static void main(String[] args) 
    {
        String str = new String();
        System.out.println("Choose method of input: 1 - console, 2 - file");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) 
        {
            case 1:
                str = scanner.nextLine();
                break;

            case 2:
                try 
                {
                    str = Files.readString(Path.of("input_str.txt"));
                } 
                catch (FileNotFoundException e) 
                {
                    System.out.println("File not found");
                } 
                catch (Exception e) 
                {
                    System.out.println("Error" + e.getMessage());
                }
                break;

            default:
                System.out.println("Incorrect input");
        }
        str = del_wor(str, scanner);        
        System.out.println("Choose method of output: 1 - console, 2 - file");
        choice = scanner.nextInt();
        switch (choice) 
        {
            case 1:
                System.out.println(str);
                break;
            case 2:
                try 
                {
                    PrintWriter writer = new PrintWriter("output_str.txt");
                    writer.write(str.toString());
                    writer.close();
                } 
                catch (FileNotFoundException e) 
                {
                    System.out.println("File not found");
                }
                break;
            default:
                break;
        }
        scanner.close();
    }

    public static String del_wor(String str, Scanner scanner)
    {
        System.out.println("Input amount of words: ");
        int amount = scanner.nextInt();
        Pattern regex = Pattern.compile("\\b[aeyuioAEYUIO][a-zA-Z]{" + (amount-1) + "}\\b");
        Matcher match = regex.matcher(str);
        String result = match.replaceAll("");
        result = result.replaceAll("  ", " ").trim();
        return result;
    }
}
