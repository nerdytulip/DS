package com.company.SlidingWindow.variable;

public class MaxConsecutiveOnes {
    // https://leetcode.com/problems/max-consecutive-ones-iii/description/
    public int longestOnes(int[] nums, int k) {
        int countZeros = 0;
        int i=0,j=0;
        int longestConsecutiveOnes = Integer.MIN_VALUE;

        while(j<nums.length){
            if (nums[j] == 0) {
                countZeros++;
            }

            while (countZeros > k) {
                if (nums[i] == 0) {
                    countZeros--;
                }
                i++;
            }

            longestConsecutiveOnes = Math.max(longestConsecutiveOnes, j - i + 1);
            j++;
        }
        return longestConsecutiveOnes;
    }
}
