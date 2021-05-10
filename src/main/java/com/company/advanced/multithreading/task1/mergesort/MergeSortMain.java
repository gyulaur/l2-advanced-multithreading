package com.company.advanced.multithreading.task1.mergesort;

import com.company.advanced.multithreading.task1.util.ArrayGenerateUtil;
import com.company.advanced.multithreading.task1.util.TimerUtil;

public class MergeSortMain {

    public static void main(String[] args) {
        int[] unordered = ArrayGenerateUtil.generateUnorderedArray();

        TimerUtil.MeasureResult sequentialResult = TimerUtil.measure(() -> MergeSortSequential.sort(unordered));

        MergeSortParallelNewArray mergeSortParallelNewArray = new MergeSortParallelNewArray(unordered);
        TimerUtil.MeasureResult parallelRunUseNewArrayResult = TimerUtil.measure(mergeSortParallelNewArray::sort);

        MergeSortParallelReuseSource mergeSortParallelReuseSource1 = new MergeSortParallelReuseSource(unordered);
        TimerUtil.MeasureResult parallelReuseSourceResult = TimerUtil.measure(mergeSortParallelReuseSource1::sort);

        System.out.format("Sequential Time 1: %.1f ms\n", sequentialResult.getAvarageTime());
        System.out.format("Average Parallel (new array) Time 1: %.1f ms\n", parallelRunUseNewArrayResult.getAvarageTime());
        System.out.format("Average Parallel (resuse source) Time 2: %.1f ms\n", parallelReuseSourceResult.getAvarageTime());
    }

}
