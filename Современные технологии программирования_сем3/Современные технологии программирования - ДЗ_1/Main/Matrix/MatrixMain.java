package Matrix;

import java.util.Scanner;

public class MatrixMain {
    static public void selectAction(){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Matrix answer = null;
        while (flag){
            System.out.println("Выберите действие \n" +
                    "1. Сложение Матриц\n" +
                    "2. Вычитание матриц\n" +
                    "3. Умнножение матрицы на число\n" +
                    "4. Произведение двух матриц\n" +
                    "5. Транспонированая матрица\n" +
                    "6. Возведение матрицы в степень\n" +
                    "0. Выход");
            switch (scanner.nextInt()){
                case 0:{
                    flag = false;
                    break;
                }
                case 1:{
                    Matrix first = new Matrix();
                    Matrix second = new Matrix();
                    answer = first.addition(second);
                    break;
                }
                case 2:{
                    Matrix first = new Matrix();
                    Matrix second = new Matrix();
                    answer = first.subtraction(second);
                    break;
                }
                case 3:{
                    Matrix matrix = new Matrix();
                    System.out.println("Введите число");
                    double value = scanner.nextDouble();
                    answer = matrix.multiplyingByNumber(value);
                    break;
                }
                case 4:{
                    Matrix first = new Matrix();
                    Matrix second = new Matrix();
                    answer = first.multiplyingByMatrix(second);
                    break;
                }
                case 5:{
                    Matrix matrix = new Matrix();
                    answer = matrix.transposition();
                    break;
                }
                case 6:{
                    Matrix matrix = new Matrix();
                    System.out.println("Введите степень");
                    int degree = scanner.nextInt();
                    answer = matrix.riseToDegree(degree);
                    break;
                }
            }
            if (answer != null){
                System.out.println(answer.toString());
            }
        }
    }
}
