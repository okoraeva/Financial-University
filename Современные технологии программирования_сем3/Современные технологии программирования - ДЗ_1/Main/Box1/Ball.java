package Box1;

public class Ball extends SolidOfRevolution{
    public Ball(double radius) {
        super(radius);
        this.volume = 4 * Math.PI * radius * radius * radius / 3;
    }
}
