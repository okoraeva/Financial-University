package Task2;

public class HourRate extends Employee {
    double perHour;

    public HourRate(String name, String surname, int id, double perHour) {
        super(name, surname, id);
        this.perHour = perHour;
    }

    public double calculateSalary() {
        return 166.4 * this.perHour;
    }

}