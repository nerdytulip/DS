package com.company.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithSumK {
    public int longestSubarrayWithSumK(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int prefixSum = 0, maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            // Case 1: If the sum from 0 to i is k
            if (prefixSum == k) {
                maxLen = i + 1;
            }

            // Case 2: If there exists a prefixSum - k
            if (prefixMap.containsKey(prefixSum - k)) {
                int prevIndex = prefixMap.get(prefixSum - k);
                maxLen = Math.max(maxLen, i - prevIndex);
            }

            // Store first occurrence of prefixSum
            if (!prefixMap.containsKey(prefixSum)) {
                prefixMap.put(prefixSum, i);
            }
        }

        return maxLen;
    }

}
