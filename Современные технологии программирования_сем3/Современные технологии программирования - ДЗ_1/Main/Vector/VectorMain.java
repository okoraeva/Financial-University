package Vector;

import java.util.Scanner;

public class VectorMain {
    static public void selectAction(){
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("1. Длинна вектора\n" +
                    "2. Скалярное произведние векторов\n" +
                    "3. Векторное произведение\n" +
                    "4. Угол между векторами\n" +
                    "5. Сумма векторов\n" +
                    "6. Разность векторов\n" +
                    "7. Сгенерировать векторы\n" +
                    "0. Выход");
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Введите координаты вектора");
                    Vector v = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v.length());
                    break;
                }
                case 2: {
                    System.out.println("Введите координаты первого вектора");
                    Vector v1 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println("Введите координаты второго вектора");
                    Vector v2 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v1.scalarProduct(v2));
                    break;
                }
                case 3: {
                    System.out.println("Введите координаты первого вектора");
                    Vector v1 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println("Введите координаты второго вектора");
                    Vector v2 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v1.vectorProduct(v2));
                    break;
                }
                case 4: {
                    System.out.println("Введите координаты первого вектора");
                    Vector v1 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println("Введите координаты второго вектора");
                    Vector v2 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v1.getAngle(v2));
                    break;
                }
                case 5: {
                    System.out.println("Введите координаты первого вектора");
                    Vector v1 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println("Введите координаты второго вектора");
                    Vector v2 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v1.addition(v2));
                    break;
                }
                case 6: {
                    System.out.println("Введите координаты первого вектора");
                    Vector v1 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println("Введите координаты второго вектора");
                    Vector v2 = new Vector(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                    System.out.println(v1.subtraction(v2));
                    break;
                }
                case 7: {
                    System.out.println("Введите число векторов, которое необходимо сгенерировать");
                    int count = scanner.nextInt();
                    if(count > 0){
                        Vector[] vectors = Vector.generateVectors(count);
                        for(int i = 0; i < count; i++){
                            System.out.println(vectors[i].toString());
                        }
                    }
                    else{
                        System.out.println("err_2.2");
                    }
                    break;
                }
                case 0: {
                    flag = false;
                    break;
                }
            }
        }
    }
}
