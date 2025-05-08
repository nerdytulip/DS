package com.company.Heap;

import java.util.*;

public class FindKPairsWithSmallestSums {
    /**
     * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=PiGYS7BbV_Q
     * */
    class SumEntry {
        int i;     // index in nums1
        int j;     // index in nums2
        int sum;   // nums1[i] + nums2[j]

        public SumEntry(int i, int j, int[] nums1, int[] nums2) {
            this.i = i;
            this.j = j;
            this.sum = nums1[i] + nums2[j];
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        PriorityQueue<SumEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.sum));
        Set<String> visited = new HashSet<>();

        SumEntry intialEntry = new SumEntry(0, 0, nums1, nums2);
        minHeap.add(intialEntry);
        visited.add(getKey(0,0));

        while (k-- > 0 && !minHeap.isEmpty()) {
            SumEntry entry = minHeap.poll();
            //(i,j)
            int i = entry.i;
            int j = entry.j;
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // push (i,j+1) if possible
            if (j + 1 < nums2.length) {
                String key1 = getKey(i, j + 1);
                if (!visited.contains(key1)) {
                    minHeap.offer(new SumEntry(i, j + 1, nums1, nums2));
                    visited.add(key1);
                }
            }

            // push (i+1,j) if possible
            if (i + 1 < nums1.length) {
                String key2 = getKey(i + 1, j);
                if (!visited.contains(key2)) {
                    minHeap.offer(new SumEntry(i + 1, j, nums1, nums2));
                    visited.add(key2);
                }
            }
        }

        return result;
    }

    private String getKey(int i, int j) {
        return i + "," + j;
    }
}
