package com.company.advanced.multithreading.task1.quicksort;

public final class QuickSortUtil {
    private QuickSortUtil() { }

    static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                SwapUtil.swap(array, i, j);
            }
        }

        SwapUtil.swap(array, i + 1, high);
        return i + 1;
    }
}
