package com.ssu.juliablack.sortarray;

import java.util.ArrayList;

public class SortArray {
    public static void test() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(12);
        list.add(1);
        list.add(-1);
        list.add(-5);

        ArrayList<Integer> sorted = new ArrayList<>();

        list.parallelStream().sorted().forEachOrdered(sorted::add);

        for (Integer item : sorted) {
            System.out.print(item + " ");
        }
    }
}
