package com.ssu.juliablack.deadlock;

class ClassA {

    synchronized void firstMethod(ClassB b) {
        System.out.println("ClassA run");

        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }

        System.out.println("ClassB run from ClassA");
        b.secondMethod();
    }

    synchronized void secondMethod() {
        System.out.println("ClassA.secondMethod()");
    }
}