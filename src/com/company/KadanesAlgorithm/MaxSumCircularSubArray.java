package com.company.KadanesAlgorithm;

public class MaxSumCircularSubArray {

    /**
     * https://leetcode.com/problems/maximum-sum-circular-subarray/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=Za8V4wkZKkM
     * */

    /**
     * TotalSum - A
     * Minimum Sum Subarray - B
     * Maximum Sum Subarray (Can be in straight array)
     * Circular max sum subarray - C = A - B (when in circular)
     *
     * Kadane's also can help take out minimym sum
     * */
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;

        // total sum
        for(int num : nums){
            totalSum+=num;
        }

        // Kadane's Min
        int minSum = KadanesMin(nums);

        // Kadane's Max
        int maxSum = KadanesMax(nums);

        // circular sum
        int circularSum = totalSum - minSum;

        if(maxSum>0){
            return Math.max(maxSum,circularSum);
        }

        return maxSum;
    }

    private int KadanesMax(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];

        for(int i=1;i<nums.length;i++){
            sum = Math.max(sum+nums[i],nums[i]);
            maxSum = Math.max(maxSum,sum);
        }
        return maxSum;
    }

    private int KadanesMin(int[] nums){
        int sum = nums[0];
        int minSum = nums[0];

        for(int i=1;i<nums.length;i++){
            sum = Math.min(sum+nums[i],nums[i]);
            minSum = Math.min(minSum,sum);
        }
        return minSum;
    }
}
