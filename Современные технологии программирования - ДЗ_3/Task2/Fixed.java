package Task2;

public class Fixed extends Employee {
    double salary;

    public Fixed(String name, String surname, int id, double salary) {
        super(name, surname, id);
        this.salary = salary;
    }

    public double calculateSalary() {
        return this.salary;
    }
}