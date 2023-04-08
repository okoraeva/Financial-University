package Main;
import Box1.Box1Main;
import Box2.Box2Main;
import Matrix.*;
import Randomizer.RandomizerMain;
import Vector.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag){
            System.out.println("Выберите задание\n" +
                    "1. Матрицы\n" +
                    "2. Вектора\n" +
                    "3. Коробка 1\n" +
                    "4. Коробка 2\n" +
                    "5. Генератор чисел\n" +
                    "0. Выход");
            switch (scanner.nextInt()){
                case 1:{
                    MatrixMain.selectAction();
                    break;
                }
                case 2:{
                    VectorMain.selectAction();
                    break;
                }
                case 3:{
                    Box1Main.selectAction();
                    break;
                }
                case 4:{
                    Box2Main.selectAction();
                    break;
                }
                case 5:{
                    RandomizerMain.selectAction();
                    break;
                }
                case 0:{
                    flag = false;
                    scanner.close();
                    break;
                }
            }
        }
    }
}
