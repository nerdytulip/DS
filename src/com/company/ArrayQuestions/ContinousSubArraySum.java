package com.company.ArrayQuestions;

import java.util.HashMap;
import java.util.Map;

public class ContinousSubArraySum {

    /**
     * https://leetcode.com/problems/continuous-subarray-sum/description/
     *
     * https://www.youtube.com/watch?v=1W_HYBqvDLw&t=84s
     * */

    public boolean checkSubarraySum(int[] nums, int k) {
        int prefSum = 0;       // Running prefix sum (counts #1 as +1, #0 as -1)
        int ans = 0;           // Variable to keep track of the longest valid subarray length
        Map<Integer, Integer> firstOccurence = new HashMap<>();

        // We start by saying that when the prefix sum is 0,
        // it effectively occurred right before the array started (index = -1).
        firstOccurence.put(0, -1);

        // Traverse through the array
        for (int i = 0; i < nums.length; i++) {
            // If nums[i] == 1, increment by 1; if nums[i] == 0, decrement by 1
            prefSum = (prefSum + nums[i]) % k;

            // If this prefix sum has already been seen,
            // calculate the subarray length between the first occurrence and the current index
            if (firstOccurence.containsKey(prefSum)) {
                if((i) - firstOccurence.get(prefSum)>=2){
                    return true;
                }
            }else {
                // If this prefix sum hasn't been seen before, store the current index
                // as the earliest occurrence of this prefix sum
                firstOccurence.put(prefSum, i);

            }
        }
        return false;
    }
}
