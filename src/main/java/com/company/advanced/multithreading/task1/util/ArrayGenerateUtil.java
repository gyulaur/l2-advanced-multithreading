package com.company.advanced.multithreading.task1.util;

import java.util.Random;

public final class ArrayGenerateUtil {

    public static final int ARRAY_SIZE = 100_000_00;
    public static final int MAX_RANDOM_NUMBER = 10_000;

    private ArrayGenerateUtil() { }

    public static int[] generateUnorderedArray() {
        return generateUnorderedArray(ARRAY_SIZE);
    }

    public static int[] generateUnorderedArray(int size) {
        return new Random().ints(size, 0, MAX_RANDOM_NUMBER).toArray();
    }
}
