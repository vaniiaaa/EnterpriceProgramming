package lab2;

import java.lang.String;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

class Matr {
    public Matr(String filename) throws FileNotFoundException {
        readFromFile(filename);
    }

    public int count_of_locmin() {
        int[][] tempM = new int[rows + 2][cols + 2];
        for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < cols + 2; j++) {
                if (i == 0 || i == rows + 1 || j == 0 || j == cols + 1)
                    tempM[i][j] = 20000;
                else
                    tempM[i][j] = M[i - 1][j - 1];
            }
        }
        int n = 0;
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                if (tempM[i][j] < tempM[i - 1][j] &&
                        tempM[i][j] < tempM[i + 1][j] &&
                        tempM[i][j] < tempM[i][j + 1] &&
                        tempM[i][j] < tempM[i][j - 1])
                    n++;

            }
        }
        return n;
    }

    public int find_max_in_row() {
        int max = 1;
        int temp = 1;
        int max_row = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (M[i][j] == M[i][j + 1])
                    temp++;
                else if (temp > max) {
                    max = temp;
                    temp = 1;
                    max_row = i + 1;
                } else
                    temp = 1;
            }
            temp = 1;
        }
        return max_row;
    }

    public void moveMaxToTopLeft() {
        int maxValue = M[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (M[i][j] > maxValue) {
                    maxValue = M[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        if (maxRow != 0) {
            int[] tempRow = M[0];
            M[0] = M[maxRow];
            M[maxRow] = tempRow;
        }

        if (maxCol != 0) {
            for (int i = 0; i < rows; i++) {
                int temp = M[i][0];
                M[i][0] = M[i][maxCol];
                M[i][maxCol] = temp;
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void writeToFile(String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    writer.print(M[i][j] + " ");
                }
                writer.println();
            }
        }
    }

    public void readFromFile(String filename) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            rows = scanner.nextInt();
            cols = scanner.nextInt();
            M = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                M[i][j] = scanner.nextInt();
            }
        }
        }
    }

    private int[][] M;
    private int rows, cols;
}
