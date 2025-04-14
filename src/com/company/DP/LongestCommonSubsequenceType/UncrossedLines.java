package com.company.DP.LongestCommonSubsequenceType;

import java.util.Arrays;

public class UncrossedLines {

    /**
     * https://leetcode.com/problems/uncrossed-lines/description/?envType=study-plan-v2&envId=dynamic-programming
     * */

    private int solveRec(int[] nums1, int[] nums2, int i, int j) {
        if(i >= nums1.length || j >= nums2.length){
            return 0;
        }

        //match
        int match = 0;
        int noMatch = 0;
        if(nums1[i] == nums2[j]) {
            match = 1 + solveRec(nums1, nums2, i+1, j+1);
        } else{
            //no match
            noMatch = Math.max(solveRec(nums1,nums2,i+1,j),solveRec(nums1,nums2,i,j+1));
        }

        return Math.max(match,noMatch);
    }

    public int maxUncrossedLinesRec(int[] nums1, int[] nums2) {
        return solveRec(nums1,nums2,0,0);
    }

    private int solveMem(int[] nums1, int[] nums2, int i, int j, int[][] memo) {
        if(i >= nums1.length || j >= nums2.length){
            return 0;
        }

        if(memo[i][j]!=-1){
            return memo[i][j];
        }

        //match
        int match = 0;
        int noMatch = 0;
        if(nums1[i] == nums2[j]) {
            match = 1 + solveMem(nums1, nums2, i+1, j+1,memo);
        } else{
            //no match
            noMatch = Math.max(solveMem(nums1,nums2,i+1,j,memo),solveMem(nums1,nums2,i,j+1,memo));
        }

        return memo[i][j] = Math.max(match,noMatch);
    }

    public int maxUncrossedLinesMem(int[] nums1, int[] nums2) {
        int[][] memo = new int[nums1.length][nums2.length];
        for(int[] rows: memo){
            Arrays.fill(rows,-1);
        }

        return solveMem(nums1,nums2,0,0,memo);
    }

    //Bu
    private int solveTab(int[] nums1, int[] nums2) {

        int[][] dp = new int[nums1.length+1][nums2.length+1];

        for(int i = nums1.length-1;i>=0;i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                //match
                int match = 0;
                int noMatch = 0;
                if (nums1[i] == nums2[j]) {
                    match = 1 + dp[i + 1][j + 1];
                } else {
                    //no match
                    noMatch = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }

                dp[i][j] = Math.max(match, noMatch);
            }
        }

        return dp[0][0];
    }

    public int maxUncrossedLinesTab(int[] nums1, int[] nums2) {
        return solveTab(nums1,nums2);
    }

    private int solveSO(int[] nums1, int[] nums2) {

        int[] curr = new int[nums2.length+1];
        int[] next = new int[nums2.length+1];

        for(int i = nums1.length-1;i>=0;i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                //match
                int match = 0;
                int noMatch = 0;
                if (nums1[i] == nums2[j]) {
                    match = 1 + next[j + 1];
                } else {
                    //no match
                    noMatch = Math.max(next[j], curr[j + 1]);
                }

                curr[j] = Math.max(match, noMatch);
            }

            int[] temp = next;
            next = curr;
            curr = temp;
        }

        return next[0];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        return solveSO(nums1,nums2);
    }
}
