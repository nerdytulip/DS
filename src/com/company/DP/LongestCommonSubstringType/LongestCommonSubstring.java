package com.company.DP.LongestCommonSubstringType;

import java.util.Arrays;

public class LongestCommonSubstring {
    /**
     * Computes the longest common substring of a and b using recursion + memoization.
     */
    public static String lcs(String a, String b) {
        int n = a.length(), m = b.length();
        // memo[i][j] = length of longest common suffix ending at a[i-1], b[j-1]
        int[][] memo = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int maxLen = 0;
        int endIdx = 0;  // end position in 'a' for the best substring

        // Try every pair (i, j) as potential end of a common suffix
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int len = lcsSuffix(a, b, memo, i, j);
                if (len > maxLen) {
                    maxLen = len;
                    endIdx = i;
                }
            }
        }

        // Extract substring from 'a'
        return a.substring(endIdx - maxLen, endIdx);
    }

    /**
     * Helper to compute length of longest common suffix ending at a[i-1], b[j-1].
     */
    private static int lcsSuffix(String s1, String s2, int[][] memo, int i, int j) {
        if (i == 0 || j == 0) return 0;
        if (memo[i][j] != -1) return memo[i][j];

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            memo[i][j] = 1 + lcsSuffix(s1, s2, memo, i - 1, j - 1);
        } else {
            memo[i][j] = 0;
        }
        return memo[i][j];
    }

    public static void main(String[] args) {
        String a = "ABABC";
        String b = "BABCA";
        System.out.println(lcs(a, b));  // prints "BABC"
    }
}
