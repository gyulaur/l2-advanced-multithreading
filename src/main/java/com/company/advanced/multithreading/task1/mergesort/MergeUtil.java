package com.company.advanced.multithreading.task1.mergesort;

public final class MergeUtil {

    private MergeUtil() {
    }

    static int[] merge(int resultLength, int[] leftArray, int[] rightArray) {
        int i = 0,j = 0;

        int[] result = new int[resultLength];

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] < rightArray[j]) {
                result[i+j] = leftArray[i];
                i++;
            } else {
                result[i+j] = rightArray[j];
                j++;
            }
        }

        if (i < leftArray.length) {
            System.arraycopy(leftArray, i, result, j + i, leftArray.length - i);
        }
        else if (j < rightArray.length) {
            /*
                for (int k = j; k < rightArray.length; k++) {
                    result[i + k] = rightArray[k];
                }
            }*/
            System.arraycopy(rightArray, j, result, i + j, rightArray.length - j);
        }
        return result;
    }
}
