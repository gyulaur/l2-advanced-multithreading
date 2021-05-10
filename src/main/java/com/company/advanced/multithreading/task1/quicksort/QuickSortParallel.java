package com.company.advanced.multithreading.task1.quicksort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSortParallel {
    private final int[] array;
    private int[] sorted;

    public QuickSortParallel(int[] array) {
        this.array = array;
    }

    public int[] sort() {
        sorted = Arrays.copyOf(array, array.length);
        int numWorkers = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numWorkers);
        pool.invoke(new Worker(0, array.length - 1));
        return sorted;
    }


    private class Worker extends RecursiveAction {

        private final int low, high;

        private Worker(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                int partitionIndex = QuickSortUtil.partition(sorted, low, high);
                Worker left = new Worker(low, partitionIndex - 1);
                Worker right = new Worker(partitionIndex + 1, high);
                invokeAll(left, right);
            }
        }
    }
}
