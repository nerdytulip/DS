package com.company.SlidingWindow.fixed;

import java.util.Arrays;

public class ChocolateDistribution {
    /**
     * https://www.geeksforgeeks.org/chocolate-distribution-problem/
     *
     * */
    static int findMinDiff(int[] arr, int m){
        Arrays.sort(arr);
        int i = 0;
        int j = m -1;

        int mini = Integer.MAX_VALUE;
        while(j<arr.length){
            int diff = arr[j] - arr[i];
            mini = Math.min(mini,diff);
            i++;
            j++;
        }

        return mini;
    }
}
