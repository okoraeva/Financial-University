package Box1;
import java.util.Scanner;

public class Box1Main {
    static public void selectAction() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        Box box = null;
        while (flag) {
            System.out.println("Введите объём коробки");
            double volume = scanner.nextDouble();
            if (volume > 0) {
                box = new Box(volume);
                flag = false;
            }
        }
        flag = true;
        while (flag) {
            System.out.println("1. Добавить шар\n" +
                    "2. Добавить цилиндр\n" +
                    "3. Добавить пирамиду\n" +
                    "0. Выход");
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Введите радиус");
                    double radius = scanner.nextDouble();
                    if (radius > 0) {
                        Ball ball = new Ball(radius);
                        System.out.println(box.add(ball));
                    }
                    break;
                }
                case 2: {
                    System.out.println("Введите радиус и высоту");
                    double radius = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    if (radius > 0 && height > 0) {
                        Cylinder cylinder = new Cylinder(radius, height);
                        System.out.println(box.add(cylinder));
                    }
                    break;
                }
                case 3: {
                    System.out.println("Введите высоту и длинну ребра");
                    double h = scanner.nextDouble();
                    double s = scanner.nextDouble();
                    if (h > 0 && s > 0) {
                        Pyramid pyramid = new Pyramid(s, h);
                        System.out.println(box.add(pyramid));
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
