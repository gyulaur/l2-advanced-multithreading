package com.company.advanced.multithreading.task3;

import java.math.BigInteger;
import java.util.concurrent.RecursiveAction;

public class FactorialAction extends RecursiveAction {

    private static final long serialVersionUID = 20L;

    private static final BigInteger THRESHOLD = BigInteger.valueOf(10);

    private final BigInteger start;
    private final BigInteger end;

    private BigInteger result = BigInteger.ONE;

    public FactorialAction(BigInteger start, BigInteger end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end.subtract(start).compareTo(THRESHOLD) <= 0) {
            computeDirectly();
        } else {
            BigInteger mid = start.add(end.subtract(start).divide(
                    BigInteger.valueOf(2)));

            FactorialAction left = new FactorialAction(start, mid);
            FactorialAction right = new FactorialAction(mid.add(BigInteger.ONE), end);

            invokeAll(left, right);
            result = left.getResult().multiply(right.getResult());
        }
    }

    private void computeDirectly() {

        for (BigInteger i = start; i.compareTo(end)<=0; i = i
                .add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
    }

    public BigInteger getResult() {
        return result;
    }

}
