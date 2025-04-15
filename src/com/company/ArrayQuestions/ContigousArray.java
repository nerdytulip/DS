package com.company.ArrayQuestions;

import java.util.HashMap;
import java.util.Map;

public class ContigousArray {
    //https://leetcode.com/problems/contiguous-array/description/
    // https://www.youtube.com/watch?v=VtC__J8G8Tw&t=9s

    public int findMaxLength(int[] nums) {
        int prefSum = 0;       // Running prefix sum (counts #1 as +1, #0 as -1)
        int ans = 0;           // Variable to keep track of the longest valid subarray length
        Map<Integer, Integer> firstOccurence = new HashMap<>();

        // We start by saying that when the prefix sum is 0,
        // it effectively occurred right before the array started (index = -1).
        firstOccurence.put(0, -1);

        // Traverse through the array
        for (int i = 0; i < nums.length; i++) {
            // If nums[i] == 1, increment by 1; if nums[i] == 0, decrement by 1
            prefSum += (nums[i] == 1) ? 1 : -1;

            // If this prefix sum hasn't been seen before, store the current index
            // as the earliest occurrence of this prefix sum
            if (!firstOccurence.containsKey(prefSum)) {
                firstOccurence.put(prefSum, i);
            }
            // If this prefix sum has already been seen,
            // calculate the subarray length between the first occurrence and the current index
            else {
                ans = Math.max(ans, i - firstOccurence.get(prefSum));
            }
        }

        return ans;
    }
}
