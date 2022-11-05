package Task1;

public class OneTimePayment extends Card {
    private int tripsAvailable;

    public OneTimePayment(int id){
        super(id);
        this.tripsAvailable = 0;
    }

    public int getTripsAvailable(){
        return this.tripsAvailable;
    }

    public void changeTripsAvailable(int num){
        this.tripsAvailable += num;
    }

    public void AtmAddTrips(ATM atm){
        atm.AddTrips(this);
    }


}