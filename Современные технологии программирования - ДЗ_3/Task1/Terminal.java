package Task1;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Terminal {
    int trips;
    int oneTime;
    double earned;

    public Terminal(){
        this.trips = 0;
        this.oneTime = 0;
        this.earned = 0;
    }

    public void checkPayment(Card card){
        if (card instanceof CardType) {
            if (DAYS.between(((CardType) card).date, LocalDate.now()) <= 30) {
                System.out.println("\nПоездка по карте " + card.id);
                System.out.println("Проездной заканчивается " + ((CardType) card).date.plusMonths(1));
                this.trips += 1;
            } else if (card.getBalance() >= ((CardType) card).fixedPayment){
                card.changeBalance(-((CardType) card).fixedPayment);
                ((CardType) card).date = LocalDate.now();
                this.earned += ((CardType) card).fixedPayment;
                this.trips += 1;
                System.out.println("\nПоездка по карте " + card.id);
                System.out.println("Куплен проездной до " + LocalDate.now().plusMonths(1));
                System.out.println("Остаток на балансе: " + card.getBalance());
            } else {System.out.println("\nОшибка, пополните баланс карты " + card.id);}


        } else if (card instanceof OneTimePayment) {
            if(((OneTimePayment) card).getTripsAvailable() >= 1) {
                ((OneTimePayment) card).changeTripsAvailable(-1);
                this.oneTime += 1;
                this.trips += 1;
                System.out.println("\nПоездка по карте " + card.id);
                System.out.println("Осталось поездок:  " + ((OneTimePayment) card).getTripsAvailable());
                System.out.println("Остаток на балансе: " + card.getBalance());
            } else if (card.getBalance() >= 40) {
                card.changeBalance(-40);
                this.earned += 40;
                this.trips += 1;
                System.out.println("\nПоездка по карте " + card.id);
                System.out.println("Остаток на балансе: " + card.getBalance());
            } else {
                System.out.println("\nОшибка, пополните баланс карты " + card.id);
            }
        }
    }
    public void getStatistics(){
        System.out.println("\nПроходимость: " + this.trips);
        System.out.println("Поштучных поездок: " + this.oneTime);
        System.out.println("Заработал " + this.earned + " руб." );
    }
}