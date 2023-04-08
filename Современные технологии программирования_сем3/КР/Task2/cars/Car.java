package Task2.cars;

public abstract class Car {

    public Car(String color, int maxSpeed, String type, int price){
        this.color = color;
        this.maxSpeed = maxSpeed;
        this.type = type;
        this.price = price;

        this.curSpeed = 0;
        this.isStarted = false;
    }

    private String color;
    private int maxSpeed;
    private String type;
    private int curSpeed;
    private int price;
    private boolean isStarted;

    public int getPrice() {
        return price;
    }

    public int getCurSpeed() {
        return curSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void start(){
        if(isStarted){
            System.out.println("Машина уже заведена!");
        }
        else{
            isStarted = true;
            System.out.println("Машина заведена");
        }
    }

    public void accelerate(int speed){
        if(isStarted){
            this.curSpeed = speed;
            System.out.println("Машина ускоряется, текущая скорость = " + curSpeed);
        }
       else{
            System.out.println("Для начала нужно завести машину!");
        }
    }

    public void stop(){
        if(!isStarted){
            System.out.println("Машина на стоянке!");
        }
        else{
            this.curSpeed = 0;
            isStarted = false;
            System.out.println("Машина остановлена");
        }
    }
}