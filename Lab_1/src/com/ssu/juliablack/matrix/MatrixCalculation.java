package com.ssu.juliablack.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MatrixCalculation {

    public static final int N = 3;

    public static void test() throws ExecutionException, InterruptedException {
        int[][] mat1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] mat2 = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] result = new int[N][N];
        BlockingQueue<Task> allTask = new ArrayBlockingQueue<>(9);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    list.add(mat1[i][k]);
                    list.add(mat2[k][j]);
                }
                allTask.add(new Task(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5)));
                list.clear();
            }
        }

      //  System.out.println("Run executorService");

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Integer>> future = service.invokeAll(allTask);

        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, count++) {
                result[i][j] = future.get(count).get();
            }
        }
        service.shutdown();
        showMatrix(result);
    }

    public static void showMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.format("%6d ", ints[j]);
            }
            System.out.println();
        }
    }
}
