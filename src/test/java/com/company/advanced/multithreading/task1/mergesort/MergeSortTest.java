package com.company.advanced.multithreading.task1.mergesort;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MergeSortTest {

    @Test
    void testSequentialMergeSort() {
        // GIVEN
        var source = new int[]{7, 4, 2, 3, 7, 9, 0};
        // WHEN
        var result = MergeSortSequential.sort(source);
        // THEN
        var expected = new int[]{0, 2, 3, 4, 7, 7, 9};
        assertArrayEquals(expected, result);
    }

    @Test
    void testParallelMergeSortReuseSource() {
        // GIVEN
        var source = new int[]{7, 4, 2, 3, 7, 9, 0};
        var sorter = new MergeSortParallelReuseSource(source);
        // WHEN
        var result = sorter.sort();
        // THEN
        var expected = new int[]{0, 2, 3, 4, 7, 7, 9};
        assertArrayEquals(expected, result);
    }

    @Test
    void testParallelMergeSortNewArray() {
        // GIVEN
        var source = new int[]{7, 4, 2, 3, 7, 9, 0};
        var sorter = new MergeSortParallelNewArray(source);
        // WHEN
        var result = sorter.sort();
        // THEN
        var expected = new int[]{0, 2, 3, 4, 7, 7, 9};
        assertArrayEquals(expected, result);
    }
}