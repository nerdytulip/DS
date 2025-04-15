package com.company.DP.LongestIncreasingSubsequenceType;

import java.util.Arrays;

public class MaxHeightByStackingCuboid {

    /**
     * https://leetcode.com/problems/maximum-height-by-stacking-cuboids/description/
     * */

    private boolean canStack(int[] newBox,int[] base) {
        return  newBox[0] <= base[0]  && newBox[1] <= base[1] && newBox[2] <= base[2];
    }

    // Backward LIS Calculation using prev-current approach
    private int calculateLIS(int[][] cuboids, int n) {
        int[] current = new int[n + 1]; // Stores LIS for current iteration
        int[] next = new int[n + 1];    // Stores LIS for next iteration
        Arrays.fill(current, 0);
        Arrays.fill(next, 0);

        // Process cuboids in reverse order
        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {

                // Case 1: Include current cuboid
                int take = 0;
                if (prev == -1 || canStack(cuboids[prev],cuboids[curr])) { // Valid stacking
                    take = cuboids[curr][2] + next[curr + 1]; // Add current height
                }

                // Case 2: Exclude current cuboid
                int notTake = next[prev + 1]; // Skip the current cuboid

                // Store the max of include or exclude cases
                current[prev + 1] = Math.max(take, notTake);
            }

            // Move current to next for the next iteration
            int[] temp = next;
            next = current;
            current = temp;
        }

        // Final result stored at position 0
        return next[0];
    }

    public int maxHeight(int[][] cuboids) {

        // Step 1: Sort dimensions within each cuboid to make max as height
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid); // Guarantees width ≤ length ≤ height
        }

        // Step 2: Sort all cuboids based on width, length, and height
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];  // Ascending width
            if (a[1] != b[1]) return a[1] - b[1];  // Ascending length
            return a[2] - b[2];                    // Ascending height
        });

        // Step 3: Use LIS to calculate the maximum stackable height
        return calculateLIS(cuboids, cuboids.length);
    }
}
