package Box1;

public class SolidOfRevolution extends Shape {
    protected double radius;

    public SolidOfRevolution(double radius) {
        super(0);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
