package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumLengthOfPairSum {
    private int solveRec(int[][] pairs, int prevIndex, int currIndex) {
        if (currIndex == pairs.length) {
            return 0;
        }

        //take
        int take = 0;
        if (prevIndex == -1 || pairs[currIndex][0] > pairs[prevIndex][1]) {
            take = 1 + solveRec(pairs, currIndex, currIndex + 1);
        }

        // no take
        int noTake = 0 + solveRec(pairs, prevIndex, currIndex + 1);

        return Math.max(take, noTake);

    }

    public int findLongestChainRec(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        return solveRec(pairs, -1, 0);
    }

    private int solveMem(int[][] pairs, int prev, int curr, int[][] memo) {
        if (curr == pairs.length) {
            return 0;
        }

        if (memo[curr][prev + 1] != -1) {
            return memo[curr][prev + 1];
        }

        //take
        int take = 0;
        if (prev == -1 || pairs[curr][0] > pairs[prev][1]) {
            take = 1 + solveMem(pairs, curr, curr + 1, memo);
        }

        // no take
        int noTake = 0 + solveMem(pairs, prev, curr + 1, memo);

        return memo[curr][prev + 1] = Math.max(take, noTake);

    }

    public int findLongestChainMem(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[][] memo = new int[pairs.length][pairs.length + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return solveMem(pairs, -1, 0, memo);
    }

    private int solveTab(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[][] dp = new int[n + 1][n + 1];

        for (int curr = n - 1; curr >= 0; curr--) {
            // prev is always one behind current
            for (int prev = curr - 1; prev >= -1; prev--) {
                //take
                int take = 0;
                if (prev == -1 || pairs[curr][0] > pairs[prev][1]) {
                    take = 1 + dp[curr + 1][curr + 1];
                }

                // no take
                int noTake = 0 + dp[curr + 1][prev + 1];

                dp[curr][prev + 1] = Math.max(take, noTake);
            }
        }

        // prev -1+1 = 0
        return dp[0][0];
    }

    public int findLongestChainTab(int[][] pairs) {
        return solveTab(pairs);
    }

    //Optimal
    public int findLongestChain_Optimal(int[][] pairs) {
        if (pairs.length == 0) {
            return 0;
        }

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        ans.add(pairs[0]);

        for(int i=1;i<pairs.length;i++){
            if(pairs[i][0] > ans.get(ans.size()-1)[1]){
                ans.add(pairs[i]);
            }else{
                int index = lowerBound(ans, pairs[i][0]);
                // replace with a smaller ending to keep future options open
                if (pairs[i][1] < ans.get(index)[1]) {
                    ans.set(index, pairs[i]);
                }
            }
        }

        return ans.size();
    }

    private int lowerBound(List<int[]> list, int targetStart) {
        int low = 0, high = list.size() - 1;
        int ans = list.size(); // Default answer points to the end (insertion at the end)
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid)[1] >= targetStart) {
                ans = mid;
                high = mid - 1; // Search on the left side for a smaller index
            } else {
                low = mid + 1;  // Search on the right side
            }
        }
        return ans;
    }

    //greedy
    public int findLongestChain(int[][] pairs) {
        // Sort pairs in ascending order based on the second element.
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        int curr = Integer.MIN_VALUE;
        int ans = 0;

        for (int[] pair : pairs) {
            if (pair[0] > curr) {
                ans++;
                curr = pair[1];
            }
        }
        return ans;
    }

}
