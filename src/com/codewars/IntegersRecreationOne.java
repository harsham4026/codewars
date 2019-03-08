package com.codewars;

/**
 * https://www.codewars.com/kata/integers-recreation-one/
 */
public class IntegersRecreationOne {
    public static String listSquared(long m, long n) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (long i = m; i <= n; i++) {
            long sum = getSquaredSumOfDivisors(i);
            long sqrt = (long) Math.sqrt(sum);
            if (sqrt * sqrt == sum) {
                builder.append("[").append(i).append(",").append(" ").append(sum).append("], ");
            }
        }
        if (builder.length() > 1) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    private static long getSquaredSumOfDivisors(long n) {
        long sum = 0;
        for (long i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += (i * i);
                if (n / i != i) {
                    sum += ((n / i) * (n / i));
                }
            }
        }
        return sum;
    }
}
