package com.company.DP.LongestCommonSubsequenceType;

import java.util.Arrays;

public class CountDistinctSubsequences {

    /**
     * https://leetcode.com/problems/distinct-subsequences/description/?envType=study-plan-v2&envId=dynamic-programming
     * */
    int solveRec(String s, String t, int i, int j) {
        // Base cases
        if (j == t.length()) {
            return 1; // Entire t matched
        }
        if (i == s.length()) {
            return 0; // s exhausted before t
        }

        if (s.charAt(i) == t.charAt(j)) {
            // Two options: match or skip
            return solveRec(s, t, i + 1, j + 1) + solveRec(s, t, i + 1, j);
        } else {
            // Only option: skip
            return solveRec(s, t, i + 1, j);
        }
    }

    public int numDistinctRec(String s, String t) {
        return solveRec(s,t,0,0);
    }

    int solveMem(String s, String t, int i, int j, int[][] memo) {
        // Base cases
        if (j == t.length()) {
            return 1; // Entire t matched
        }
        if (i == s.length()) {
            return 0; // s exhausted before t
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        if (s.charAt(i) == t.charAt(j)) {
            // Two options: match or skip
            memo[i][j] = solveMem(s, t, i + 1, j + 1,memo) + solveMem(s, t, i + 1, j,memo);
        } else {
            // Only option: skip
            memo[i][j] = solveMem(s, t, i + 1, j,memo);
        }

        return memo[i][j];
    }

    public int numDistinctMem(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        for(int[] row : memo){
            Arrays.fill(row,-1);
        }
        return solveMem(s,t,0,0,memo);
    }

    int solveTab(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] memo = new int[n+1][m+1];

        for(int i = 0;i<=n;i++){
            memo[i][m] = 1;
        }

        for(int i=n-1;i>=0;i--){
            for (int j=m-1;j>=0;j--){
                if (s.charAt(i) == t.charAt(j)) {
                    // Two options: match or skip
                    memo[i][j] = memo[i + 1][j + 1] + memo[i + 1][j];
                } else {
                    // Only option: skip
                    memo[i][j] = memo[i + 1][j];
                }
            }
        }
        return memo[0][0];
    }

    public int numDistinctTab(String s, String t) {
        return solveTab(s,t);
    }

    int solveSO(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] curr =  new int[m+1];
        int[] next = new int[m+1];

        // Only one-time setup: Empty t matched by any suffix of s
        next[m] = 1;

        for(int i=n-1;i>=0;i--){
            // Important: Set curr[m] = 1 at the start of each i loop
            curr[m] = 1;
            for (int j=m-1;j>=0;j--){
                if (s.charAt(i) == t.charAt(j)) {
                    // Two options: match or skip
                    curr[j] = next[j + 1] + next[j];
                } else {
                    // Only option: skip
                    curr[j] = next[j];
                }
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int numDistinct(String s, String t) {
        return solveSO(s,t);
    }
}
