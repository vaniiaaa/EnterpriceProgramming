//8 22 36
package lab2;


import java.io.FileNotFoundException;



public class second_task_matr {

    public static void main(String[] args) {
        try {
            Matr M = new Matr("matrix.txt");
            M.printMatrix();
            System.out.println("Row with max seria is: " + M.find_max_in_row());
            System.out.println("Number of locmins: " + M.count_of_locmin());
            M.moveMaxToTopLeft();
            System.out.println("Max element in left top: ");
            M.printMatrix();
            M.writeToFile("input.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}