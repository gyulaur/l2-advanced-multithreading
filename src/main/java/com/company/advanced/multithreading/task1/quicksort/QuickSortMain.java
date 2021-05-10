package com.company.advanced.multithreading.task1.quicksort;

import java.util.Arrays;

import com.company.advanced.multithreading.task1.util.ArrayGenerateUtil;
import com.company.advanced.multithreading.task1.util.TimerUtil;

public class QuickSortMain {

    public static void main(String[] args) {
        int[] array = ArrayGenerateUtil.generateUnorderedArray(100);

        System.out.println("Source: " + Arrays.toString(array));
        QuickSortSequential quickSortSequential = new QuickSortSequential(array);

        TimerUtil.MeasureResult measureSequential = TimerUtil.measure(quickSortSequential::sort);

        QuickSortParallel quickSortParallel = new QuickSortParallel(array);
        TimerUtil.MeasureResult measureParallel = TimerUtil.measure(quickSortParallel::sort);


        System.out.println("Sequential quick sort Time: " + measureSequential.getAvarageTime());
        System.out.println("Parallel quick sort Time: " + measureParallel.getAvarageTime());

    }

}
