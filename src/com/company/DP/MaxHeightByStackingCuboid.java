package com.company.DP;

import java.util.Arrays;

public class MaxHeightByStackingCuboid {

    // Check if 'base' can hold 'newBox'
    private boolean canStack_1_Array(int[] base, int[] newBox) {
        return base[0] <= newBox[0] && base[1] <= newBox[1] && base[2] <= newBox[2];
    }

    // LIS Calculation for Maximum Height
    private int calculateLIS_1_Array(int[][] cuboids, int n) {
        int[] dp = new int[n]; // dp[i] = max height ending at i
        int maxHeight = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2]; // Start with current height
            for (int j = 0; j < i; j++) {
                if (canStack(cuboids[j], cuboids[i])) { // Check if stackable
                    dp[i] = Math.max(dp[i], cuboids[i][2] + dp[j]);
                }
            }
            maxHeight = Math.max(maxHeight, dp[i]); // Track global max height
        }

        return maxHeight;
    }

    private boolean canStack(int[] base, int[] newBox) {
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
                if (prev == -1 || canStack(cuboids[curr], cuboids[prev])) { // Valid stacking
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
