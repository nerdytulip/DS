package com.company.Greedy;

import java.util.Arrays;

public class MinNumberOfCoinsToBeAdded {

    public static int minCoinsToObtainTarget(int[] coins, int target) {
        Arrays.sort(coins); // Step 1: Sort the coins array
        long reachable = 0; // Step 2: Track the maximum reachable value
        int addedCoins = 0; // Counter for the number of added coins
        int i = 0;          // Pointer to iterate over the sorted coins

        // Step 3: Keep expanding the range until reachable >= target
        while (reachable < target) {
            if (i < coins.length && coins[i] <= reachable + 1) {
                // Case 1: The current coin can help extend the reachable range
                reachable += coins[i];
                i++; // Move to the next coin
            } else {
                // Case 2: Add a new coin with value `reachable + 1`
                addedCoins++;
                reachable += reachable + 1;
            }
        }

        return addedCoins; // Return the number of coins added
    }

    public static void main(String[] args) {
        // Example 1
        int[] coins1 = {1, 4, 10};
        int target1 = 19;
        System.out.println(minCoinsToObtainTarget(coins1, target1)); // Output: 2

        // Example 2
        int[] coins2 = {1, 4, 10, 5, 7, 19};
        int target2 = 19;
        System.out.println(minCoinsToObtainTarget(coins2, target2)); // Output: 1

        // Example 3
        int[] coins3 = {1, 1, 1};
        int target3 = 20;
        System.out.println(minCoinsToObtainTarget(coins3, target3)); // Output: 3
    }
}
