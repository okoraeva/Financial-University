import java.util.*;

public class Main{

    public static double f(double x){
        return Math.cos(Math.pow(x, 5)) + Math.pow(x, 4) - 345.3*x - 23;
    }

    public static double recursiveSolution(double a, double b){
        double x1 = (a + b) / 2;

        if (Math.abs(b - a) <= 0.001){
            return a;
        }

        if (f(a) * f(x1) < 0){
            return recursiveSolution(a, x1);
        }else{
            return recursiveSolution(x1, b);
        }
    }

    public static void main(String[] args){
        double a = 0;
        double b = 10;
        if (f(a)*f(b) > 0){
            System.out.println("На отрезке нет решения уравнения");
        }else{
            System.out.println(recursiveSolution(a, b));
        }
    }
}