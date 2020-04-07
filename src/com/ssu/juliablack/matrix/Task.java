package com.ssu.juliablack.matrix;

import java.util.concurrent.Callable;

public class Task implements Callable<Integer> {
    int a1, a2, a3, b1, b2, b3;

    public Task(int a1, int b1, int a2, int b2, int a3, int b3) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
    }

    @Override
    public Integer call() {
        //System.out.println("Call task");
        return a1 * b1 + a2 * b2 + a3 * b3;
    }
}