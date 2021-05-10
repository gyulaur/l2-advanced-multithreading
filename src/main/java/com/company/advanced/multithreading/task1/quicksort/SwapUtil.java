package com.company.advanced.multithreading.task1.quicksort;

public final class SwapUtil {
    private SwapUtil() {
    }

    static void swap(int[] array, int i, int j) {
        var tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
