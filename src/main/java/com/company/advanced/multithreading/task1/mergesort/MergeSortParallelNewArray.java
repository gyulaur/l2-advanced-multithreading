package com.company.advanced.multithreading.task1.mergesort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MergeSortParallelNewArray {
    private final int[] source;

    public MergeSortParallelNewArray(int[] source) {
        this.source = source;
    }

    public int[] sort() {
        int numWorkers = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numWorkers);
        return pool.invoke(new SortTask(source));
    }

    public static class SortTask extends RecursiveTask<int[]> {

        private final int[] array;

        public SortTask(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            if (array.length < 2) {
                Arrays.sort(array);
                return array;
            }

            int middle = array.length / 2;

            var left = new SortTask(Arrays.copyOfRange(array, 0, middle));
            var right = new SortTask(Arrays.copyOfRange(array, middle, array.length));

            invokeAll(left, right);

            return MergeUtil.merge(array.length, left.join(), right.join());
        }

    }
}
