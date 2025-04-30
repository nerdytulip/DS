package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfLongestIncreasingSubsequence_FenwickTree {

    // Holds the length of an LIS ending here, and how many such sequences
    private static class SequenceInfo {
        int length;
        int count;

        SequenceInfo(int length, int count) {
            this.length = length;
            this.count = count;
        }
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        // 1) Coordinate-compress the values into ranks 1..maxRank
        int[] sortedNums = Arrays.stream(nums)
                .sorted()
                .distinct()
                .toArray();

        Map<Integer, Integer> valueToRank = new HashMap<>();
        int maxRank = 0;
        for (int value : sortedNums) {
            if (!valueToRank.containsKey(value)) {
                valueToRank.put(value, ++maxRank);
            }
        }

        // 2) Fenwick (BIT) tree array of SequenceInfo, 1-indexed
        SequenceInfo[] fenwickTree = new SequenceInfo[maxRank + 1];
        for (int i = 1; i <= maxRank; i++) {
            fenwickTree[i] = new SequenceInfo(0, 0);
        }

        // 3) Process each number in original order
        for (int number : nums) {
            int rank = valueToRank.get(number);

            // Query the best (length, count) for all ranks < current rank
            SequenceInfo bestPrefix = queryFenwick(fenwickTree, rank - 1);

            // Build our new SequenceInfo for this number
            int newLength = bestPrefix.length + 1;
            int newCount = (bestPrefix.length == 0 ? 1 : bestPrefix.count);

            // Update the Fenwick tree at 'rank' with our new info
            updateFenwick(fenwickTree, maxRank, rank,
                    new SequenceInfo(newLength, newCount));
        }

        // 4) The answer is the total count for the overall max length
        SequenceInfo overallBest = queryFenwick(fenwickTree, maxRank);
        return overallBest.count;
    }

    // Returns the combined SequenceInfo for all indices <= idx
    private SequenceInfo queryFenwick(SequenceInfo[] tree, int idx) {
        SequenceInfo result = new SequenceInfo(0, 0);
        while (idx > 0) {
            result = combine(result, tree[idx]);
            idx -= idx & -idx;
        }
        return result;
    }

    // Merges updateInfo into all tree positions >= idx
    private void updateFenwick(SequenceInfo[] tree, int size, int idx, SequenceInfo updateInfo) {
        while (idx <= size) {
            tree[idx] = combine(tree[idx], updateInfo);
            /**
             * idx     =  0101  (that’s 5)
               -idx    =  1011  (flip 0101 → 1010, then +1 → 1011)
               idx & -idx = 0001  (only the rightmost 1 bit)
               idx = idx + (idx & -idx);
               = 5   + 1
               = 6
             */
            idx += idx & -idx;
        }
    }

    // Combines two SequenceInfo objects:
    // • If lengths equal, sum their counts
    // • Otherwise take the one with larger length
    private SequenceInfo combine(SequenceInfo a, SequenceInfo b) {
        if (a.length == b.length) {
            return new SequenceInfo(a.length, a.count + b.count);
        }
        return (a.length > b.length) ? a : b;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,4,7};
        NumberOfLongestIncreasingSubsequence_FenwickTree subsequence_fenwickTree = new NumberOfLongestIncreasingSubsequence_FenwickTree();
        int result = subsequence_fenwickTree.findNumberOfLIS(nums);
    }

}
