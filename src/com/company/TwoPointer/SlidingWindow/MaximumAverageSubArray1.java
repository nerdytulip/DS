package com.company.TwoPointer.SlidingWindow;

public class MaximumAverageSubArray1 {
    public double findMaxAverage(int[] nums, int k) {
        int i=0,j=0;
        double avg = 0;
        double maxAverage = Integer.MIN_VALUE;

        while(j<nums.length){
            avg+= nums[j];
            if((j-i+1) < k){
                j++;
            } else if(j-i+1 == k){
                maxAverage = Math.max(maxAverage,avg/k);
                avg-=nums[i];
                i++;
                j++;
            }
        }
        return maxAverage;
    }
}
