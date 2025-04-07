package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinimumSwapsToMakeSequencesIncreasing {
    private int solveRec(int[] nums1, int[] nums2, int index, boolean swapped) {
        if(index == nums1.length){
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        int prev1 = nums1[index-1];
        int prev2 = nums2[index-1];

        if (swapped) {
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        //noswap
        if(nums1[index] > prev1 && nums2[index] > prev2){
            ans = solveRec(nums1,nums2,index+1,false);
        }

        //swap
        if(nums1[index] > prev2 && nums2[index] > prev1){
            ans = Math.min(ans,1 + solveRec(nums1,nums2,index+1,true));
        }
        return ans;
    }

    public int minSwapRec(int[] nums1, int[] nums2) {
        //signifies if previous two numbers are swapped or not
        boolean swapped = false;
        int[] nums1_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums1))
                .toArray();
        int[] nums2_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums2))
                .toArray();
        return solveRec(nums1_changed,nums2_changed,1,swapped);
    }

    private int solveMem(int[] nums1, int[] nums2, int index, boolean swapped, int[][] memo) {
        if(index == nums1.length){
            return 0;
        }

        int swap_int = swapped ? 1 :0;
        if(memo[index][swap_int]!=-1){
            return memo[index][swap_int];
        }

        int ans = Integer.MAX_VALUE;

        int prev1 = nums1[index-1];
        int prev2 = nums2[index-1];

        if (swapped) {
            int temp = prev1;
            prev1 = prev2;
            prev2 = temp;
        }

        //noswap
        if(nums1[index] > prev1 && nums2[index] > prev2){
            ans = solveMem(nums1,nums2,index+1,false,memo);
        }

        //swap
        if(nums1[index] > prev2 && nums2[index] > prev1){
            ans = Math.min(ans,1 + solveMem(nums1,nums2,index+1,true,memo));
        }

        return memo[index][swap_int]=ans;
    }

    public int minSwapMem(int[] nums1, int[] nums2) {
        //signifies if previous two numbers are swapped or not
        boolean swapped = false;
        int[] nums1_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums1))
                .toArray();
        int[] nums2_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums2))
                .toArray();

        int n = nums1_changed.length;
        int[][] memo = new int[n+1][2];
        for(int[] row : memo){
            Arrays.fill(row,-1);
        }

        return solveMem(nums1_changed,nums2_changed,1,swapped,memo);
    }

    private int solveTab(int[] nums1, int[] nums2){
        int n = nums1.length;
        int[][] memo = new int[n+1][2];
        for(int[] row : memo){
            Arrays.fill(row,0);
        }

        for(int index = n-1;index>=1;index--){
            for(int swapped = 1;swapped>=0;swapped--){
                int ans = Integer.MAX_VALUE;

                int prev1 = nums1[index-1];
                int prev2 = nums2[index-1];

                if (swapped == 1) {
                    int temp = prev1;
                    prev1 = prev2;
                    prev2 = temp;
                }

                //noswap
                if(nums1[index] > prev1 && nums2[index] > prev2){
                    ans = memo[index+1][0];
                }

                //swap
                if(nums1[index] > prev2 && nums2[index] > prev1){
                    ans = Math.min(ans,1 + memo[index+1][1]);
                }

                memo[index][swapped]=ans;
            }
        }

        return memo[1][0];
    }

    public int minSwapTab(int[] nums1, int[] nums2) {
        //signifies if previous two numbers are swapped or not
        boolean swapped = false;
        int[] nums1_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums1))
                .toArray();
        int[] nums2_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums2))
                .toArray();

        return solveTab(nums1_changed,nums2_changed);
    }

    private int solveSO(int[] nums1, int[] nums2){
        int n = nums1.length;

        //because when we had dp array , we had initialized values in memo array with 0
        int swap =0;
        int noSwap = 0;

        int currSwapped =0;
        int currNoSwapped = 0;

        for(int index = n-1;index>=1;index--){
            // Reset current values for this iteration
            currSwapped = Integer.MAX_VALUE;
            currNoSwapped = Integer.MAX_VALUE;

            for(int swapped = 1;swapped>=0;swapped--){
                int ans = Integer.MAX_VALUE;

                int prev1 = nums1[index-1];
                int prev2 = nums2[index-1];

                if (swapped == 1) {
                    int temp = prev1;
                    prev1 = prev2;
                    prev2 = temp;
                }

                //noswap
                if(nums1[index] > prev1 && nums2[index] > prev2){
                    ans = noSwap;
                }

                //swap
                if(nums1[index] > prev2 && nums2[index] > prev1){
                    ans = Math.min(ans,1 + swap);
                }

                if(swapped == 1){
                    currSwapped = ans;
                }else{
                    currNoSwapped = ans;
                }
            }

            swap = currSwapped;
            noSwap = currNoSwapped;
        }

        return Math.min(swap,noSwap);
    }

    public int minSwap(int[] nums1, int[] nums2) {
        //signifies if previous two numbers are swapped or not
        boolean swapped = false;
        int[] nums1_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums1))
                .toArray();
        int[] nums2_changed = IntStream.concat(IntStream.of(-1), IntStream.of(nums2))
                .toArray();

        return solveTab(nums1_changed,nums2_changed);
    }

}
