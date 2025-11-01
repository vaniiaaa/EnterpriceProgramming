// kr.java
package kr;

import java.util.Scanner;

public class kr {
    public static void main(String[] args) 
    {
        HotelsStorage hotels = new HotelsStorage();
        hotels.readfile("hotel.txt");
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("Choose option: ");
            System.out.println("1. Show all hotels");
            System.out.println("2. Sort by city and stars (descending)");
            System.out.println("3. Find hotels by city");
            System.out.println("4. Find cities by hotel name");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    hotels.output();
                    break;
                    
                case 2:
                    hotels.sortByCityAndStars();
                    System.out.println("Hotels sorted by city and stars: ");
                    hotels.output();
                    break;
                    
                case 3:
                    System.out.print("Enter city name: ");
                    String city = scanner.nextLine();
                    hotels.printHotelsByCity(city);
                    break;
                    
                case 4:
                    System.out.print("Enter hotel name: ");
                    String hotelName = scanner.nextLine();
                    hotels.printCitiesByHotelName(hotelName);
                    break;
                    
                case 5:
                    running = false;
                    System.out.println("End");
                    break;
                    
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }
}