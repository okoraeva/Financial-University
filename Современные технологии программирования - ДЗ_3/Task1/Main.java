package Task1;

public class Main {
    public static void main(String[] args) {

        Terminal terminal = new Terminal();
        CardType card1 = new CardType(1001, 3);
        OneTimePayment card2 = new OneTimePayment(2001);
        ATM atm = new ATM();

// Пополняем карты
        card1.AtmChangeBalance(atm);
        card2.AtmChangeBalance(atm);
        card2.AtmAddTrips(atm);

        terminal.checkPayment(card1);
        terminal.checkPayment(card1);
        terminal.checkPayment(card2);
        terminal.checkPayment(card2);

        terminal.getStatistics();
    }
}