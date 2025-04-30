package com.company.TwoPointer.SlidingWindow;

public class MinimumSizeSubArraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int minLenWindow = Integer.MAX_VALUE;
        int currentSum = 0;

        int low =0;
        int high =0;

        while(high<nums.length) {

            currentSum += nums[high];

            while (currentSum >= target) {
                int currentWindowSize = high - low + 1;
                minLenWindow = Math.min(minLenWindow, currentWindowSize);

                currentSum -= nums[low];
                low++;
            }
            high++;
        }

        return minLenWindow == Integer.MAX_VALUE ? 0 : minLenWindow;
    }
}
