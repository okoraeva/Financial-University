package Task1;

public abstract class Card {
    int id;
    private double balance;

    public Card(int id) {
        this.id = id;
        this.balance = 0;
    }

    public double getBalance(){
        return this.balance;
    }

    public void changeBalance(Double sum){
        this.balance += sum;
    }
    public void changeBalance(int sum){
        this.balance += sum;
    }

    public void AtmChangeBalance(ATM atm){
        atm.AddSum(this);
    }

    public void MobileChangeBalance(Mobile mobile){
        mobile.AddSum(this);
    }

}