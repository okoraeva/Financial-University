package Randomizer;
import java.util.Scanner;

public class RandomizerMain {
    static public void selectAction(){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Randomizer randomizer = null;
        while (flag){
            System.out.println("Введите колличество элементов");
            int count = scanner.nextInt();
            if (count > 0){
                double[] values = new double[count];
                int[] weights = new int[count];

                System.out.println("Введите значения");
                for(int i = 0; i < count; i++){
                    values[i] = scanner.nextDouble();
                }

                System.out.println("Введите веса");
                for(int i = 0; i < count; i++){
                    weights[i] = scanner.nextInt();
                }
                randomizer = new Randomizer(values, weights);
                flag = false;
            }
            else {
                System.out.println("err_5.1");
            }
        }

        flag = true;
        while (flag) {
            System.out.println("1. Сгенерировать число\n" +
                    "0. Выход");
            switch (scanner.nextInt()) {
                case 0: {
                    flag = false;
                    break;
                }
                case 1: {
                    System.out.println(randomizer.random());
                    break;
                }
            }
        }
    }
}
