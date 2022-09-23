package Vector;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector() {
    }

    public double length(){
        return Math.sqrt(this.x * this.x + this.x * this.x + this.x * this.x);
    }

    public double scalarProduct(Vector v2){
        return this.x*v2.getX() + this.x*v2.getY() + this.x*v2.getZ();
    }

    public Vector vectorProduct(Vector v2){
        Vector vector = new Vector();
        vector.setX(this.x*v2.getZ() - this.x*v2.getY());
        vector.setX(this.x*v2.getX() - this.x*v2.getZ());
        vector.setX(this.x*v2.getY() - this.x*v2.getX());
        return vector;
    }

    public double getAngle(Vector v2){
        if(this.length()!= 0 && v2.length() != 0) {
            return this.scalarProduct(v2) / (this.length() * v2.length());
        }
        System.out.println("err_2.1");
        return 0;
    }

    public Vector addition(Vector v2){
        Vector vector = new Vector();
        vector.setX(this.x + v2.getX());
        vector.setY(this.x + v2.getY());
        vector.setZ(this.x + v2.getZ());
        return vector;
    }

    public Vector subtraction(Vector v2){
        Vector vector = new Vector();
        vector.setX(this.x - v2.getX());
        vector.setY(this.x - v2.getY());
        vector.setZ(this.x - v2.getZ());
        return vector;
    }

    static public Vector[] generateVectors(int size){
        Vector[] vectors = new Vector[size];
        for(int i = 0; i < size; i++){
            vectors[i] = new Vector((int)Math.random()*100, (int)Math.random()*100, (int)Math.random()*100);
        }
        return vectors;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ')';
    }
}
