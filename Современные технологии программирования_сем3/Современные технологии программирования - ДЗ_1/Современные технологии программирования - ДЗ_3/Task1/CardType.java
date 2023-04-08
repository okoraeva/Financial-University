package Task1;
import java.time.LocalDate;

public class CardType extends Card{
    // Студенческая - 1, Школьная 2, Социальная - 3
    int cardType; 
    LocalDate date;
    double fixedPayment;

    public CardType(int id, int cardType){
        super(id);
        this.cardType = cardType;
        this.date = LocalDate.of(2000, 1, 1);

        switch (this.cardType) {
            case 1 -> this.fixedPayment = 100;
            case 2 -> this.fixedPayment = 50;
            case 3 -> this.fixedPayment = 150;
        }
    }

}