package Box1;

public class Box extends Shape{
    public Box(double volume) {
        super(volume);
    }

    public boolean add(Shape shape){
        if (this.volume > shape.getVolume()){
            this.volume -= shape.getVolume();
            return true;
        }
        else return false;
    }
}
