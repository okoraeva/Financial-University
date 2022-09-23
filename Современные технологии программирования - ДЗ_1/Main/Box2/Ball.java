package Box2;

public class Ball extends SolidOfRevolution {
    public Ball(double radius) {
        this.radius = radius;
        this.volume = 4 * Math.PI * radius * radius * radius / 3;
    }
}
