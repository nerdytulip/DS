package com.company.DP.DPWithDistinctWays;

import java.util.Arrays;

public class DecodeWays {
    private int solveRec(String s, int index) {
        // Base case: Reached end of string → 1 valid decoding
        if (index == s.length()) {
            return 1;
        }

        // If the string starts with '0', it's invalid
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Option 1: Take a single digit
        int count = solveRec(s, index + 1);

        // Option 2: Take two digits (only if it's <= 26)
        if (index + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(index, index + 2));
            if (num <= 26) {
                count += solveRec(s, index + 2);
            }
        }

        return count;
    }

    public int numDecodingsRec(String s) {
        return solveRec(s, 0);
    }

    private int solveMem(String s, int index, Integer[] memo) {
        // Base case: reached end of string
        if (index == s.length()) {
            return 1;
        }

        // If already computed
        if (memo[index] != null) {
            return memo[index];
        }

        // If current char is '0', can't decode
        if (s.charAt(index) == '0') {
            return memo[index] = 0;
        }

        // Option 1: Single digit
        int count = solveMem(s, index + 1, memo);

        // Option 2: Two digits
        if (index + 1 < s.length()) {
            int num = Integer.parseInt(s.substring(index, index + 2));
            if (num <= 26) {
                count += solveMem(s, index + 2, memo);
            }
        }

        // Save result and return
        return memo[index] = count;
    }

    public int numDecodingsMem(String s) {
        Integer[] memo = new Integer[s.length()];
        return solveMem(s, 0, memo);
    }

    private int solveTab(String s){
        int n = s.length();
        int[] dp = new int[n + 1]; // dp[i] = number of ways to decode from index i onward

        // Base case: Empty string has 1 way to decode
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            // If current character is '0', no decoding
            if (s.charAt(i) == '0') {
                dp[i] = 0;
            } else {
                // Option 1: Take 1 digit
                dp[i] = dp[i + 1];

                // Option 2: Take 2 digits if valid
                if (i + 1 < n) {
                    int num = Integer.parseInt(s.substring(i, i + 2));
                    if (num <= 26) {
                        dp[i] += dp[i + 2];
                    }
                }
            }
        }

        return dp[0];
    }

    public int numDecodingsTab(String s) {
        return solveTab(s);
    }

    private int solveSO(String s) {
        int n = s.length();

        int next = 1;     // dp[i+1], base case: empty string
        int nextToNext = 0; // dp[i+2]

        int curr = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                curr = 0;
            } else {
                // Take 1 digit
                curr = next;

                // Take 2 digits if valid
                if (i + 1 < n) {
                    int num = Integer.parseInt(s.substring(i, i + 2));
                    if (num <= 26) {
                        curr += nextToNext;
                    }
                }
            }

            // Shift for next iteration
            nextToNext = next;
            next = curr;
        }

        return next;
    }

    public int numDecodings(String s) {
        return solveSO(s);
    }

}
