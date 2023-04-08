package Box2;

public class Box extends Shape {
    public Box(double volume) {
        this.volume = volume;
    }

    public boolean add(Shape shape){
        if (this.volume > shape.getVolume()){
            this.volume -= shape.getVolume();
            return true;
        }
        else return false;
    }
}
