package com.company.advanced.multithreading.task1.quicksort;

import java.util.Arrays;

public class QuickSortSequential {
    private final int[] array;

    public QuickSortSequential(int[] array) {
        this.array = array;
    }

    public int[] sort() {
        int[] sorted = Arrays.copyOf(array, array.length);
        quicksort(sorted, 0, array.length - 1);
        return sorted;
    }

    private static void quicksort(int[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = QuickSortUtil.partition(array, low, high);
            quicksort(array, low, partitionIndex - 1);
            quicksort(array, partitionIndex + 1, high);
        }
    }

}
