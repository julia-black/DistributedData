package juliablack.deadlock;

public class ClassB {
    synchronized void firstMethod(ClassA a) {

        System.out.println("ClassB run");

        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }

        System.out.println("ClassA run from ClassB");
        a.secondMethod();
    }

    synchronized void secondMethod() {
        System.out.println("ClassB.secondMethod()");
    }
}
