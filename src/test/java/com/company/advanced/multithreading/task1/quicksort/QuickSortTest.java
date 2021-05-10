package com.company.advanced.multithreading.task1.quicksort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class QuickSortTest {

    @Test
    void testSequentialQuickSort() {
        // GIVEN
        var source = new int[]{7, 4, 2, 3, 7, 9, 0};
        var sorter = new QuickSortSequential(source);
        // WHEN
        var result = sorter.sort();
        // THEN
        var expected = new int[]{0, 2, 3, 4, 7, 7, 9};
        assertArrayEquals(expected, result);
    }

    @Test
    void testParallelQuickSort() {
        // GIVEN
        var source = new int[]{7, 4, 2, 3, 7, 9, 0};
        var sorter = new QuickSortParallel(source);
        // WHEN
        var result = sorter.sort();
        // THEN
        var expected = new int[]{0, 2, 3, 4, 7, 7, 9};
        assertArrayEquals(expected, result);
    }

}