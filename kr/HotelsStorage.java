package kr;

import java.util.*;
import java.io.*;


class HotelsStorage {
    private ArrayList<Hotel> st;

    public HotelsStorage() {
        st = new ArrayList<Hotel>();
    }

    public void readfile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            
            while (fileScanner.hasNextLine()) {
                Hotel hotel = Hotel.readFromFile(fileScanner);
                if (hotel != null) {
                    st.add(hotel);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }

    public ArrayList<Hotel> getAllHotels() {
        return st;
    }


    public void output() {
        System.out.println("List of all hotels:");
        for (Hotel hotel : st) {
            System.out.print(hotel.out());
        }
    }


    public static Comparator<Hotel> byCityAndStars = 
        Comparator.comparing(Hotel::getCity).thenComparing(Comparator.comparingInt(Hotel::getStar).reversed());

    public void sortByCityAndStars() {
        Collections.sort(st, byCityAndStars);
    }

    public void printHotelsByCity(String city) {

        
        System.out.println("Hotels in " + city + ":");
        boolean found = false;
        for (Hotel hotel : st) {
            if (hotel.getCity().equalsIgnoreCase(city)) {
                System.out.print(hotel.out());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No hotels found in " + city);
        }
    }

    public void printCitiesByHotelName(String hotelName) {
        System.out.println("Cities with hotel '" + hotelName + "':");
        boolean found = false;
        for (Hotel hotel : st) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                System.out.println(hotel.getCity() + " - " + hotel.getStar() + " stars");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cities found with hotel '" + hotelName + "'");
        }
    }

}