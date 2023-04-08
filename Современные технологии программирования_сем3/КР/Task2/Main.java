package Task2;

import Task2.cars.SedanCar;
import Task2.cars.TeslaCar;

public class Main {

    public static void main(String[] args){
        TeslaCar tesla1 = new TeslaCar("серый");
        TeslaCar tesla2 = new TeslaCar("белый");

        LadaCar sedan1 = new SedanCar("синий");

        Garage garage = new Garage(200);

        garage.addCar(tesla1);
        garage.addCar(tesla2);
        garage.addCar(sedan1);
        garage.getCars("amount");
        garage.getCars("price");
    }
}