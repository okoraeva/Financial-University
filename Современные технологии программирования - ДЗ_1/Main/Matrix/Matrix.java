package Matrix;

import java.util.Scanner;

public class Matrix {
    private double matrix[][];
    private int countOfStrings;
    private int countOfColumns;

    public Matrix(double matrix[][]){
        this.matrix = matrix;
        this.countOfColumns = matrix.length;
        this.countOfStrings = matrix[0].length;
    }

    public Matrix(){
        System.out.println("Ведите число строк");
        Scanner scanner = new Scanner(System.in);
        int strings = scanner.nextInt();
        System.out.println("Ведите число столбцов");
        int columns = scanner.nextInt();

        if(strings > 0 && columns > 0){
            System.out.println("Вводите элементы");
            scanner = new Scanner(System.in);
            double[][] matrix = new double[strings][columns];
            for(int i = 0; i < strings; i++){
                for (int j = 0; j < columns; j++){
                    matrix[i][j] = scanner.nextDouble();
                }
            }
            this.countOfStrings = strings;
            this.countOfColumns = columns;
            this.matrix = matrix;
        }
        else{
            System.out.println("err_1.1");
        }
    }

    public Matrix addition(Matrix second){
        if(this.countOfColumns == second.getCountOfColumns() && this.countOfStrings == second.getCountOfStrings()){
            Matrix matrix = new Matrix(new double[this.countOfStrings][this.countOfColumns]);

            for(int i = 0; i < matrix.getCountOfStrings(); i++){
                for(int j = 0; j < matrix.getCountOfColumns(); j++){
                    matrix.getMatrix()[i][j] = this.matrix[i][j] + second.getMatrix()[i][j];
                }
            }
            return matrix;
        }
        System.out.println("err_1.2");
        return null;
    }

    public Matrix subtraction(Matrix second){
        if(this.countOfColumns == second.getCountOfColumns() && this.countOfStrings == second.getCountOfStrings()){
            Matrix matrix = new Matrix(new double[this.countOfStrings][this.countOfColumns]);

            for(int i = 0; i < matrix.getCountOfStrings(); i++){
                for(int j = 0; j < matrix.getCountOfColumns(); j++){
                    matrix.getMatrix()[i][j] = this.matrix[i][j] + second.getMatrix()[i][j];
                }
            }
            return matrix;
        }
        System.out.println("err_1.2");
        return null;
    }

    public Matrix multiplyingByNumber(double number){
        Matrix newMatrix = new Matrix(new double[this.countOfStrings][this.countOfColumns]);

        for(int i = 0; i < this.countOfStrings; i++) {
            for (int j = 0; j < this.countOfColumns; j++) {
                newMatrix.getMatrix()[i][j] = this.matrix[i][j] * number;
            }
        }
        return newMatrix;
    }

    public Matrix transposition(){
        Matrix newMatrix = new Matrix(new double[this.countOfColumns][this.countOfStrings]);

        for(int i = 0; i < this.countOfStrings; i++) {
            for (int j = 0; j < this.countOfColumns; j++) {
                newMatrix.getMatrix()[j][i] = this.matrix[i][j];
            }
        }
        return newMatrix;
    }

    public Matrix multiplyingByMatrix(Matrix second){
        if(this.countOfColumns == second.getCountOfStrings()){
            Matrix matrix = new Matrix(new double[this.countOfStrings][second.getCountOfColumns()]);
            for(int i = 0; i < matrix.getCountOfStrings(); i++){
                for(int j = 0; j < matrix.getCountOfColumns(); j++){
                    for(int i1 = 0; i1 < this.countOfColumns; i1++){
                        matrix.getMatrix()[i][j] += this.matrix[i][i1] * second.getMatrix()[i1][j];
                    }
                }
            }
            return matrix;
        }
        System.out.println("err_1.4");
        return null;
    }

    public Matrix riseToDegree(int degree){
        if(degree > 0  && this.countOfColumns == this.countOfStrings){
            Matrix newMatrix = this.multiplyingByMatrix(this);
            for (int i = 0; i < degree - 1; i++){
                newMatrix = newMatrix.multiplyingByMatrix(this);
            }
            return newMatrix;
        }
        System.out.println("err_1.5");
        return null;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getCountOfStrings() {
        return countOfStrings;
    }

    public int getCountOfColumns() {
        return countOfColumns;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < countOfStrings; i++){
            for(int j = 0; j < countOfColumns; j++){
                string.append(matrix[i][j]).append(" ");
            }
            string.append("\n");
        }
        return string.toString();
    }
}
