package com.company.advanced.multithreading.task3;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class FactorialRecursiveTask extends RecursiveTask<BigInteger> {

    private static final long serialVersionUID = 1L;

    private BigInteger start;
    private BigInteger end;

    private static final BigInteger THRESHOLD = BigInteger.valueOf(10);

    public FactorialRecursiveTask(BigInteger start, BigInteger end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if (end.subtract(start).compareTo(THRESHOLD) <= 0) {
            return computeDirectly();
        } else {
            BigInteger mid = start.add(end.subtract(start).divide(
                    BigInteger.valueOf(2)));
            FactorialRecursiveTask left = new FactorialRecursiveTask(start, mid);
            left.fork();
            FactorialRecursiveTask right = new FactorialRecursiveTask(
                    mid.add(BigInteger.ONE), end);

            invokeAll(left, right);
            return right.join().multiply(left.join());

        }
    }

    private BigInteger computeDirectly() {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = start; i.compareTo(end) <= 0; i = i
                .add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

}

