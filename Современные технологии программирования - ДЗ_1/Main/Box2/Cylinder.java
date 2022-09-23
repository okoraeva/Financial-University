package Box2;

public class Cylinder extends SolidOfRevolution {
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;

        this.volume = Math.PI * radius * radius * height;
    }

    
}
