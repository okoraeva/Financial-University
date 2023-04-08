import java.util.*; 

class MyThread extends Thread {

    private Object lock;

    public MyThread(Object object) {
        this.lock = object;
    }
    
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    System.out.println(getName());
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] strings) {
        Object lock = new Object();
        new MyThread(lock).start();
        new MyThread(lock).start();
    }
}

