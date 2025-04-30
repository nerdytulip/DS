package com.company.TwoPointer.SlidingWindow;

public class LongestSubArrayOf1sAfterDeletingOneElement {
    public int longestSubarray(int[] nums) {
        int countZeros = 0;
        int i=0,j=0;
        int longestConsecutiveOnes = Integer.MIN_VALUE;

        while(j<nums.length){
            if (nums[j] == 0) {
                countZeros++;
            }

            while (countZeros > 1) {
                if (nums[i] == 0) {
                    countZeros--;
                }
                i++;
            }

            int currentWindowLength = j - i + 1;
            longestConsecutiveOnes = Math.max(longestConsecutiveOnes, currentWindowLength-1);
            j++;
        }
        return longestConsecutiveOnes;
    }
}
