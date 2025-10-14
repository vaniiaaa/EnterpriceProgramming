package lab1;
import java.util.Scanner;
import java.text.*;

class Number {
    private double x, k;
    private static Scanner scanner = new Scanner(System.in);

    public Number() {
        x = k = 1.0;
    }
    public Number(double a, double b) {
        x = a;
        k = b;
    }
    public Number(Number X) {
        x = X.x;
        k = X.k;
    }
    
    public void input() {
        System.out.println("Enter x and k");
        x = scanner.nextDouble();
        k = scanner.nextDouble();
    }

    public double count_exp() {
        double answ = 1.0, epsilon = Math.pow(0.1, k);
        double step = 1.0;
        int n = 1;
        while (Math.abs(step) >= epsilon) {
            step = step * x / n;
            answ += step;
            n++;
        }
        return answ;
    }

    public double count_sin() {
        double answ = 0, eps = Math.pow(0.1, k);
        double step = x%(2*Math.PI), y = step;
        int n = 3;
        while (Math.abs(step) >= eps) {
            answ += step;
            step *= (-y) * y / n / (n-1);
            n += 2;
        }  
        return answ;
    }
    public double syst_sin() {
        return Math.sin(x);
    }
    public double syst_exp() {
        return Math.exp(x);
    }
}


public class first_task_Taylor {
    public static void main(String[] args) {
        NumberFormat n_form = NumberFormat.getNumberInstance();
        n_form.setMaximumFractionDigits(3);
        Number num1 = new Number();
        num1.input();
        System.out.println("Sinus expansion: " + n_form.format(num1.count_sin()));
        System.out.println("Sinus expansion: " + n_form.format(num1.syst_sin()));
        num1.input();
        System.out.println("Exponentaial expansion: " + n_form.format(num1.count_exp()));
        System.out.println("Exponentaial expansion: " + n_form.format(num1.syst_exp()));
    }
}
