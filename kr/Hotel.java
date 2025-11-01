package kr;

import java.lang.String;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hotel 
{
    private String name, city;
    private int star;

    public Hotel(){name = city = ""; star = -1;}
    public Hotel(String _city, String _name, int _star)
    {
        city = _city;
        name = _name;
        star = _star;
    }
    public static Hotel readFromFile(Scanner fileScanner) {
        if (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();
            
            Pattern pattern = Pattern.compile("^(\\S+)\\s+(.+?)\\s+(\\d+)$");
            Matcher matcher = pattern.matcher(line);
            
            if (matcher.matches()) {
                String city = matcher.group(1);
                String name = matcher.group(2).trim();
                int star = Integer.parseInt(matcher.group(3));
                return new Hotel(city, name, star);
            } else {
                System.out.println("Invalid format in line: " + line);
            }
        }
        return null;
    }
    public String out() {
        return city + " " + name + " " + star + '\n';
    }

    
    public String getCity() { return city;}
    public int getStar() {return star;}
    public String getName() {return name;}
}
