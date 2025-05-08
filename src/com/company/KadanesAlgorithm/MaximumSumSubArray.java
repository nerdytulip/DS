package com.company.KadanesAlgorithm;

public class MaximumSumSubArray {

    // Kadane's Algorithm
    public int maxSubArray(int[] nums) {
        int maxi = Integer.MIN_VALUE;
        int prefixSum = 0;

        for(int i = 0;i<nums.length;i++){
            prefixSum += nums[i];
            maxi = Math.max(maxi,prefixSum);
            if(prefixSum<0){
                prefixSum = 0;
            }
        }

        return maxi;
    }
}
