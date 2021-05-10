package com.company.advanced.multithreading.task1.util;

import java.util.concurrent.Callable;

public class TimerUtil {

    private static final int NUM_EVAL_RUNS = 5;

    public static MeasureResult measure(Callable<int[]> function) {
        double parallelNewArrayTime = 0;
        int[] sortedByForkJoinPool = new int[0];
        for (int i = 0; i < NUM_EVAL_RUNS; i++) {
            long start = System.currentTimeMillis();
            try {
                sortedByForkJoinPool = function.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            parallelNewArrayTime += System.currentTimeMillis() - start;
        }

        return new MeasureResult(sortedByForkJoinPool, parallelNewArrayTime / NUM_EVAL_RUNS);
    }

    public static class MeasureResult {
        private final int[] result;
        private final double avarageTime;

        public MeasureResult(int[] result, double avarageTime) {
            this.result = result;
            this.avarageTime = avarageTime;
        }

        public int[] getResult() {
            return result;
        }

        public double getAvarageTime() {
            return avarageTime;
        }
    }
}
