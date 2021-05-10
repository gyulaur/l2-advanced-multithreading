package com.company.advanced.multithreading.task1.mergesort;

import java.util.Arrays;

public class MergeSortSequential {

    static int[] sort(int[] array) {

        if (array.length < 3) {
            Arrays.sort(array);
            return array;
        }

        int middle = array.length / 2;

        var left = sort(Arrays.copyOfRange(array, 0, middle));
        var right = sort(Arrays.copyOfRange(array, middle, array.length));

        return MergeUtil.merge(array.length, left, right);
    }
}
