package com.company;

import java.util.ArrayList;
import java.util.List;

public class BasicMathProblems {
    public int gcd(int a, int b) {
        List<Integer> factorsA = primeFactors(a);
        List<Integer> factorsB = primeFactors(b);
        int gcd = 1;

        for (Integer factor : factorsA) {
            if (factorsB.contains(factor)) {
                gcd *= factor;
                factorsB.remove(factor); // Avoid double counting
            }
        }
        return gcd;
    }

    public int EuclidianGCD(int a, int b) {
        return b == 0 ? a : EuclidianGCD(b, a % b);
    }

    public int EuclidianIterativeGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

}
