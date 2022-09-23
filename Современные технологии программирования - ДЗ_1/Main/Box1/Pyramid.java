package Box1;

public class Pyramid extends Shape{
    private double s;
    private double h;

    public Pyramid(double s, double h) {
        super(s*h/3);
    }
}
