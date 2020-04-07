package juliablack.deadlock;

public class Deadlock implements Runnable {

    ClassA a = new ClassA();
    ClassB b = new ClassB();

    public Deadlock() {
        Thread thread = new Thread(this);
        thread.start();
        a.firstMethod(b);
    }

    @Override
    public void run() {
        b.firstMethod(a);
    }
}