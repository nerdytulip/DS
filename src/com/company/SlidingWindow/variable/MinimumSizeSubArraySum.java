package com.company.SlidingWindow.variable;

public class MinimumSizeSubArraySum {
    // https://leetcode.com/problems/minimum-size-subarray-sum/description/
    public int minSubArrayLen(int target, int[] nums) {
        int minLenWindow = Integer.MAX_VALUE;
        int currentSum = 0;

        int i =0;
        int j =0;

        while(j<nums.length) {

            currentSum += nums[j];

            while (currentSum >= target) {
                int currentWindowSize = j - i + 1;
                minLenWindow = Math.min(minLenWindow, currentWindowSize);

                currentSum -= nums[i];
                i++;
            }
            j++;
        }

        return minLenWindow == Integer.MAX_VALUE ? 0 : minLenWindow;
    }
}
