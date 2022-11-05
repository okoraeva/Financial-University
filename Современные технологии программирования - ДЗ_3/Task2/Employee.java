package Task2;

public abstract class Employee {
    String name;
    String surname;
    int id;

    public Employee(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public abstract double calculateSalary();

    public String getName(){
        return this.name;
    }
}