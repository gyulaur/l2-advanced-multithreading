package com.company.advanced.multithreading.task3;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class FactorialMain {

    public static void main(String[] args) {
        long start;
        System.out.println("Start Time for Action : " + (start = System.nanoTime()));
        factorialAction(200);
        System.out.println("End Time for Action : " + (System.nanoTime() - start));

        System.out.println("Start Time for RecurssiveTask : " + (start = System.nanoTime()));
        factorialRecursiveTask(200);
        System.out.println("End Time for RecurssiveTask : " + (System.nanoTime() - start));

    }

    private static void factorialRecursiveTask(int fact) {
        FactorialRecursiveTask recursiveTask = new FactorialRecursiveTask(BigInteger.ONE,
                BigInteger.valueOf(fact));
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(recursiveTask));

    }

    private static void factorialAction(int fact) {
        FactorialAction recursiveAction = new FactorialAction(BigInteger.ONE,
                BigInteger.valueOf(fact));
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(recursiveAction);
        System.out.println(recursiveAction.getResult());
    }
}