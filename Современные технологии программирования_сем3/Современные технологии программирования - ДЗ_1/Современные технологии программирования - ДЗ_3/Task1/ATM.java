package Task1;
import java.util.Scanner;

public class ATM {
    public void AddSum(Card card) {
        Scanner in = new Scanner(System.in);
        System.out.print("\nВведите сумму для пополнения карты " + card.id +": ");
        double a = in.nextDouble();
        card.changeBalance(a);
        System.out.println("Карта "+card.id + " пополнена на " + a + " руб.");
    }
    public void AddTrips(OneTimePayment card) {
        Scanner in = new Scanner(System.in);
        System.out.println("\nДобавление разовых поездок");
        System.out.print("Введите сумму для пополнения карты " + card.id +": ");
        double a = in.nextDouble();
        card.changeTripsAvailable((int) Math.floor(a/38));
        System.out.println("На карту "+card.id + " начислено поездок: " + (int) Math.floor(a/38));
        System.out.println("Ваша сдача: "+ a%38);
    }

}