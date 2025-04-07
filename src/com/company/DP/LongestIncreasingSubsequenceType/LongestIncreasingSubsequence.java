package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    private int solveRec(int[] nums, int curr, int prev) {
        //base-case
        if (curr == nums.length) {
            return 0;
        }

        //include
        //prev is when you are standing at -1
        int take = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            //because prev = curr , and since included so we are moving prev to one point next
            take = 1 + solveRec(nums, curr + 1, curr);
        }

        //exclude
        //prev remains same because we did not pick the current element
        int notTake = 0 + solveRec(nums, curr + 1, prev);

        return Math.max(take, notTake);
    }

    public int lengthOfLISRec(int[] nums) {
        return solveRec(nums, 0, -1);
    }

    //top-down TC - O(n^2) and SC - O(n^2)
    private int solveMem(int[] nums, int curr, int prev, int[][] memo) {
        //base-case
        if (curr == nums.length) {
            return 0;
        }

        if (memo[curr][prev + 1] != -1) {
            return memo[curr][prev + 1];
        }

        //include
        //prev is when you are standing at -1
        int take = 0;
        if (prev == -1 || nums[curr] > nums[prev]) {
            //because prev = curr , and since included so we are moving prev to one point next
            take = 1 + solveMem(nums, curr + 1, curr, memo);
        }

        //exclude
        //prev remains same because we did not pick the current element
        int notTake = 0 + solveMem(nums, curr + 1, prev, memo);

        memo[curr][prev + 1] = Math.max(take, notTake);
        return memo[curr][prev + 1];
    }

    public int lengthOfLISMem(int[] nums) {
        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        int[][] memo = new int[nums.length][nums.length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return solveMem(nums, 0, -1, memo);
    }

    //bottom-up TC - O(n^2) and SC - O(n^2)
    private int solveTab(int[] nums) {

        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        int n = nums.length;
        //n+1 for row too because for the first element we need nth value , even though it is 0
        int[][] memo = new int[n + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, 0);
        }

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {

                //include
                //prev is when you are standing at -1
                int take = 0;
                if (prev == -1 || nums[curr] > nums[prev]) {
                    //because prev = curr , and since included so we are moving prev to one point next
                    take = 1 + memo[curr + 1][curr + 1];
                }

                //exclude
                //prev remains same because we did not pick the current element
                //+1 in prev because it can be -1 - since in bu we are decreasing the value
                int notTake = 0 + memo[curr + 1][prev + 1];

                memo[curr][prev + 1] = Math.max(take, notTake);
            }
        }
        // prev -1+1 = 0
        return memo[0][0];
    }

    public int lengthOfLISTab(int[] nums) {
        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        return solveTab(nums);
    }

    //so - TC - O(n^2) and SC - O(n)
    private int solveSO(int[] nums) {

        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        int n = nums.length;
        //n+1 for row too because for the first element we need nth value , even though it is 0
        int[] current = new int[n + 1];
        Arrays.fill(current, 0);
        int[] next = new int[n + 1];
        Arrays.fill(next, 0);

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {

                //include
                //prev is when you are standing at -1
                int take = 0;
                if (prev == -1 || nums[curr] > nums[prev]) {
                    //because prev = curr , and since included so we are moving prev to one point next
                    take = 1 + next[curr + 1];
                }

                //exclude
                //prev remains same because we did not pick the current element
                //+1 in prev because it can be -1 - since in bu we are decreasing the value
                int notTake = 0 + next[prev + 1];

                current[prev + 1] = Math.max(take, notTake);
            }

            int[] temp = next;
            next = current;
            current = temp;
        }
        // prev -1+1 = 0
        return next[0];
    }

    public int lengthOfLISSO(int[] nums) {
        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        return solveSO(nums);
    }

    // DP with binary search - TC (nlogn)
    private int solveOptimal(int[] nums){
        if(nums.length == 0){
            return 0;
        }

        List<Integer> ans = new ArrayList<>();
        ans.add(nums[0]);

        for(int i=1;i<nums.length;i++){
            if (nums[i] > ans.get(ans.size() - 1)) {
                // If current element is greater than the last element in ans
                ans.add(nums[i]);
            }else {
                // Find the index of the smallest element that is greater than or equal to a[i]
                // Find the index where a[i] can be inserted
                int index = lowerBound(ans, nums[i]);
                ans.set(index, nums[i]); // Replace the element
            }
        }

        return ans.size();
    }

    private static int lowerBound(List<Integer> list, int x) {
        int low = 0, high = list.size() - 1;
        int ans = list.size(); // Default answer points to the end (insertion at the end)

        while (low <= high) {
            int mid = low + (high - low) / 2; // Avoid integer overflow

            // Maybe an answer
            if (list.get(mid) >= x) {
                ans = mid;      // Possible answer found
                high = mid - 1; // Search on the left side for a smaller index
            } else {
                low = mid + 1;  // Search on the right side
            }
        }
        return ans; // Final position to insert or find the element
    }

    public int lengthOfLIS(int[] nums) {
        //curr goes from 0 to n-1 (n elements), and prev goes from -1 to n-1 (n+1 elements) - when storing values for -1 , we will store it at 0th index and n-1 in nth index
        return solveOptimal(nums);
    }

}
