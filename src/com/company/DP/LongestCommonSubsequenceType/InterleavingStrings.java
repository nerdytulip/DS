package com.company.DP.LongestCommonSubsequenceType;

public class InterleavingStrings {
    private boolean solveRec(String s1, String s2, String s3, int i, int j) {
        // base case
        if (i == s1.length() && j == s2.length()) {
            return true;
        }

        // current index in s3
        int k = i + j;

        // take from s1 if possible
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (solveRec(s1, s2, s3, i + 1, j)) {
                return true;
            }
        }

        // take from s2 if possible
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            if (solveRec(s1, s2, s3, i, j + 1)) {
                return true;
            }
        }

        return false;
    }

    public boolean isInterleaveRec(String s1, String s2, String s3) {
        // length check
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        return solveRec(s1, s2, s3, 0, 0);
    }

    private boolean solveMem(String s1, String s2, String s3, int i, int j, Boolean[][] memo) {
        // base case
        if (i == s1.length() && j == s2.length()) {
            return true;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int k = i + j;

        // option 1: take from s1 if matching
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            if (solveMem(s1, s2, s3, i + 1, j, memo)) {
                return memo[i][j] = true;
            }
        }

        // option 2: take from s2 if matching
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            if (solveMem(s1, s2, s3, i, j + 1, memo)) {
                return memo[i][j] = true;
            }
        }

        return memo[i][j] = false;
    }

    public boolean isInterleaveMem(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return solveMem(s1, s2, s3, 0, 0, memo);
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        int len = s3.length();

        if (n + m != len) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c = s3.charAt(i + j - 1);

                if (s1.charAt(i - 1) == c && dp[i - 1][j]) {
                    dp[i][j] = true;
                } else if (s2.charAt(j - 1) == c && dp[i][j - 1]) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[n][m];
    }



}
