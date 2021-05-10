package com.company.advanced.multithreading.task1.mergesort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallelReuseSource {
    private final int[] source;

    public MergeSortParallelReuseSource(int[] source) {
        this.source = source;
    }

    public int[] getSource() {
        return source;
    }

    public int[] sort() {
        int numWorkers = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(numWorkers);
        pool.invoke(new Worker(0, source.length - 1));
        return source;
    }

    private class Worker extends RecursiveAction {

        private final int left, right;

        public Worker(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;
                Worker leftWorker = new Worker(left, mid);
                Worker rightWorker = new Worker(mid + 1, right);
                invokeAll(leftWorker, rightWorker);
                merge(left, mid, right);
            }
        }

    }

    private void merge(int left, int mid, int right) {
        int leftIndex = 0, mergeIndex = left, rightIndex = 0;

        int[] leftTempArray = Arrays.copyOfRange(source, left, mid + 1);
        int[] rightTempArray = Arrays.copyOfRange(source, mid + 1, right + 1);

        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftTempArray[leftIndex] <= rightTempArray[rightIndex]) {
                source[mergeIndex++] = leftTempArray[leftIndex++];
            } else {
                source[mergeIndex++] = rightTempArray[rightIndex++];
            }
        }

        if (leftIndex < leftLength) {
//            for (int k = leftIndex; k < leftLength; k++) {
//                source[mergeIndex++] = leftTempArray[leftIndex++];
//            }
            for (int k = 0; leftIndex + k < leftLength; k++) {
                source[mergeIndex + k] = leftTempArray[leftIndex + k];
            }
        } else if (rightIndex < rightLength) {
//            for (int k = rightIndex; k < rightLength; k++) {
//                source[mergeIndex++] = rightTempArray[rightIndex++];
//            }
            for (int k = 0; rightIndex + k < rightLength; k++) {
                source[mergeIndex + k] = rightTempArray[rightIndex + k];
            }
        }
    }
}
