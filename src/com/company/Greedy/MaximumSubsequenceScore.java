package com.company.Greedy;

import java.util.*;

// https://www.youtube.com/watch?v=bwWk-oRLuxg
// https://leetcode.com/problems/maximum-subsequence-score/description/?envType=study-plan-v2&envId=leetcode-75
public class MaximumSubsequenceScore {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        List<int[]> pairs = new ArrayList<>();
        for(int i =0;i<nums1.length;i++){
            pairs.add(new int[]{nums1[i],nums2[i]});
        }

        pairs.sort((a,b) -> b[1] - a[1]);

        long ans = Integer.MIN_VALUE;
        long curr_sum = 0;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for(int i=0;i<k;i++){
            int[] pair = pairs.get(i);
            curr_sum += pair[0];
            heap.add(pair[0]);
        }

        ans = Math.max(ans,curr_sum* pairs.get(k-1)[1]);

        // we are expanding now
        for(int i =k;i<nums1.length;i++){
            int[] pair = pairs.get(i);
            curr_sum+= pair[0] - heap.poll();
            heap.add(pair[0]);

            ans = Math.max(ans, curr_sum*pair[1]);
        }

        return ans;
    }
}
